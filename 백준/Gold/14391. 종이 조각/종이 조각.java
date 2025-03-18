import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static String[] table;
    static int[][] vh;
    static int maxv = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        table = new String[n];
        for (int i = 0; i < n; i++) {
            table[i] = br.readLine();
        }

        vh = new int[n][m];
        make(0, 0);
        System.out.println(maxv);
    }

    static void make(int x, int y) {
        if (x == n) {
            int[][] visited = new int[n][m];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (visited[i][j] == 0) {
                        StringBuilder num = new StringBuilder();
                        int cx = i, cy = j;
                        int flag = vh[i][j];
                        while (true) {
                            visited[cx][cy] = 1;
                            num.append(table[cx].charAt(cy));
                            if (flag == 1) {
                                cx += 1;
                            } else {
                                cy += 1;
                            }
                            if (cx == n || cy == m || flag != vh[Math.min(cx, n - 1)][Math.min(cy, m - 1)]) {
                                break;
                            }
                        }
                        sum += Integer.parseInt(num.toString());
                    }
                }
            }
            maxv = Math.max(maxv, sum);
        } else {
            int nx = x + (y + 1) / m;
            int ny = (y + 1) % m;
            vh[x][y] = 0;
            make(nx, ny);
            vh[x][y] = 1;
            make(nx, ny);
        }
    }
}
