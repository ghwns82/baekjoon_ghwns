import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Main {
    static int n;
    static ArrayList<HashSet<Integer>> graph;
    static boolean[] visited;
    static long result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        visited = new boolean[n];
        visited[0] = true;
        dfs(0);

        System.out.println(result);
    }

    static int dfs(int cur) {
        int dots = 1;
        for (int next : graph.get(cur)) {
            if (!visited[next]) {
                visited[next] = true;
                dots += dfs(next);
            }
        }
        if (cur != 0) {
            result += comb(n, 2) - comb(n - dots, 2);
        }
        return dots;
    }

    // nCk 계산 (Python의 math.comb 대체)
    static long comb(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;
        long res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;
        }
        return res;
    }
}
