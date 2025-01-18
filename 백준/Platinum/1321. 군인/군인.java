import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N; // 부대 개수
    private static int M; // 명령 개수

    private static int[] units; // 부대별 인원 수
    private static int[] tree; // 세그먼트 트리 배열

    // 세그먼트 트리 초기화
    private static int init(int start, int end, int index) {
        if (start == end) { // 리프 노드
            tree[index] = units[start];
            return tree[index];
        }

        int mid = (start + end) / 2;
        tree[index] = init(start, mid, index * 2) + init(mid + 1, end, index * 2 + 1);
        return tree[index];
    }

    // 세그먼트 트리 업데이트
    private static void update(int start, int end, int index, int target, int value) {
        if (target < start || target > end) { // 범위 밖
            return;
        }

        tree[index] += value; // 값 갱신
        if (start == end) { // 리프 노드 도달
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, index * 2, target, value);
        update(mid + 1, end, index * 2 + 1, target, value);
    }

    // 군번에 해당하는 부대 찾기
    private static int findUnit(int start, int end, int index, int target) {
        if (start == end) { // 리프 노드에 도달
            return start + 1; // 1-based index
        }

        int mid = (start + end) / 2;
        if (tree[index * 2] >= target) { // 왼쪽 자식 탐색
            return findUnit(start, mid, index * 2, target);
        } else { // 오른쪽 자식 탐색
            return findUnit(mid + 1, end, index * 2 + 1, target - tree[index * 2]);
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine()); // 부대 개수

        units = new int[N];
        tree = new int[N * 4];
        tokens = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            units[i] = Integer.parseInt(tokens.nextToken());
        }

        init(0, N - 1, 1); // 세그먼트 트리 초기화

        M = Integer.parseInt(br.readLine()); // 명령 개수
        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(tokens.nextToken());

            if (cmd == 1) {
                // 부대 인원 변경
                int a = Integer.parseInt(tokens.nextToken()) - 1;
                int b = Integer.parseInt(tokens.nextToken());
                update(0, N - 1, 1, a, b);
            } else {
                // 군번에 해당하는 부대 찾기
                int a = Integer.parseInt(tokens.nextToken());
                sb.append(findUnit(0, N - 1, 1, a)).append("\n");
            }
        }

        System.out.println(sb);
    }
}