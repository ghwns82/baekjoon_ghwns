import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static boolean[] visited;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 정점 수 입력
        int n = Integer.parseInt(br.readLine());

        // 그래프 초기화
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 입력
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            graph[u].add(v);
            graph[v].add(u);
        }

        // 방문 여부 및 DP 배열 초기화
        visited = new boolean[n];
        dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0; // 얼리 어답터가 아닌 경우
            dp[i][1] = 1; // 얼리 어답터인 경우
        }

        // 재귀 호출로 최소 얼리 어답터 계산
        visited[0] = true;
        recursion(0);

        // 결과 출력
        System.out.println(Math.min(dp[0][0], dp[0][1]));
    }

    private static void recursion(int cur) {
        for (int next : graph[cur]) {
            if (!visited[next]) {
                visited[next] = true;
                recursion(next);
                dp[cur][0] += dp[next][1];
                dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
    }
}