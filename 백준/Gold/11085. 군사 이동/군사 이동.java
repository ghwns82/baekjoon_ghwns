import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int p = sc.nextInt(); // 정점 수
        int w = sc.nextInt(); // 간선 수
        int c = sc.nextInt(); // 시작 정점
        int v = sc.nextInt(); // 도착 정점

        List<int[]> roads = new ArrayList<>();
        for (int i = 0; i < w; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();
            roads.add(new int[]{a, b, cost});
        }

        // 간선들을 비용 기준 내림차순 정렬
        roads.sort((a, b) -> b[2] - a[2]);

        // 유니온-파인드 초기화
        parent = new int[p];
        for (int i = 0; i < p; i++) {
            parent[i] = i;
        }

        // 최대 신장 트리를 구성하면서 두 노드가 연결되면 종료
        for (int[] edge : roads) {
            int a = edge[0];
            int b = edge[1];
            int cost = edge[2];

            union(a, b);
            if (find(c) == find(v)) {
                System.out.println(cost);
                break;
            }
        }
    }

    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[Math.max(rootA, rootB)] = Math.min(rootA, rootB);
        }
    }
}
