import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // BufferedReader로 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 첫 번째 줄에서 N과 M 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        long[] mod = new long[M]; // 나머지 카운트를 저장할 배열
        
        long sum = 0;
        long result = 0;

        // 배열 요소를 입력 받으며 나머지 계산과 카운팅 수행
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            sum += num;
            
            // sum이 M보다 크면 M으로 나머지 연산 수행
            int remainder = (int)(sum % M);
            if (remainder < 0) remainder += M; // 음수 나머지 방지
            
            mod[remainder]++; // 나머지 값에 해당하는 카운트를 증가
        }

        // 나머지가 0인 경우 그 자체로 나누어 떨어지는 구간
        result += mod[0];

        // 나머지가 같은 것들로 만들 수 있는 구간의 개수 계산
        for (int i = 0; i < M; i++) {
            if (mod[i] > 1) {
                result += mod[i] * (mod[i] - 1) / 2;  // 조합을 이용하여 구간의 개수 계산
            }
        }

        // 결과 출력
        System.out.println(result);
    }
}