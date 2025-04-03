import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        // col은 1부터 시작하므로 0-based index로 맞춰줍니다.
        int sortCol = col - 1;

        // 정렬: col번째 컬럼 기준 오름차순, col 값이 같다면 기본키(첫 번째 컬럼) 기준 내림차순
        Arrays.sort(data, (a, b) -> {
            if (a[sortCol] != b[sortCol]) {
                return Integer.compare(a[sortCol], b[sortCol]);
            } else {
                return Integer.compare(b[0], a[0]); // 기본키는 내림차순
            }
        });

        int answer = 0;

        // row_begin ~ row_end 범위의 행에 대해 S_i 계산 후 XOR 누적
        for (int i = row_begin - 1; i <= row_end - 1; i++) {
            int[] row = data[i];
            int si = 0;
            for (int val : row) {
                si += val % (i + 1); // i는 0-based, 문제는 1-based이므로 (i+1)
            }
            answer ^= si;
        }

        return answer;
    }
}
