import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	private static StringBuilder output = new StringBuilder();

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		int n = Integer.parseInt(tokens.nextToken());
		char[][] house = new char[n][n];
		for (int i = 0; i < n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < house.length; j++) {
				house[i][j] = tokens.nextToken().charAt(0);
			}
		}

		 BigInteger[][][] dp = new BigInteger[n][n][3];

	        // 배열을 0으로 초기화
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                dp[i][j][0] = BigInteger.ZERO;
	                dp[i][j][1] = BigInteger.ZERO;
	                dp[i][j][2] = BigInteger.ZERO;
	            }
	        }

	        // 도착지점에 벽이 있으면 0 출력 후 종료
	        if (house[n - 1][n - 1] == '1') {
	            System.out.println(0);
	            System.exit(0);
	        }

	        // 시작 조건 설정
	        dp[0][1][0] = BigInteger.ONE;

	        // DP 배열을 채우는 루프
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                if (j - 1 >= 0 && house[i][j - 1] == '0') {
	                    dp[i][j][0] = dp[i][j][0].add(dp[i][j - 1][0]).add(dp[i][j - 1][1]);
	                }
	                if (i - 1 >= 0 && house[i - 1][j] == '0') {
	                    dp[i][j][2] = dp[i][j][2].add(dp[i - 1][j][2]).add(dp[i - 1][j][1]);
	                }
	                if (i - 1 >= 0 && j - 1 >= 0 && house[i][j - 1] != '1' && house[i - 1][j] != '1' && house[i - 1][j - 1] != '1') {
	                    for (int k = 0; k < 3; k++) {
	                        dp[i][j][1] = dp[i][j][1].add(dp[i - 1][j - 1][k]);
	                    }
	                }
	            }
	        }

	        // 결과 출력
	        BigInteger result = dp[n - 1][n - 1][0]
	                            .add(dp[n - 1][n - 1][1])
	                            .add(dp[n - 1][n - 1][2]);

	        System.out.println(result);

	}
}