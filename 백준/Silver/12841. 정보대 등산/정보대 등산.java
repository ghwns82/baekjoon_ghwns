import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        long[] cross = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cross[i] = Long.parseLong(st.nextToken());
        }

        long[] left = new long[n - 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            left[i] = Long.parseLong(st.nextToken());
        }

        long[] right = new long[n - 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            right[i] = Long.parseLong(st.nextToken());
        }

        long[] nsLeft = new long[n];
        long[] nsRight = new long[n];
        for (int i = 1; i < n; i++) {
            nsLeft[i] = nsLeft[i - 1] + left[i - 1];
            nsRight[i] = nsRight[i - 1] + right[i - 1];
        }

        // 초기값 (파이썬 result = [~, -1])
        long bestCost = nsLeft[n - 1] + nsRight[n - 1] + cross[n - 1];
        int bestIndex = -1;

        for (int i = 0; i < n; i++) {
            long tmp = nsLeft[i] + (nsRight[n - 1] - nsRight[i]) + cross[i];
            // 파이썬의 min([cost, idx])와 동일하게 처리
            if (tmp < bestCost || (tmp == bestCost && (i + 1) < bestIndex)) {
                bestCost = tmp;
                bestIndex = i + 1;
            }
        }

        System.out.println(bestIndex + " " + bestCost);
    }
}
