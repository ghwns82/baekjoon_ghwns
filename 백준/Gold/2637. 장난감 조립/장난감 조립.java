import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        int[] pre = new int[n + 1];
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            graph.get(x).add(new int[]{y, k});
            pre[y]++;
        }

        dp[n] = 1;
        Queue<Integer> work = new LinkedList<>();
        work.offer(n);

        while (!work.isEmpty()) {
            int node = work.poll();
            for (int[] next : graph.get(node)) {
                int nextNode = next[0];
                int count = next[1];
                dp[nextNode] += dp[node] * count;
                pre[nextNode]--;
                if (pre[nextNode] == 0) {
                    work.offer(nextNode);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++) {
            if (graph.get(i).isEmpty()) {
                sb.append(i).append(" ").append(dp[i]).append("\n");
            }
        }

        System.out.print(sb);
    }
}