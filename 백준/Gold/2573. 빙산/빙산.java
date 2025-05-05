import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] space;
    static List<int[]> ices = new ArrayList<>();
    static int[][] visited;
    static final int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        space = new int[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], 1);  // visited 초기값 1로 설정 (파이썬과 동일)
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                if (space[i][j] > 0) {
                    ices.add(new int[]{i, j});
                }
            }
        }

        int time = 0;
        boolean flag = true;

        while (!ices.isEmpty()) {
            if (flag) {
                int[] start = ices.get(0);
                int x = start[0], y = start[1];
                visited[x][y] = time;
                List<int[]> queue = new ArrayList<>();
                queue.add(new int[]{x, y});
                int cnt = 1;

                while (!queue.isEmpty()) {
                    int[] cur = queue.remove(queue.size() - 1);  // stack pop
                    int i = cur[0], j = cur[1];

                    for (int[] d : direction) {
                        int nx = i + d[0];
                        int ny = j + d[1];

                        if (nx >= 0 && nx < n && ny >= 0 && ny < m &&
                            space[nx][ny] > 0 && visited[nx][ny] != time) {
                            visited[nx][ny] = time;
                            queue.add(new int[]{nx, ny});
                            cnt++;
                        }
                    }
                }

                if (cnt < ices.size()) {
                    System.out.println(-time);
                    return;
                }
            }

            time--;
            flag = false;

            List<Integer> diff = new ArrayList<>();
            for (int[] ice : ices) {
                int i = ice[0], j = ice[1];
                int water = 0;

                for (int[] d : direction) {
                    int x = i + d[0];
                    int y = j + d[1];

                    if (x >= 0 && x < n && y >= 0 && y < m && space[x][y] == 0) {
                        water++;
                    }
                }

                diff.add(water);
            }

            List<int[]> newIces = new ArrayList<>();
            for (int index = 0; index < ices.size(); index++) {
                int[] ice = ices.get(index);
                int i = ice[0], j = ice[1];
                space[i][j] = Math.max(0, space[i][j] - diff.get(index));

                if (space[i][j] > 0) {
                    newIces.add(new int[]{i, j});
                } else {
                    flag = true;
                }
            }

            ices = newIces;
        }

        System.out.println(0);
    }
}
