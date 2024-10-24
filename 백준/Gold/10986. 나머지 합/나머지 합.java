import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 입력 받기
        int N = sc.nextInt(); // 배열의 크기
        int M = sc.nextInt(); // 나눌 값
        long[] A = new long[N + 1]; // 부분합을 저장할 배열
        
        // 입력 받은 배열 요소의 누적 합 구하기
        for (int i = 1; i <= N; i++) {
            A[i] = A[i - 1] + sc.nextInt();
        }
        
        // 나머지 카운트를 저장할 배열 (0 ~ M-1 까지의 값)
        long[] mod = new long[M];
        
        long result = 0;
        
        // 누적합의 나머지 계산
        for (int i = 1; i <= N; i++) {
            int remainder = (int) (A[i] % M);
            if (remainder < 0) remainder += M; // 음수 나머지 방지
            
            // 나머지가 0인 경우는 그 자체로 나누어 떨어지는 구간
            if (remainder == 0) result++;
            
            // 이전에 같은 나머지가 나온 횟수를 더함
            result += mod[remainder];
            
            // 현재 나머지 카운트를 1 증가
            mod[remainder]++;
        }
        
        // 결과 출력
        System.out.println(result);
    }
}