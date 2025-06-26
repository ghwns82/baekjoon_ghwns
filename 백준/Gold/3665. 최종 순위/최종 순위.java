import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            Set<Integer>[] memo = new Set[n + 1]; // 1-based
            for (int i = 0; i <= n; i++) {
                memo[i] = new HashSet<>();
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            Set<Integer> tmp = new HashSet<>();
            for (int i = 1; i <= n; i++) tmp.add(i);

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // 초기 우선순위 그래프 설정
            for (int i = 0; i < n; i++) {
                int current = arr[i];
                tmp.remove(current);
                memo[current] = new HashSet<>(tmp);
            }

            int m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (memo[b].contains(a)) {
                    memo[b].remove(a);
                    memo[a].add(b);
                } else {
                    memo[a].remove(b);
                    memo[b].add(a);
                }
            }

            // 위상 정렬 복원
            int[] result = new int[n];
            boolean[] used = new boolean[n + 1];
            boolean impossible = false;

            for (int i = 1; i <= n; i++) {
                int size = memo[i].size();
                if (size >= n || result[size] != 0) {
                    impossible = true;
                    break;
                }
                result[size] = i;
            }

            if (impossible) {
                System.out.println("IMPOSSIBLE");
            } else {
                // 출력 (역순)
                StringBuilder sb = new StringBuilder();
                for (int i = n - 1; i >= 0; i--) {
                    sb.append(result[i]).append(" ");
                }
                System.out.println(sb.toString().trim());
            }
        }
    }
}
