import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 동전의 종류 수
        int k = Integer.parseInt(st.nextToken()); // 목표 금액

        int[] coin = new int[n]; // 동전 금액 배열
        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k + 1]; // dp[i]: i원을 만들 수 있는 경우의 수
        dp[0] = 1; // 0원을 만드는 방법은 '아무것도 선택하지 않는 1가지'뿐

        // 각 동전 금액에 대해 가능한 경우의 수 갱신
        for (int c : coin) {
            for (int i = c; i <= k; i++) {
                // i원을 만들기 위해, 이전에 i-c원을 만들 수 있는 경우의 수를 더함
                dp[i] += dp[i - c];
            }
        }

        // k원을 만들 수 있는 총 경우의 수 출력
        System.out.println(dp[k]);
    }
}
