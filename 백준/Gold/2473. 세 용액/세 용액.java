import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 용액 수 입력
        int n = Integer.parseInt(br.readLine());
        
        // 용액의 특성값 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }
        
        // 정렬
        Arrays.sort(a);
        
        long closestSum = Long.MAX_VALUE; // 최소 절댓값 합
        long[] result = new long[3]; // 결과 저장 배열

        // 투 포인터로 합 계산
        for (int std = 0; std < n - 2; std++) {
            int s = std + 1; // 시작 포인터
            int e = n - 1;   // 끝 포인터
            
            while (s < e) {
                long sum = a[std] + a[s] + a[e];

                // 절댓값 기준으로 최소값 갱신
                if (Math.abs(sum) < Math.abs(closestSum)) {
                    closestSum = sum;
                    result[0] = a[std];
                    result[1] = a[s];
                    result[2] = a[e];
                }

                // 특성값 조정
                if (sum > 0) {
                    e--; // 값을 줄이기 위해 끝 포인터 감소
                } else if (sum < 0) {
                    s++; // 값을 늘리기 위해 시작 포인터 증가
                } else {
                    // 0에 가장 가까운 값 발견
                    System.out.println(a[std] + " " + a[s] + " " + a[e]);
                    return;
                }
            }
        }

        // 결과 출력 (오름차순 정렬된 상태)
        Arrays.sort(result);
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }
}