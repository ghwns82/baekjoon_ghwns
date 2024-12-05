import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 첫 줄 입력 처리
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        // 점들 입력 받기
        Set<Point> arr = new HashSet<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr.add(new Point(x, y));
        }

        // BFS 초기화
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0, 0, 0)); // 시작점 (0, 0), 이동 횟수 0

        while (!queue.isEmpty()) {
            State current = queue.poll();
            int a = current.x;
            int b = current.y;
            int c = current.moves;

            Set<Point> tmp = new HashSet<>();
            for (int x = a - 2; x <= a + 2; x++) {
                for (int y = b - 2; y <= b + 2; y++) {
                    Point p = new Point(x, y);
                    if (!arr.contains(p)) continue;

                    if (y == t) {
                        System.out.println(c + 1); // 정상 도달
                        return;
                    }

                    queue.add(new State(x, y, c + 1));
                    tmp.add(p);
                }
            }
            arr.removeAll(tmp); // 방문한 점 제거
        }

        System.out.println(-1); // 정상에 도달할 수 없음
    }

    // BFS 상태 클래스
    static class State {
        int x, y, moves;

        State(int x, int y, int moves) {
            this.x = x;
            this.y = y;
            this.moves = moves;
        }
    }

    // 점 클래스
    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}