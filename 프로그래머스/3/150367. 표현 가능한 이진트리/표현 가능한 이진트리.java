import java.util.*;

public class Solution {
    public List<Integer> solution(long[] numbers) {
        List<Integer> answer = new ArrayList<>();

        for (long number : numbers) {
            // 이진수로 변환 (앞의 "0b" 제거)
            String biNum = Long.toBinaryString(number);

            // unit은 트리 형태를 맞추기 위한 최소 2^n 길이
            int unit = 1;
            while (biNum.length() > unit) {
                unit <<= 1; // unit을 2배씩 늘림
            }

            // 왼쪽에 '0'을 채워서 트리의 완전 이진 트리 형태로 맞춤
            biNum = String.format("%" + unit + "s", biNum).replace(' ', '0');

            int result = 1; // 기본적으로 올바른 트리라고 가정
            Stack<int[]> stack = new Stack<>();
            // index: 현재 부모 노드, len_arm: 자식으로 이동할 거리
            stack.push(new int[]{unit >> 1, unit >> 2});

            while (!stack.isEmpty()) {
                int[] curr = stack.pop();
                int index = curr[0];
                int lenArm = curr[1];

                // 리프 노드는 그냥 통과
                if (lenArm == 0) {
                    continue;
                }

                int left = index - lenArm;
                int right = index + lenArm;

                // 부모 노드가 0인데, 자식 중 하나라도 1이면 올바르지 않은 트리
                if (biNum.charAt(index) == '0' && !(biNum.charAt(left) == '0' && biNum.charAt(right) == '0')) {
                    result = 0;
                    break;
                }

                // 왼쪽, 오른쪽 자식을 스택에 추가
                stack.push(new int[]{left, lenArm >> 1});
                stack.push(new int[]{right, lenArm >> 1});
            }

            answer.add(result);
        }

        return answer;
    }
}
