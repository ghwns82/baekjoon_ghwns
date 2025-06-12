import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 BufferedReader 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // n: XOR 연산으로 만들 수 있는 최대 숫자
        int n = Integer.parseInt(br.readLine());

        // m: 주어진 시작 숫자의 개수
        int m = Integer.parseInt(br.readLine());

        // a 배열을 입력받고, 중복된 숫자를 제거한 후 저장 (set 사용)
        Set<Integer> initialSet = new HashSet<>();
        String[] tokens = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            initialSet.add(Integer.parseInt(tokens[i]));
        }

        // 몇 비트까지 XOR 시뮬레이션 할지를 계산
        // 예: n=10이면, 2^4 = 16 > 10 이므로 size는 4 (0~3비트까지 사용)
        int size = 0;
        int num = 1;
        while (n > num) {
            num <<= 1;  // num *= 2
            size++;
        }

        // visited[i]: 숫자 i를 만들기 위해 필요한 최소 연산 횟수 (XOR 단계 수)
        // -1로 초기화: 아직 방문하지 않음
        int[] visited = new int[n + 1];
        Arrays.fill(visited, -1);

        // BFS를 위한 큐 선언 (int[] = {현재 숫자, XOR 횟수})
        Queue<int[]> queue = new LinkedList<>();

        // 시작 숫자들을 큐에 넣고 방문 처리
        for (int i : initialSet) {
            // 중복된 숫자 삽입 방지
            if (visited[i] != 0) {
                queue.offer(new int[]{i, 0});
            }
            visited[i] = 0;  // 시작점은 XOR 0회로 방문 처리
        }

        int safe = 0;  // 마지막으로 계산된 XOR 단계 수 저장

        // BFS 시작
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();  // 큐에서 현재 상태 꺼내기
            int currentNum = cur[0];   // 현재 숫자
            safe = cur[1];             // 현재까지 XOR 연산 횟수

            // 가능한 모든 비트 위치에 대해 XOR 시도
            for (int i = 0; i < size; i++) {
                int d = 1 << i;               // 2^i 비트를 토글
                int next = currentNum ^ d;    // 현재 숫자에서 i번째 비트를 토글한 새로운 숫자

                // n 이하의 수이면서 아직 방문하지 않은 수라면
                if (next <= n && visited[next] == -1) {
                    visited[next] = safe + 1;               // 방문 처리: 현재보다 1단계 더 깊음
                    queue.offer(new int[]{next, safe + 1}); // 큐에 추가하여 다음 탐색 대상으로 설정
                }
            }
        }

        // BFS 종료 후 가장 늦게 방문한 노드의 XOR 횟수 출력
        System.out.println(safe);
    }
}
