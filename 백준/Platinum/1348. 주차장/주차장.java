import java.io.*;
import java.util.*;

public class Main {

    // 전역 변수
    static int ROW, COL;                  // 지도 크기
    static char[][] MAP;                  // 입력 지도
    static Map<Integer, Map<Integer, Integer>> distanceGraph = new HashMap<>(); // C 위치별로 P까지 거리 저장
    static Set<Integer> visited;          // DFS 방문 체크
    static Map<Integer, Integer> match;   // 매칭 결과 저장 (P → C)
    
    // 상하좌우 이동 방향
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ROW = Integer.parseInt(st.nextToken());
        COL = Integer.parseInt(st.nextToken());
        MAP = new char[ROW][COL];

        for (int i = 0; i < ROW; i++) {
            MAP[i] = br.readLine().toCharArray();
        }

        // Step 1️⃣ : 각 'C'에서 BFS 실행 → 각 'P'까지의 거리 저장
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (MAP[i][j] == 'C') {
                    distanceGraph.put(i * COL + j, bfsFromCleaner(i, j));
                }
            }
        }

        // 'C'가 없는 경우
        if (distanceGraph.isEmpty()) {
            System.out.println(0);
            return;
        }

        // Step 2️⃣ : 이진 탐색으로 최소 거리 찾기
        int left = 1, right = ROW * COL;
        int answer = -1;

        while (left < right) {
            int mid = (left + right) / 2;

            match = new HashMap<>();
            if (canAllCleanersBeMatched(mid)) {
                // 모든 'C'가 거리 mid 이하로 매칭 가능 → 더 작은 거리 탐색
                answer = mid;
                right = mid;
            } else {
                // 불가능 → 거리 늘려야 함
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    /**
     * BFS : 특정 'C'(청소기)에서 출발하여 모든 'P'(더러운 곳)까지 최소 거리 계산
     */
    static Map<Integer, Integer> bfsFromCleaner(int startX, int startY) {
        Map<Integer, Integer> foundP = new HashMap<>();
        int[][] dist = new int[ROW][COL];
        for (int[] row : dist) Arrays.fill(row, -1);

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY, 0});
        dist[startX][startY] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], d = cur[2];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (nx < 0 || nx >= ROW || ny < 0 || ny >= COL) continue;
                if (dist[nx][ny] != -1) continue;
                if (MAP[nx][ny] == 'X') continue; // 벽

                dist[nx][ny] = d + 1;
                q.add(new int[]{nx, ny, d + 1});

                // 'P' 발견 시 결과에 기록
                if (MAP[nx][ny] == 'P') {
                    foundP.put(nx * COL + ny, d + 1);
                }
            }
        }

        return foundP;
    }

    /**
     * 현재 거리 제한(limit)으로 모든 'C'가 'P'에 매칭 가능한지 검사
     */
    static boolean canAllCleanersBeMatched(int limit) {
        for (int cleaner : distanceGraph.keySet()) {
            visited = new HashSet<>();
            if (!tryMatch(cleaner, limit)) {
                return false; // 한 명이라도 매칭 실패 시 전체 불가능
            }
        }
        return true;
    }

    /**
     * DFS 기반 이분매칭 (현재 cleaner를 매칭 시도)
     */
    static boolean tryMatch(int cleaner, int limit) {
        if (visited.contains(cleaner)) return false;
        visited.add(cleaner);

        for (Map.Entry<Integer, Integer> entry : distanceGraph.get(cleaner).entrySet()) {
            int dirtySpot = entry.getKey();  // P 위치
            int dist = entry.getValue();     // C → P 거리

            if (dist > limit) continue; // 제한 거리 초과 시 불가

            // 이미 매칭된 P가 없거나, 기존 C를 다른 곳으로 옮길 수 있는 경우
            if (!match.containsKey(dirtySpot) || tryMatch(match.get(dirtySpot), limit)) {
                match.put(dirtySpot, cleaner);
                return true;
            }
        }
        return false;
    }
}
