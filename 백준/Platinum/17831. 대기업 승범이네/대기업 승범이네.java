import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp;
    static List<Integer>[] children;
    static int[] sng;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 직원 수 입력
        int n = Integer.parseInt(br.readLine());

        // 부모 관계 입력
        st = new StringTokenizer(br.readLine());
        children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int p = Integer.parseInt(st.nextToken()) - 1;
            children[p].add(i + 1);
        }

        // 실력 입력
        st = new StringTokenizer(br.readLine());
        sng = new int[n];
        for (int i = 0; i < n; i++) {
            sng[i] = Integer.parseInt(st.nextToken());
        }

        // DP 배열 초기화
        dp = new int[n][2];

        // 재귀 호출로 최대 시너지 계산
        recursion(0);

        // 결과 출력
        System.out.println(Math.max(dp[0][0], dp[0][1]));
    }

    private static void recursion(int cur) {
        for (int c : children[cur]) {
            recursion(c);
            dp[cur][0] += Math.max(dp[c][0], dp[c][1]);
        }

        for (int c : children[cur]) {
            int v;
            if (dp[c][0] < dp[c][1]) {
                v = dp[cur][0] - dp[c][1] + dp[c][0] + sng[c] * sng[cur];
            } else {
                v = dp[cur][0] + sng[c] * sng[cur];
            }
            dp[cur][1] = Math.max(dp[cur][1], v);
        }
    }
}