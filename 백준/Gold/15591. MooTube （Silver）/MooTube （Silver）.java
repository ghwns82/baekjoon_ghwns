import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to, weight;
        Edge(int t, int w) {
            this.to = t;
            this.weight = w;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력을 BufferedReader + StringTokenizer로 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        List<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 입력
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        StringBuilder sb = new StringBuilder();

        // 쿼리 처리
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            boolean[] visited = new boolean[n + 1];
            ArrayDeque<Integer> dq = new ArrayDeque<>();

            visited[v] = true;
            dq.add(v);
            int cnt = 0;

            while (!dq.isEmpty()) {
                int cur = dq.poll();
                for (Edge e : graph[cur]) {
                    if (!visited[e.to] && e.weight >= k) {
                        visited[e.to] = true;
                        cnt++;
                        dq.add(e.to);
                    }
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.print(sb.toString());
    }
}
