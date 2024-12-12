import java.util.*;
import java.io.*;

public class Main {
    static int n, h, d;
    static char[][] grid;
    static List<int[]> nodes = new ArrayList<>();
    static int startIdx, endIdx;
    static int[][] distances;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        grid = new char[n][n];

        // 입력 처리 및 노드 저장
        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    startIdx = nodes.size();
                    nodes.add(new int[]{i, j});
                } else if (grid[i][j] == 'E') {
                    endIdx = nodes.size();
                    nodes.add(new int[]{i, j});
                } else if (grid[i][j] == 'U') {
                    nodes.add(new int[]{i, j});
                }
            }
        }

        // 노드 간 맨해튼 거리 계산
        int numNodes = nodes.size();
        distances = new int[numNodes][numNodes];

        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (i != j) {
                    distances[i][j] = manhattanDistance(nodes.get(i), nodes.get(j));
                }
            }
        }

        // 다익스트라 실행 및 결과 출력
        System.out.println(dijkstra());
    }

    // 맨해튼 거리 계산 함수
    static int manhattanDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    // 다익스트라 알고리즘
    static int dijkstra() {
        PriorityQueue<State> pq = new PriorityQueue<>();
        Map<StateKey, Integer> visited = new HashMap<>();

        pq.add(new State(0, startIdx, h, 0));

        while (!pq.isEmpty()) {
            State current = pq.poll();

            int moves = current.moves;
            int currentNode = current.currentNode;
            int health = current.health;
            int durability = current.durability;

            // 안전지대에 도착하면 최소 이동 횟수 반환
            if (currentNode == endIdx) {
                return moves;
            }

            // 이미 방문한 상태인지 확인
            StateKey stateKey = new StateKey(currentNode, health, durability);
            if (visited.containsKey(stateKey) && visited.get(stateKey) <= moves) {
                continue;
            }
            visited.put(stateKey, moves);

            // 다른 모든 노드로 이동 시도
            for (int nextNode = 0; nextNode < nodes.size(); nextNode++) {
                if (nextNode == currentNode) {
                    continue;
                }

                int distance = distances[currentNode][nextNode];
                int newHealth, newDurability;

                // 시작점에서는 우산 없이 이동
                if (currentNode == startIdx) {
                    if (health < distance) {
                        continue;
                    }
                    newHealth = health - distance;
                    newDurability = 0;
                } else {
                    // 우산 내구도 + 체력으로 이동 가능 여부 확인
                    if (health + durability < distance) {
                        continue;
                    }

                    // 내구도 소진 여부 확인
                    if (durability >= distance) {
                        newHealth = health;
                        newDurability = durability - distance;
                    } else {
                        newHealth = health - (distance - durability);
                        newDurability = 0;
                    }
                }

                // 체력이 0 미만이면 이동 불가
                if (newHealth < 0) {
                    continue;
                }

                // 우산을 획득하면 내구도 초기화
                if (grid[nodes.get(nextNode)[0]][nodes.get(nextNode)[1]] == 'U') {
                    newDurability = d;
                }

                // 우선순위 큐에 새로운 상태 추가
                pq.add(new State(moves + distance, nextNode, newHealth, newDurability));
            }
        }

        // 안전지대에 도달하지 못한 경우
        return -1;
    }

    // 상태 클래스
    static class State implements Comparable<State> {
        int moves;
        int currentNode;
        int health;
        int durability;

        public State(int moves, int currentNode, int health, int durability) {
            this.moves = moves;
            this.currentNode = currentNode;
            this.health = health;
            this.durability = durability;
        }

        @Override
        public int compareTo(State o) {
            return this.moves - o.moves;
        }
    }

    // 상태 키 클래스
    static class StateKey {
        int currentNode;
        int health;
        int durability;

        public StateKey(int currentNode, int health, int durability) {
            this.currentNode = currentNode;
            this.health = health;
            this.durability = durability;
        }

        @Override
        public int hashCode() {
            return Objects.hash(currentNode, health, durability);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            StateKey other = (StateKey) obj;
            return currentNode == other.currentNode && health == other.health && durability == other.durability;
        }
    }
}