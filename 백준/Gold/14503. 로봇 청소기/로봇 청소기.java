import java.io.*;
import java.util.*;

public class Main {
    // 방향 벡터 (북, 동, 남, 서)
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] room = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(cleanRoom(N, M, r, c, d, room));
    }

    private static int cleanRoom(int N, int M, int r, int c, int d, int[][] room) {
        int cleanedCount = 0;

        while (true) {
            // 1. 현재 칸이 청소되지 않았다면 청소
            if (room[r][c] == 0) {
                room[r][c] = 2; // 청소 완료 표시
                cleanedCount++;
            }

            // 2. 현재 칸의 주변 4칸 탐색
            boolean found = false;
            for (int i = 0; i < 4; i++) {
                d = (d + 3) % 4; // 반시계 방향으로 90도 회전
                int nx = r + dx[d];
                int ny = c + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && room[nx][ny] == 0) {
                    r = nx;
                    c = ny;
                    found = true;
                    break;
                }
            }

            // 3. 청소되지 않은 빈 칸이 없는 경우
            if (!found) {
                int backDirection = (d + 2) % 4;
                int bx = r + dx[backDirection];
                int by = c + dy[backDirection];

                if (bx >= 0 && bx < N && by >= 0 && by < M && room[bx][by] != 1) {
                    r = bx;
                    c = by;
                } else {
                    break; // 뒤가 벽인 경우 작동 중지
                }
            }
        }

        return cleanedCount;
    }
}