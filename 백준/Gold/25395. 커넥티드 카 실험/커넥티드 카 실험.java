import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken()) - 1;

        int[] loc = new int[n];
        int[] fuel = new int[n];
        TreeSet<Integer> result = new TreeSet<>();
        result.add(s + 1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            loc[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            fuel[i] = Integer.parseInt(st.nextToken());
        }

        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{s, fuel[s]});

        int left = s - 1;
        int right = s + 1;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int now = current[0];
            int power = current[1];

            if (left >= 0 && loc[now] - loc[left] <= power) {
                result.add(left + 1);
                q.offer(new int[]{left, Math.max(power - (loc[now] - loc[left]), fuel[left])});
                left--;
            }

            if (right < n && loc[right] - loc[now] <= power) {
                result.add(right + 1);
                q.offer(new int[]{right, Math.max(power - (loc[right] - loc[now]), fuel[right])});
                right++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int car : result) {
            sb.append(car).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}