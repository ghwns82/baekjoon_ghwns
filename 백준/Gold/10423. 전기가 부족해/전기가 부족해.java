import java.util.*;

class Edge implements Comparable<Edge> {
    int cost;
    int from;
    int to;

    public Edge(int cost, int from, int to) {
        this.cost = cost;
        this.from = from;
        this.to = to;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.cost, other.cost);
    }
}

public class Main {
    public static int primMST(List<List<Edge>> graph, List<Integer> plants, int n) {
        int[] weight = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Edge> work = new PriorityQueue<>();

        // 여러 발전소에서 시작하는 초기화
        for (int start : plants) {
            visited[start] = true;
            for (Edge edge : graph.get(start)) {
                work.add(new Edge(edge.cost, start, edge.to));
            }
        }

        int totalCost = 0;
        // MST 구성을 위한 반복
        while (!work.isEmpty()) {
            Edge edge = work.poll();
            if (!visited[edge.to]) {
                visited[edge.to] = true;
                weight[edge.to] += edge.cost;
                totalCost += edge.cost;

                for (Edge nextEdge : graph.get(edge.to)) {
                    if (!visited[nextEdge.to]) {
                        work.add(new Edge(nextEdge.cost, edge.to, nextEdge.to));
                    }
                }
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 도시의 수
        int m = scanner.nextInt(); // 도로의 수
        int k = scanner.nextInt(); // 발전소의 수

        // 발전소 리스트 입력 받기
        List<Integer> plants = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            plants.add(scanner.nextInt());
        }

        // 그래프 초기화
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 도로 정보 입력 받기
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            graph.get(u).add(new Edge(w, u, v));
            graph.get(v).add(new Edge(w, v, u));
        }

        // MST 비용 출력
        System.out.println(primMST(graph, plants, n));
        scanner.close();
    }
}