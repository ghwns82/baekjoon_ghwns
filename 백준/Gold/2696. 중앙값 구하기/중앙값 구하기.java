import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] nums = new int[n];
            int idx = 0;
            while (idx < n) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    nums[idx++] = Integer.parseInt(st.nextToken());
                }
            }

            PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> right = new PriorityQueue<>();
            List<Integer> result = new ArrayList<>();

            int mid = nums[0];
            result.add(mid);
            for (int i = 1; i < n; i++) {
                int num = nums[i];
                if (num >= mid) {
                    right.offer(num);
                } else {
                    left.offer(num);
                }

                if (left.size() < right.size()) {
                    left.offer(mid);
                    mid = right.poll();
                } else if (left.size() > right.size()) {
                    right.offer(mid);
                    mid = left.poll();
                }

                if (i % 2 == 0) {
                    result.add(mid);
                }
            }

            sb.append(result.size()).append("\n");
            for (int i = 0; i < result.size(); i++) {
                sb.append(result.get(i)).append(" ");
                if ((i + 1) % 10 == 0) sb.append("\n");
            }
            if (result.size() % 10 != 0) sb.append("\n");
        }

        System.out.print(sb);
    }
}
