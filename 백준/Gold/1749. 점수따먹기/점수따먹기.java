import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 행의 수
        int M = Integer.parseInt(st.nextToken()); // 열의 수
        int[][] matrix = new int[N][M];
        
        // 행렬 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 최대 합을 저장할 변수
        int maxSum = Integer.MIN_VALUE;
        
        // 부분 행렬의 최대 합을 구하기 위한 2차원 배열 처리
        for (int startRow = 0; startRow < N; startRow++) {
            int[] temp = new int[M]; // 임시 배열
            
            for (int endRow = startRow; endRow < N; endRow++) {
                // 누적 합 계산
                for (int col = 0; col < M; col++) {
                    temp[col] += matrix[endRow][col];
                }
                
                // 1차원 배열의 최대 부분 배열 합을 구하는 Kadane's Algorithm 적용
                int currentMax = kadane(temp);
                maxSum = Math.max(maxSum, currentMax);
            }
        }
        
        // 결과 출력
        System.out.println(maxSum);
    }
    
    // 1차원 배열의 최대 부분합을 구하는 Kadane's Algorithm
    public static int kadane(int[] array) {
        int maxEndingHere = array[0];
        int maxSoFar = array[0];
        
        for (int i = 1; i < array.length; i++) {
            maxEndingHere = Math.max(array[i], maxEndingHere + array[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
}