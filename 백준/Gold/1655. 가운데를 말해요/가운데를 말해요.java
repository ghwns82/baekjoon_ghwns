import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>();
        Queue<Integer> mid = new LinkedList<>();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (mid.isEmpty()) {
                mid.add(num);
            } else {
                if (mid.peek() <= num) {
                    right.offer(num);
                } else {
                    left.offer(num);
                }
            }

            if (left.size() > right.size()) {
                right.offer(mid.poll());
                mid.add(left.poll());
            }
            if (right.size() > left.size() + 1) {
                left.offer(mid.poll());
                mid.add(right.poll());
            }

            sb.append(mid.peek()).append('\n');
        }
        System.out.print(sb);
    }
}
