import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] cross = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cross[i] = Integer.parseInt(st.nextToken());
        }

        int[] left = new int[n - 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            left[i] = Integer.parseInt(st.nextToken());
        }

        int[] right = new int[n - 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            right[i] = Integer.parseInt(st.nextToken());
        }

        long[] nsLeft = new long[n];
        long[] nsRight = new long[n];
        nsLeft[0] = 0;
        nsRight[0] = 0;

        for (int i = 1; i < n; i++) {
            nsLeft[i] = nsLeft[i - 1] + left[i - 1];
            nsRight[i] = nsRight[i - 1] + right[i - 1];
        }

        long minValue = nsLeft[n - 1] + nsRight[n - 1] + cross[n - 1];
        int minIndex = -1;

        for (int i = 0; i < n; i++) {
            long tmp = nsLeft[i] + (nsRight[n - 1] - nsRight[i]) + cross[i];
            if (tmp < minValue) {
                minValue = tmp;
                minIndex = i + 1; // 출력은 1부터 시작
            }
        }

        System.out.println(minIndex + " " + minValue);
    }
}
