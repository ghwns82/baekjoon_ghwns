import java.io.*;
import java.util.*;

public class Main {
    static int N, size;
    static double result = 0.0;
    static double probability = 1.0;
    static boolean[][] visited;
    static int curX = 0, curY = 0;

    static class Dir {
        int p, dx, dy;
        Dir(int p, int dx, int dy) {
            this.p = p; this.dx = dx; this.dy = dy;
        }
    }

    static List<Dir> direction = new ArrayList<>();

    static void dfs(int depth) {
        if (depth == N) {
            result += probability;
            return;
        }
        int x = curX, y = curY;
        for (Dir d : direction) {
            int nx = (d.dx + x + size) % size;
            int ny = (d.dy + y + size) % size;
            if (visited[nx][ny]) continue;

            probability *= d.p;
            visited[nx][ny] = true;
            curX = nx; curY = ny;

            dfs(depth + 1);

            probability /= d.p;
            curX = x; curY = y;
            visited[nx][ny] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        if (N == 1 || (e * w == 0 && n * s == 0)) {
            System.out.println(1.0);
            return;
        }

        size = 2 * N + 1;
        visited = new boolean[size][size];
        visited[0][0] = true;

        if (e > 0) direction.add(new Dir(e, 0, 1));
        if (w > 0) direction.add(new Dir(w, 0, -1));
        if (n > 0) direction.add(new Dir(n, 1, 0));
        if (s > 0) direction.add(new Dir(s, -1, 0));

        dfs(0);

        double denom = Math.pow(100, N);
        System.out.println(result / denom);
    }
}
