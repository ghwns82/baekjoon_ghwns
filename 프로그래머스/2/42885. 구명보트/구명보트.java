import java.util.Arrays;

public class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int light = 0;
        int heavy = people.length - 1;
        int answer = 0;

        while (light <= heavy) {
            if (people[light] + people[heavy] <= limit) {
                light++;
            }
            heavy--;
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[]{70, 50, 80, 50}, 100)); // 3
        System.out.println(sol.solution(new int[]{30, 30, 30}, 100));     // 2
    }
}
