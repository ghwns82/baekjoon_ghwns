import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[][] miro = new char[r][c];
        int[][] dp = new int[r][c];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> queue = new LinkedList<>();
        int jihunX = -1, jihunY = -1;

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                miro[i][j] = line.charAt(j);
                if (miro[i][j] == 'J') {
                    jihunX = i;
                    jihunY = j;
                } else if (miro[i][j] == 'F') {
                    queue.offer(new int[]{i, j, -1}); // Fire
                }
            }
        }

        queue.offer(new int[]{jihunX, jihunY, 0}); // Jihun
        dp[jihunX][jihunY] = 0;

        // Directions for movement
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // Check if Jihun starts at the edge
        if (jihunX == 0 || jihunX == r - 1 || jihunY == 0 || jihunY == c - 1) {
            System.out.println(1);
            return;
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int time = current[2];

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                    continue; // Out of bounds
                }

                if (miro[nx][ny] == '.') {
                    if (time == -1) { // Fire spreads
                        queue.offer(new int[]{nx, ny, -1});
                        miro[nx][ny] = 'F';
                    } else { // Jihun moves
                        int nextTime = time + 1;

                        if (nx == 0 || nx == r - 1 || ny == 0 || ny == c - 1) {
                            System.out.println(nextTime + 1);
                            return;
                        }

                        if (dp[nx][ny] == -1) {
                            dp[nx][ny] = nextTime;
                            queue.offer(new int[]{nx, ny, nextTime});
                        }
                    }
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}