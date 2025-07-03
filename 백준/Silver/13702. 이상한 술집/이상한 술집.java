import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static int n, k;
    static int[] waters;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String[] nk = br.readLine().split(" ");
        n = Integer.parseInt(nk[0]);
        k = Integer.parseInt(nk[1]);

        waters = new int[n];
        for (int i = 0; i < n; i++) {
            waters[i] = Integer.parseInt(br.readLine());
        }

        // 이진 탐색
        long start = 1; // 0으로 하면 0으로 나눌 수 없으므로 1부터 시작
        long end = (1L << 31) - 1;
        long result = 0;

        while (start <= end) {
            long mid = (start + end) / 2;

            if (cal(mid) < k) {
                end = mid - 1;
            } else {
                result = mid;
                start = mid + 1;
            }
        }

        System.out.println(result);
    }

    // 주어진 길이로 나눴을 때 가능한 조각 수 계산
    static long cal(long x) {
        long sum = 0;
        for (int water : waters) {
            sum += water / x;
        }
        return sum;
    }
}
