import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
    static Set<Integer> visited;
    static Map<Integer, Integer> selected;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // BFS로 각 C의 거리 그래프 생성
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'C') {
                    graph.put(i * C + j, bfs(i, j));
                }
            }
        }

        if (graph.isEmpty()) {
            System.out.println(0);
            return;
        }

        int left = 1, right = R * C, result = -1;
        while (left < right) {
            int mid = (left + right) / 2;
            visited = new HashSet<>();
            selected = new HashMap<>();

            if (possible(mid)) {
                result = mid;
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(result);
    }

    static Map<Integer, Integer> bfs(int x, int y) {
        Map<Integer, Integer> result = new HashMap<>();
        int[][] dist = new int[R][C];
        for (int[] d : dist) Arrays.fill(d, -1);

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y, 0});
        dist[x][y] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1], cd = cur[2];

            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                if (dist[nx][ny] != -1) continue;
                if (map[nx][ny] == 'X') continue;

                dist[nx][ny] = cd + 1;
                q.add(new int[]{nx, ny, cd + 1});

                if (map[nx][ny] == 'P') {
                    result.put(nx * C + ny, cd + 1);
                }
            }
        }
        return result;
    }

    static boolean possible(int limit) {
        for (int key : graph.keySet()) {
            visited = new HashSet<>();
            if (!bimatch(key, limit)) {
                return false;
            }
        }
        return true;
    }

    static boolean bimatch(int cur, int limit) {
        if (visited.contains(cur)) return false;
        visited.add(cur);

        for (Map.Entry<Integer, Integer> entry : graph.get(cur).entrySet()) {
            int next = entry.getKey();
            int dist = entry.getValue();

            if (dist > limit) continue;

            if (!selected.containsKey(next) || bimatch(selected.get(next), limit)) {
                selected.put(next, cur);
                return true;
            }
        }
        return false;
    }
}
