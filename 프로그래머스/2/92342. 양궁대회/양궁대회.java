import java.util.*;

public class Solution {
    public int[] solution(int n, int[] info) {
        // info를 역순으로 저장 (문제 특성상 뒤에서부터 계산하기 위함)
        int[] reversedInfo = new int[11];
        for (int i = 0; i < 11; i++) {
            reversedInfo[i] = info[10 - i];
        }

        // 어피치의 초기 점수 계산
        int oppositeScore = 0;
        for (int i = 0; i < 11; i++) {
            if (reversedInfo[i] > 0) {
                oppositeScore += i;
            }
        }

        // 기본 반환값 초기화 (-1이 기본값)
        int[] answer = {-1};

        // 현재까지 사용한 화살 기록 리스트 (각 점수에 몇 발 쐈는지 저장)
        List<Integer> howToDo = new ArrayList<>();

        // 현재 최대 점수 차이를 저장할 변수
        int result = 0;

        // 스택 초기화: {last_index, remain, used_cnt, my_score, your_score}
        // last_index: 마지막으로 선택한 인덱스(과녁칸)
        // remain: 남은 화살 수
        // used_cnt: 현재 인덱스에서 사용한 화살 수
        // my_score: 현재까지 나의 점수
        // your_score: 현재까지 어피치 점수
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{-1, n, 0, 0, oppositeScore}); // 초기 상태 삽입

        // DFS 방식으로 모든 경우 탐색
        while (!stack.isEmpty()) {
            int[] state = stack.pop();
            int lastIndex = state[0];
            int remain = state[1];
            int usedCnt = state[2];
            int myScore = state[3];
            int yourScore = state[4];

            // 만약 lastIndex가 howToDo 범위 내에 있으면 기록 삭제 (백트래킹)
            while (lastIndex > -1 && lastIndex < howToDo.size()) {
                howToDo.remove(howToDo.size() - 1);
            }

            // 사용한 화살 수 기록
            if (lastIndex == howToDo.size()) {
                howToDo.add(usedCnt);
            }

            int nextIndex = lastIndex + 1;

            // base case: 모든 점수 구간을 다 봤거나, 화살이 다 떨어진 경우
            if (nextIndex > 10 || remain == 0) {
                // 점수 차이가 기존보다 크면 결과 갱신
                if (myScore - yourScore > result) {
                    result = myScore - yourScore;
                    answer = new int[11];
                    for (int i = 0; i < howToDo.size(); i++) {
                        answer[i] = howToDo.get(i);
                    }
                }
                continue;
            }

            // 현재 nextIndex에서 사용할 수 있는 화살 수 경우의 수 모두 탐색
            for (int use = 0; use <= remain; use++) {
                int nextMyScore = myScore;
                int nextYourScore = yourScore;

                // 나의 화살 수가 어피치보다 많으면 점수 획득
                if (reversedInfo[nextIndex] < use) {
                    nextMyScore += nextIndex;
                    // 어피치가 해당 구간에 쏜 화살이 있었다면 점수를 빼줌
                    if (reversedInfo[nextIndex] > 0) {
                        nextYourScore -= nextIndex;
                    }
                }

                // 다음 상태를 스택에 추가
                stack.push(new int[]{nextIndex, remain - use, use, nextMyScore, nextYourScore});
            }
        }

        // 결과를 원래 순서로 되돌림 (reversedInfo 사용했으므로)
        for (int i = 0; i < answer.length / 2; i++) {
            int temp = answer[i];
            answer[i] = answer[10 - i];
            answer[10 - i] = temp;
        }

        return answer;
    }
}
