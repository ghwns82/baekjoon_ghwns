import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());  // 접시 수
        int d = Integer.parseInt(st.nextToken());  // 초밥 종류 수
        int k = Integer.parseInt(st.nextToken());  // 연속으로 먹을 접시 수
        int c = Integer.parseInt(st.nextToken());  // 쿠폰 번호
        
        int[] belt = new int[n];
        for (int i = 0; i < n; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }

        // 초밥 종류 카운트와 윈도우 설정
        HashMap<Integer, Integer> sushiCount = new HashMap<>();
        int uniqueSushi = 0;
        
        // 초기 윈도우 설정
        for (int i = n - k; i < n; i++) {
            sushiCount.put(belt[i], sushiCount.getOrDefault(belt[i], 0) + 1);
            if (sushiCount.get(belt[i]) == 1) {
                uniqueSushi++;
            }
        }
        
        // 쿠폰 초밥 추가
        sushiCount.put(c, sushiCount.getOrDefault(c, 0) + 1);
        if (sushiCount.get(c) == 1) {
            uniqueSushi++;
        }
        
        int result = uniqueSushi;

        // 투 포인터로 슬라이딩 윈도우 이동
        int start = 0, end = n - k;
        while (start < n) {
            // 오른쪽 끝 초밥 추가
            sushiCount.put(belt[start], sushiCount.getOrDefault(belt[start], 0) + 1);
            if (sushiCount.get(belt[start]) == 1) {
                uniqueSushi++;
            }

            // 왼쪽 끝 초밥 제거
            sushiCount.put(belt[end], sushiCount.get(belt[end]) - 1);
            if (sushiCount.get(belt[end]) == 0) {
                uniqueSushi--;
            }
            
            // 결과 갱신
            result = Math.max(result, uniqueSushi);
            
            // 포인터 이동
            start++;
            end = (end + 1) % n;
        }

        System.out.println(result);
    }
}