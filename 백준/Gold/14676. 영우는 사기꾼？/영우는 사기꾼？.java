import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Map<Integer, Set<Integer>> info = new HashMap<>();
        int[] pre = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            info.putIfAbsent(a, new HashSet<>());
            info.get(a).add(b);
            pre[b]++;
        }

        Map<Integer, Integer> mine = new HashMap<>();
        String result = "King-God-Emperor";

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == 1) {
                if (pre[b] == 0) {
                    mine.putIfAbsent(b, 0);
                    if (mine.get(b) == 0) {
                        if (info.containsKey(b)) {
                            for (int p : info.get(b)) {
                                pre[p]--;
                            }
                        }
                    }
                    mine.put(b, mine.get(b) + 1);
                } else {
                    result = "Lier!";
                }
            } else {
                if (mine.getOrDefault(b, 0) > 0) {
                    mine.put(b, mine.get(b) - 1);
                    if (mine.get(b) == 0) {
                        if (info.containsKey(b)) {
                            for (int p : info.get(b)) {
                                pre[p]++;
                            }
                        }
                    }
                } else {
                    result = "Lier!";
                }
            }
        }

        System.out.println(result);
    }
}