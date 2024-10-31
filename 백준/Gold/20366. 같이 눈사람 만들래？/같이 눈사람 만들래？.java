import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        int n = Integer.parseInt(br.readLine());
        int[] snows = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            snows[i] = Integer.parseInt(st.nextToken());
        }

        // 눈사람 리스트 생성
        List<int[]> snowmanList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                snowmanList.add(new int[]{snows[i] + snows[j], i, j});
            }
        }

        // 눈사람 리스트 정렬
        snowmanList.sort((a, b) -> Integer.compare(a[0], b[0]));

        long result = Long.MAX_VALUE;

        // 완전 탐색을 통해 최소 차이 찾기
        for (int i = 0; i < snowmanList.size(); i++) {
            int v = snowmanList.get(i)[0];
            int[] points = Arrays.copyOfRange(snowmanList.get(i), 1, 3);

            // i번째 눈사람과 가까운 키를 가지는 다음 눈사람 탐색
            for (int u = i + 1; u < snowmanList.size(); u++) {
                int[] nextSnowman = snowmanList.get(u);
                
                if (Math.abs(nextSnowman[0] - v) >= result) break;  // 최솟값보다 크면 break
                if (nextSnowman[1] != points[0] && nextSnowman[1] != points[1] &&
                    nextSnowman[2] != points[0] && nextSnowman[2] != points[1]) {
                    result = Math.min(result, Math.abs(nextSnowman[0] - v));
                    break;
                }
            }
        }

        System.out.println(result);
    }
}