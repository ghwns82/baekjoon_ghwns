import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int[] parent, rank;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        Edge[] edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            int weight = sc.nextInt();
            edges[i] = new Edge(u, v, weight);
        }

        System.out.println(kruskal(N, edges));
        sc.close();
    }

    public static int kruskal(int N, Edge[] edges) {
        Arrays.sort(edges);  // 간선을 가중치 순으로 정렬
        parent = new int[N];
        rank = new int[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int mstWeight = 0;
        int edgesUsed = 0;

        for (Edge edge : edges) {
            if (find(edge.u) != find(edge.v)) {
                union(edge.u, edge.v);
                mstWeight += edge.weight;
                edgesUsed++;
                if (edgesUsed == N - 1) break;
            }
        }

        return mstWeight;
    }

    public static int find(int u) {
        if (u != parent[u]) {
            parent[u] = find(parent[u]);  // 경로 압축
        }
        return parent[u];
    }

    public static void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU != rootV) {
            if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
        }
    }
}