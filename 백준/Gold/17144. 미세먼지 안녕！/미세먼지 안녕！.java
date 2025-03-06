import java.io.*;
import java.util.*;

public class Main {
    static int R, C, T;
    static int[][] room;
    static int[] cleaner;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        room = new int[R][C];
        cleaner = new int[2];

        int idx = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1) {
                    cleaner[idx++] = i;
                }
            }
        }

        for (int t = 0; t < T; t++) {
            spreadDust();
            cleanAir();
        }

        System.out.println(getDustAmount());
    }

    static void spreadDust() {
        int[][] newRoom = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] > 0) {
                    int spreadAmount = room[i][j] / 5;
                    int spreadCount = 0;

                    for (int d = 0; d < 4; d++) {
                        int ni = i + dx[d];
                        int nj = j + dy[d];

                        if (ni >= 0 && ni < R && nj >= 0 && nj < C && room[ni][nj] != -1) {
                            newRoom[ni][nj] += spreadAmount;
                            spreadCount++;
                        }
                    }

                    newRoom[i][j] += room[i][j] - (spreadAmount * spreadCount);
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] == -1) continue;
                room[i][j] = newRoom[i][j];
            }
        }
    }

    static void cleanAir() {
        int upper = cleaner[0];
        int lower = cleaner[1];

        for (int i = upper - 1; i > 0; i--) room[i][0] = room[i - 1][0];
        for (int j = 0; j < C - 1; j++) room[0][j] = room[0][j + 1];
        for (int i = 0; i < upper; i++) room[i][C - 1] = room[i + 1][C - 1];
        for (int j = C - 1; j > 1; j--) room[upper][j] = room[upper][j - 1];
        room[upper][1] = 0;

        for (int i = lower + 1; i < R - 1; i++) room[i][0] = room[i + 1][0];
        for (int j = 0; j < C - 1; j++) room[R - 1][j] = room[R - 1][j + 1];
        for (int i = R - 1; i > lower; i--) room[i][C - 1] = room[i - 1][C - 1];
        for (int j = C - 1; j > 1; j--) room[lower][j] = room[lower][j - 1];
        room[lower][1] = 0;
    }

    static int getDustAmount() {
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] > 0) sum += room[i][j];
            }
        }
        return sum;
    }
}
