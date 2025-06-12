import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        // 중복 제거된 초기값 목록 생성
        Set<Integer> initialSet = new HashSet<>();
        String[] tokens = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            initialSet.add(Integer.parseInt(tokens[i]));
        }

        // size 계산 (몇 비트까지 필요한지)
        int size = 0;
        int num = 1;
        while (n > num) {
            num <<= 1;
            size++;
        }

        // 방문 여부 및 BFS 준비
        int[] visited = new int[n + 1];
        Arrays.fill(visited, -1);
        Queue<int[]> queue = new LinkedList<>();

        for (int i : initialSet) {
            if (visited[i] != 0) {
                queue.offer(new int[]{i, 0});
            }
            visited[i] = 0;
        }

        int safe = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int currentNum = cur[0];
            safe = cur[1];

            for (int i = 0; i < size; i++) {
                int d = 1 << i;
                int next = currentNum ^ d;
                if (next <= n && visited[next] == -1) {
                    visited[next] = safe + 1;
                    queue.offer(new int[]{next, safe + 1});
                }
            }
        }

        System.out.println(safe);
    }
}
