import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // Number of singers
        int m = Integer.parseInt(st.nextToken()); // Number of PDs

        int[] enter = new int[n + 1]; // In-degree array
        Set<Integer> singer = new HashSet<>();
        Map<Integer, List<Integer>> log = new HashMap<>(); // Adjacency list

        for (int i = 1; i <= n; i++) {
            singer.add(i);
            log.put(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            int[] sequence = new int[count];

            for (int j = 0; j < count; j++) {
                sequence[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < count - 1; j++) {
                int a = sequence[j];
                int b = sequence[j + 1];
                if (!log.get(a).contains(b)) {
                    log.get(a).add(b);
                    enter[b]++;
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        Queue<Integer> work = new LinkedList<>();

        while (!singer.isEmpty()) {
            for (Iterator<Integer> it = singer.iterator(); it.hasNext(); ) {
                int i = it.next();
                if (enter[i] == 0) {
                    work.offer(i);
                    it.remove();
                }
            }

            if (work.isEmpty()) {
                System.out.println(0);
                return;
            }

            while (!work.isEmpty()) {
                int i = work.poll();
                result.add(i);
                for (int j : log.get(i)) {
                    enter[j]--;
                }
            }
        }

        for (int num : result) {
            System.out.println(num);
        }
    }
}