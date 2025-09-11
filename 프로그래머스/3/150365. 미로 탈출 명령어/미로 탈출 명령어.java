import java.util.*;

public class Solution {
    static StringBuilder result = new StringBuilder();
    static String answer;

    static void dfs(int d, int l, int r, int u, int o,
                    int x, int y, int n, int m, int k) {
        if (d == 0 && l == 0 && r == 0 && u == 0 && o == 0) {
            String cur = result.toString();
            if (cur.compareTo(answer) < 0) {
                answer = cur;
            }
            return;
        }

        // down
        if (x < n && result.length() < k) {
            result.append('d');
            if (d > 0) dfs(d - 1, l, r, u, o, x + 1, y, n, m, k);
            if (o > 0) dfs(d, l, r, u + 1, o - 1, x + 1, y, n, m, k);
            result.deleteCharAt(result.length() - 1);
        }

        // up
        if (x > 1 && result.length() < k) {
            result.append('u');
            if (u > 0) dfs(d, l, r, u - 1, o, x - 1, y, n, m, k);
            if (o > 0) dfs(d + 1, l, r, u, o - 1, x - 1, y, n, m, k);
            result.deleteCharAt(result.length() - 1);
        }

        // left
        if (y > 1 && result.length() < k) {
            result.append('l');
            if (l > 0) dfs(d, l - 1, r, u, o, x, y - 1, n, m, k);
            if (o > 0) dfs(d, l, r + 1, u, o - 1, x, y - 1, n, m, k);
            result.deleteCharAt(result.length() - 1);
        }

        // right
        if (y < m && result.length() < k) {
            result.append('r');
            if (r > 0) dfs(d, l, r - 1, u, o, x, y + 1, n, m, k);
            if (o > 0) dfs(d, l + 1, r, u, o - 1, x, y + 1, n, m, k);
            result.deleteCharAt(result.length() - 1);
        }
    }

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        int dis = Math.abs(x - r) + Math.abs(y - c);
        if (dis % 2 != k % 2 || dis > k) {
            return "impossible";
        }

        answer = "z".repeat(k);

        int d = Math.max(0, r - x);
        int u = Math.max(0, x - r);
        int l = Math.max(0, y - c);
        int rCount = Math.max(0, c - y);
        int o = (k - dis) / 2;

        dfs(d, l, rCount, u, o, x, y, n, m, k);

        return answer;
    }

    public static void main(String[] args) {
        // 예시 실행
        System.out.println(solution(3, 4, 2, 3, 3, 1, 5));
    }
}
