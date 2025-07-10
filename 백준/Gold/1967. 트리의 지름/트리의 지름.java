import java.util.*;

public class Main {
    static List<List<Integer>> tree;
    static List<List<Integer>> weights;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 노드 수
        tree = new ArrayList<>();
        weights = new ArrayList<>();
        dp = new int[n + 1][0];

        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
            weights.add(new ArrayList<>());
        }

        // 입력 받기
        while (sc.hasNextInt()) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            tree.get(x).add(y);
            weights.get(x).add(z);
        }

        // DP 배열 초기화
        for (int i = 0; i <= n; i++) {
            dp[i] = new int[tree.get(i).size() + 2];  // 두 개 공간 추가
        }

        check(1); // 루트에서 시작

        // 결과 계산
        int maxSum = 0;
        for (int i = 1; i <= n; i++) {
            Arrays.sort(dp[i]);
            int len = dp[i].length;
            int sum = dp[i][len - 1] + dp[i][len - 2];
            maxSum = Math.max(maxSum, sum);
        }

        System.out.println(maxSum);
    }

    static int check(int node) {
        for (int i = 0; i < tree.get(node).size(); i++) {
            int child = tree.get(node).get(i);
            int weight = weights.get(node).get(i);
            dp[node][i + 2] = weight + check(child);
        }
        return Arrays.stream(dp[node]).max().getAsInt();
    }
}
