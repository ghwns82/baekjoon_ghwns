import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        double dist;
        int u, v;

        public Edge(double dist, int u, int v) {
            this.dist = dist;
            this.u = u;
            this.v = v;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.dist, o.dist);
        }
    }

    static int[] parent;

    // Find with path compression
    static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    // Union
    static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px != py) {
            parent[py] = px;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double[][] stars = new double[n][2];

        for (int i = 0; i < n; i++) {
            stars[i][0] = sc.nextDouble(); // x
            stars[i][1] = sc.nextDouble(); // y
        }

        List<Edge> edges = new ArrayList<>();

        // 모든 별들 쌍에 대해 거리 계산
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dx = stars[i][0] - stars[j][0];
                double dy = stars[i][1] - stars[j][1];
                double dist = Math.sqrt(dx * dx + dy * dy);
                edges.add(new Edge(dist, i, j));
            }
        }

        // 거리 기준 정렬
        Collections.sort(edges);

        // Union-Find 초기화
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        double result = 0;
        for (Edge e : edges) {
            if (find(e.u) != find(e.v)) {
                union(e.u, e.v);
                result += e.dist;
            }
        }

        System.out.printf("%.2f\n", result);
    }
}
