import java.util.*;

public class Solution {
    public int[] solution(int n, int[] info) {
        int[] reversedInfo = new int[11];
        for (int i = 0; i < 11; i++) {
            reversedInfo[i] = info[10 - i];
        }

        int oppositeScore = 0;
        for (int i = 0; i < 11; i++) {
            if (reversedInfo[i] > 0) {
                oppositeScore += i;
            }
        }

        int[] answer = {-1};
        List<Integer> howToDo = new ArrayList<>();
        int result = 0;

        Stack<int[]> stack = new Stack<>();
        // last_index, remain, used_cnt, my_score, your_score
        stack.push(new int[]{-1, n, 0, 0, oppositeScore});

        while (!stack.isEmpty()) {
            int[] state = stack.pop();
            int lastIndex = state[0];
            int remain = state[1];
            int usedCnt = state[2];
            int myScore = state[3];
            int yourScore = state[4];

            while (lastIndex > -1 && lastIndex < howToDo.size()) {
                howToDo.remove(howToDo.size() - 1);
            }

            if (lastIndex == howToDo.size()) {
                howToDo.add(usedCnt);
            }

            int nextIndex = lastIndex + 1;
            if (nextIndex > 10 || remain == 0) {
                if (myScore - yourScore > result) {
                    result = myScore - yourScore;
                    answer = new int[11];
                    for (int i = 0; i < howToDo.size(); i++) {
                        answer[i] = howToDo.get(i);
                    }
                    for (int i = howToDo.size(); i < 11; i++) {
                        answer[i] = 0;
                    }
                }
                continue;
            }

            for (int use = 0; use <= remain; use++) {
                int nextMyScore = myScore;
                int nextYourScore = yourScore;
                if (reversedInfo[nextIndex] < use) {
                    nextMyScore += nextIndex;
                    if (reversedInfo[nextIndex] > 0) {
                        nextYourScore -= nextIndex;
                    }
                }
                stack.push(new int[]{nextIndex, remain - use, use, nextMyScore, nextYourScore});
            }
        }

        // reverse the answer array to match the original Python return
        for (int i = 0; i < answer.length / 2; i++) {
            int temp = answer[i];
            answer[i] = answer[10 - i];
            answer[10 - i] = temp;
        }

        return answer;
    }
}
