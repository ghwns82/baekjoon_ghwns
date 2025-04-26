import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static String[] space;
    static int[] parent, rank;
    static int[][] result;
    static int[][] direction = {{0,1},{1,0},{-1,0},{0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        space = new String[n];
        for (int i = 0; i < n; i++) {
            space[i] = br.readLine();
        }

        parent = new int[n * m];
        rank = new int[n * m];
        for (int i = 0; i < n * m; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (space[i].charAt(j) == '1') continue;
                int index = i * m + j;
                if (j > 0 && space[i].charAt(j - 1) == '0') {
                    union(index - 1, index);
                }
                if (i > 0 && space[i-1].charAt(j) == '0') {
                    union(index - m, index);
                }
            }
        }

        result = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (space[i].charAt(j) == '0') continue;
                result[i][j]++;
                HashSet<Integer> visited = new HashSet<>();
                for (int[] d : direction) {
                    int dx = i + d[0];
                    int dy = j + d[1];
                    if (0 <= dx && dx < n && 0 <= dy && dy < m && space[dx].charAt(dy) == '0') {
                        int index = dx * m + dy;
                        int pkey = find(index);
                        if (visited.contains(pkey)) continue;
                        visited.add(pkey);
                        result[i][j] += rank[pkey];
                    }
                }
                result[i][j] %= 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int[] row : result) {
            for (int val : row) {
                sb.append(val);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static int find(int x) {
        if (parent[x] != x) {
            int root = find(parent[x]);
            rank[root] += rank[x];
            rank[x] = 0;
            parent[x] = root;
        }
        return parent[x];
    }

    static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) return;
        int x = Math.min(ra, rb);
        int y = Math.max(ra, rb);
        parent[y] = x;
        find(a);
        find(b);
    }
}
