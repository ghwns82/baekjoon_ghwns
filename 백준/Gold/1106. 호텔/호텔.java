import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        int C = sc.nextInt();  // 늘려야 할 최소 고객 수
        int N = sc.nextInt();  // 도시의 수

        int[][] cities = new int[N][2];  // 각 도시의 비용과 고객 수 저장
        for (int i = 0; i < N; i++) {
            cities[i][0] = sc.nextInt();  // 비용
            cities[i][1] = sc.nextInt();  // 늘릴 수 있는 고객 수
        }

        // DP 테이블 초기화: dp[i]는 i명의 고객을 늘리기 위한 최소 비용
        int[] dp = new int[C + 101];  // 충분한 크기로 dp 배열 설정
        Arrays.fill(dp, Integer.MAX_VALUE);  // 모든 값을 큰 값으로 초기화
        dp[0] = 0;  // 고객을 0명 늘리는 데는 비용이 0

        // 동적 계획법으로 최소 비용 계산
        for (int[] city : cities) {
            int cost = city[0];  // 홍보 비용
            int customers = city[1];  // 늘릴 수 있는 고객 수

            // DP 테이블 갱신
            for (int i = customers; i <= C + 100; i++) {
                if (dp[i - customers] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - customers] + cost);
                }
            }
        }

        // 최소 고객 C명을 늘리기 위한 최소 비용 찾기
        int result = Integer.MAX_VALUE;
        for (int i = C; i <= C + 100; i++) {
            result = Math.min(result, dp[i]);
        }

        // 결과 출력
        System.out.println(result);

        sc.close();
    }
}