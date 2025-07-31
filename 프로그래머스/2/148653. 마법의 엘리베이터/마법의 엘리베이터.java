import java.util.*;

public class Solution {
    public static int solution(int storey) {
        Deque<int[]> work = new ArrayDeque<>();
        work.add(new int[]{storey, 0});
        int result = 100_000_000;

        while (!work.isEmpty()) {
            int[] curState = work.pollFirst();
            int cur = curState[0];
            int cnt = curState[1];

            // 뒤에 0 제거
            while (cur > 0 && cur % 10 == 0) {
                cur /= 10;
            }

            if (cur == 0) {
                result = Math.min(result, cnt);
            } else if (cur == 1) {
                result = Math.min(result, cnt + 1);
            } else {
                int digit = cur % 10;
                work.add(new int[]{cur / 10, cnt + digit});
                work.add(new int[]{(cur / 10) + 1, cnt + (10 - digit)});
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int storey = 2554;
        System.out.println(solution(storey));
    }
}
