import java.util.*;

public class Main {
    static int[][] board = new int[10][10];
    static int[] papers = {0, 5, 5, 5, 5, 5};  // 1~5 크기의 색종이 각각 5장
    static int result = 100;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        rec(0, 0);

        System.out.println(result == 100 ? -1 : result);
    }

    static void rec(int index, int cnt) {
        while (index < 100) {
            int i = index / 10;
            int j = index % 10;
            index++;

            if (board[i][j] == 0) continue;

            for (int d = 1; d <= 5; d++) {
                if (i + d > 10 || j + d > 10) continue;

                boolean flag = true;
                for (int di = i; di < i + d; di++) {
                    for (int dj = j; dj < j + d; dj++) {
                        if (board[di][dj] == 0) {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) break;
                }

                if (flag && papers[d] > 0) {
                    papers[d]--;
                    for (int di = i; di < i + d; di++) {
                        for (int dj = j; dj < j + d; dj++) {
                            board[di][dj] = 0;
                        }
                    }

                    rec(index, cnt + 1);

                    // backtrack
                    for (int di = i; di < i + d; di++) {
                        for (int dj = j; dj < j + d; dj++) {
                            board[di][dj] = 1;
                        }
                    }
                    papers[d]++;
                }
            }

            // 더 이상 진행 불가 → 브레이크
            return;
        }

        // 전부 탐색 완료 → 최소값 갱신
        result = Math.min(result, cnt);
    }
}
