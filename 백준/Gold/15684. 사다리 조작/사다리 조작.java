import java.io.*;
import java.util.*;

public class Main {
    static int n, m, h;
    static int[][] graph;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        // graph[h][n] : 각 높이마다 가로선을 저장
        graph = new int[h][n];
        for (int i = 0; i < h; i++) {
            Arrays.fill(graph[i], -1);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 파이썬은 0-index 변환, 자바도 동일하게 적용
            graph[a - 1][b - 1] = b;
            graph[a - 1][b] = b - 1;
        }

        for (result = 0; result < 4; result++) {
            dfs(result, 0);
        }
        System.out.println(-1);
    }

    static void check() {
        for (int start = 0; start < n; start++) {
            int cur = start;
            for (int i = 0; i < h; i++) {
                if (graph[i][cur] > -1) {
                    cur = graph[i][cur];
                }
            }
            if (cur != start) {
                return; // 실패
            }
        }
        System.out.println(result);
        System.exit(0); // 정답 찾으면 프로그램 종료
    }

    static void dfs(int depth, int index) {
        if (depth == 0) {
            check();
            return;
        }

        int total = (n - 1) * h;
        for (int i = index; i < total; i++) {
            int x = i / (n - 1);
            int y = i % (n - 1);

            if (graph[x][y] == -1 && graph[x][y + 1] == -1) {
                graph[x][y] = y + 1;
                graph[x][y + 1] = y;

                dfs(depth - 1, i + 1);

                graph[x][y] = -1;
                graph[x][y + 1] = -1;
            }
        }
    }
}
