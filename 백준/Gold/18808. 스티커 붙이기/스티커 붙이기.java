import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] laptop;
    static int stickerRows, stickerCols;
    static int[][] sticker;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 노트북 크기와 스티커 개수 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        laptop = new int[N][M];
        
        for (int i = 0; i < K; i++) {
            // 스티커 크기 및 모양 입력
            st = new StringTokenizer(br.readLine());
            stickerRows = Integer.parseInt(st.nextToken());
            stickerCols = Integer.parseInt(st.nextToken());
            sticker = new int[stickerRows][stickerCols];
            
            for (int r = 0; r < stickerRows; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < stickerCols; c++) {
                    sticker[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            
            // 스티커 붙이기 시도
            placeSticker();
        }
        
        // 채워진 칸 수 세기
        int filledCells = countFilledCells();
        System.out.println(filledCells);
    }
    
    // 스티커 붙이기 함수
    static void placeSticker() {
        for (int rotation = 0; rotation < 4; rotation++) {
            if (canPlaceSticker()) {
                return;
            }
            rotateSticker();
        }
    }
    
    // 스티커 붙이기 가능한지 확인하고 붙이기
    static boolean canPlaceSticker() {
        for (int i = 0; i <= N - stickerRows; i++) {
            for (int j = 0; j <= M - stickerCols; j++) {
                if (isPlaceable(i, j)) {
                    applySticker(i, j);
                    return true;
                }
            }
        }
        return false;
    }
    
    // 특정 위치에 스티커를 붙일 수 있는지 확인
    static boolean isPlaceable(int row, int col) {
        for (int i = 0; i < stickerRows; i++) {
            for (int j = 0; j < stickerCols; j++) {
                if (sticker[i][j] == 1 && laptop[row + i][col + j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }
    
    // 스티커를 실제로 노트북에 붙이기
    static void applySticker(int row, int col) {
        for (int i = 0; i < stickerRows; i++) {
            for (int j = 0; j < stickerCols; j++) {
                if (sticker[i][j] == 1) {
                    laptop[row + i][col + j] = 1;
                }
            }
        }
    }
    
    // 스티커 90도 회전
    static void rotateSticker() {
        int[][] rotatedSticker = new int[stickerCols][stickerRows];
        for (int i = 0; i < stickerRows; i++) {
            for (int j = 0; j < stickerCols; j++) {
                rotatedSticker[j][stickerRows - 1 - i] = sticker[i][j];
            }
        }
        sticker = rotatedSticker;
        int temp = stickerRows;
        stickerRows = stickerCols;
        stickerCols = temp;
    }
    
    // 스티커로 채워진 칸 세기
    static int countFilledCells() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (laptop[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
}