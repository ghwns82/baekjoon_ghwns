import java.util.*;

public class Solution {
    public static int[] solution(String s) {
        // 1. 바깥 괄호 제거
        s = s.substring(2, s.length() - 2);
        // 2. 각 집합 분리
        String[] parts = s.split("\\},\\{");

        // 3. 길이 기준 정렬을 위해 리스트에 집합을 파싱
        List<List<Integer>> sets = new ArrayList<>();
        for (String part : parts) {
            String[] nums = part.split(",");
            List<Integer> list = new ArrayList<>();
            for (String num : nums) {
                list.add(Integer.parseInt(num));
            }
            sets.add(list);
        }

        // 4. 집합들을 크기순으로 정렬
        sets.sort(Comparator.comparingInt(List::size));

        // 5. 순서대로 중복 없이 튜플 구성
        List<Integer> answer = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();

        for (List<Integer> set : sets) {
            for (int num : set) {
                if (!seen.contains(num)) {
                    seen.add(num);
                    answer.add(num);
                    break;
                }
            }
        }

        // 6. 결과 반환
        return answer.stream().mapToInt(i -> i).toArray();
    }

    // 테스트용 main
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")));   // [2, 1, 3, 4]
        System.out.println(Arrays.toString(solution("{{1,2,3},{2,1},{1,2,4,3},{2}}")));   // [2, 1, 3, 4]
        System.out.println(Arrays.toString(solution("{{20,111},{111}}")));               // [111, 20]
        System.out.println(Arrays.toString(solution("{{123}}")));                        // [123]
        System.out.println(Arrays.toString(solution("{{4,2,3},{3},{2,3,4,1},{2,3}}")));   // [3, 2, 4, 1]
    }
}
