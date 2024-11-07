import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()); // 논의 개수
        PriorityQueue<Edge> wellQueue = new PriorityQueue<>((a, b) -> a.cost - b.cost); // 우물 비용 우선순위 큐
        
        // 우물 설치 비용을 가상의 노드와 연결된 간선으로 추가
        for (int i = 0; i < n; i++) {
            int cost = Integer.parseInt(br.readLine());
            wellQueue.offer(new Edge(i, i, cost));
        }
        
        // 논 간의 연결 비용 그래프 초기화
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // Prim 알고리즘을 위한 방문 처리와 결과 변수 초기화
        boolean[] visited = new boolean[n];
        int cnt = 0, result = 0;
        
        // MST 생성 과정
        while (cnt < n) {
            Edge current;
            
            // 우물 비용 큐와 논 연결 큐에서 가장 작은 비용 간선 선택
            if (!wellQueue.isEmpty() && (wellQueue.peek().cost <= Integer.MAX_VALUE)) {
                current = wellQueue.poll();
            } else {
                break;
            }
            
            if (visited[current.node]) continue;
            
            visited[current.node] = true;
            result += current.cost;
            cnt++;
            
            // 현재 노드와 연결된 모든 다른 노드 간선 추가
            for (int i = 0; i < n; i++) {
                if (i != current.node && !visited[i]) {
                    wellQueue.offer(new Edge(current.node, i, graph[current.node][i]));
                }
            }
        }
        
        System.out.println(result);
    }
}

// 간선 클래스를 정의하여 노드와 비용을 저장
class Edge {
    int start;
    int node;
    int cost;
    
    public Edge(int start, int node, int cost) {
        this.start = start;
        this.node = node;
        this.cost = cost;
    }
}