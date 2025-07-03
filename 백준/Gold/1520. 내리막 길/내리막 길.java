import java.util.*;

public class Main {
    static int n, m;
    static int[][] maps;
    static boolean[][] visited;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 입력 받기
        n = sc.nextInt();
        m = sc.nextInt();
        maps = new int[n][m];
        visited = new boolean[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                maps[i][j] = sc.nextInt();

        // 시작 위치 초기화
        dp[0][0] = 1;
        visited[0][0] = true;

        // 결과 출력
        System.out.println(search(n - 1, m - 1));
    }

    static int search(int x, int y) {
        int result = 0;

        // 상, 좌, 하, 우
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        // 4방향 탐색
        for (int dir = 0; dir < 4; dir++) {
            int a = x + dx[dir];
            int b = y + dy[dir];

            if (0 <= a && a < n && 0 <= b && b < m && maps[x][y] < maps[a][b]) {
                if (!visited[a][b]) {
                    dp[a][b] = search(a, b);
                    visited[a][b] = true;
                }
                result += dp[a][b];
            }
        }

        return result;
    }
}
