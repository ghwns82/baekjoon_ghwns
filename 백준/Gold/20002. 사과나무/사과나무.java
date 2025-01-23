import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 과수원의 크기 입력
        int n = Integer.parseInt(br.readLine());
        int[][] land = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                land[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 누적합 배열 계산
        int[][] ns = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                ns[i][j] = ns[i][j - 1] + ns[i - 1][j] - ns[i - 1][j - 1] + land[i - 1][j - 1];
            }
        }

        // 최대값 계산
        int maxValue = land[0][0];
        for (int k = 1; k <= n; k++) {
            for (int i = 0; i <= n - k; i++) {
                for (int j = 0; j <= n - k; j++) {
                    int v = ns[i + k][j + k] + ns[i][j] - ns[i + k][j] - ns[i][j + k];
                    maxValue = Math.max(maxValue, v);
                }
            }
        }

        // 결과 출력
        System.out.println(maxValue);
    }
}