import java.io.*;
import java.util.*;

public class Main {
    private static int[][] board = new int[9][9];
    private static List<int[]> emptyCells = new ArrayList<>();
    private static boolean solved = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        for (int i = 0; i < 9; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < 9; j++) {
                board[i][j] = line.charAt(j) - '0';
                if (board[i][j] == 0) {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }

        solve(0);

        br.close();
    }

    private static void solve(int index) {
        if (index == emptyCells.size()) {
            printBoard();
            solved = true;
            return;
        }

        int[] cell = emptyCells.get(index);
        int row = cell[0];
        int col = cell[1];

        // 1부터 9까지 시도 (사전식으로 앞서는 답을 찾기 위해 오름차순)
        for (int num = 1; num <= 9; num++) {
            if (isValid(row, col, num)) {
                board[row][col] = num;
                solve(index + 1);
                if (solved) return; // 이미 답을 찾았으면 종료
                board[row][col] = 0; // 백트래킹
            }
        }
    }

    private static boolean isValid(int row, int col, int num) {
        // 같은 행에 중복된 숫자가 있는지 확인
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) return false;
        }

        // 같은 열에 중복된 숫자가 있는지 확인
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) return false;
        }

        // 3x3 박스에 중복된 숫자가 있는지 확인
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) return false;
            }
        }

        return true;
    }

    private static void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}