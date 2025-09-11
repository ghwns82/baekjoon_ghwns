public class Solution {
    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        int dis = Math.abs(x - r) + Math.abs(y - c);
        if (dis % 2 != k % 2 || dis > k) {
            return "impossible";
        }

        StringBuilder answer = new StringBuilder();
        int d = Math.max(0, r - x);
        int l = Math.max(0, y - c);
        int rr = Math.max(0, c - y); // r과 변수명이 겹쳐서 rr로 변경
        int u = Math.max(0, x - r);
        int o = (k - dis) / 2;

        for (int i = 0; i < k; i++) {
            if (x < n && d > 0) {
                answer.append('d');
                x++;
                d--;
                continue;
            }
            if (x < n && o > 0) {
                answer.append('d');
                x++;
                u++;
                o--;
                continue;
            }

            if (y > 1 && o > 0) {
                answer.append('l');
                y--;
                rr++;
                o--;
                continue;
            }
            if (y > 1 && l > 0) {
                answer.append('l');
                y--;
                l--;
                continue;
            }

            if (y < m && rr > 0) {
                answer.append('r');
                y++;
                rr--;
                continue;
            }
            if (y < m && o > 0) {
                answer.append('r');
                y++;
                l++;
                o--;
                continue;
            }

            if (x > 1 && u > 0) {
                answer.append('u');
                x--;
                u--;
                continue;
            }
            if (x > 1 && o > 0) {
                answer.append('u');
                x--;
                d++;
                o--;
                continue;
            }
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        // 예시 실행
        System.out.println(solution(3, 4, 2, 3, 3, 1, 5));
    }
}
