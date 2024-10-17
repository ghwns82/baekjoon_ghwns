import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        int n = sc.nextInt();  // 동전의 종류 수
        int k = sc.nextInt();  // 목표 금액

        int[] coins = new int[n];  // 동전의 종류를 저장할 배열
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        // DP 테이블 초기화: 목표 금액을 만들기 위한 최소 동전 개수
        int[] dp = new int[k + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);  // 큰 값으로 초기화
        dp[0] = 0;  // 0원을 만드는 방법은 동전이 필요 없으므로 0

        // 동전 교환 문제를 해결하는 동적 계획법
        for (int coin : coins) {
            for (int i = coin; i <= k; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // 결과 출력
        if (dp[k] == Integer.MAX_VALUE) {
            System.out.println(-1);  // 목표 금액을 만들 수 없는 경우
        } else {
            System.out.println(dp[k]);  // 최소 동전 개수 출력
        }

        sc.close();
    }
}