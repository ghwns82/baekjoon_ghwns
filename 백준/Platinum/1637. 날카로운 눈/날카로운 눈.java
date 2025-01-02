import java.io.*;
import java.util.*;

public class Main {
    static List<int[]> dummies = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dummies.add(new int[]{a, c, b});
        }

        long s = 1, e = 2_147_483_647L;

        if (search(e) % 2 == 0) {
            System.out.println("NOTHING");
            return;
        }

        long result = e;
        while (s <= e) {
            long m = (s + e) / 2;
            long v = search(m);
            
            if (v % 2 == 1) {
                e = m - 1;
                result = Math.min(result, m);
            } else {
                s = m + 1;
            }
        }

        System.out.println(result + " " + (search(result) - search(result - 1)));
    }

    static long search(long n) {
        long count = 0;
        for (int[] dummy : dummies) {
            int a = dummy[0], c = dummy[1], b = dummy[2];
            if (a > n) continue;
            count += (Math.min(c, n) - a) / b + 1;
        }
        return count;
    }
}