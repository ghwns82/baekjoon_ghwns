import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> tmp = new TreeSet<>();
        for (int i = -n; i < 0; i++) {
            int currentIdx = (n + i) % n;
            int nextIdx = (n + i + 1) % n;

            if (arr[currentIdx] % n == (arr[nextIdx] + 1) % n) {
                tmp.add(currentIdx);
                tmp.add(nextIdx);
            }
        }

        List<Integer> tmpList = new ArrayList<>(tmp);

        if (tmpList.size() >= n) {
            if (arr[0] == n) {
                sb.append("2\n1 ").append(n - 1).append("\n1");
            } else {
                sb.append("1\n1 ").append(n - 1).append("\n").append(n - arr[0]);
            }
        } else {
            int start = -1, end = -1;
            for (int i = 0; i < tmpList.size() - 1; i++) {
                if (tmpList.get(i) % n != (tmpList.get(i + 1) - 1) % n) {
                    end = tmpList.get(i);
                    start = tmpList.get(i + 1);
                    break;
                }
            }

            if (start == -1 || end == -1) {
                start = tmpList.get(0);
                end = tmpList.get(tmpList.size() - 1);
            }

            int move = (arr[end] - 1 - start + n) % n;

            for (int k2 = 0; k2 < n; k2++) {
                int k1 = (move - k2 + n) % n;

                if (start < end && k1 > 0 && k2 > 0) {
                    sb.append(k1).append("\n").append(start + 1).append(" ").append(end + 1).append("\n").append(k2);
                    break;
                }
                start = (start + 1) % n;
                end = (end + 1) % n;
            }
        }

        System.out.println(sb.toString());
    }
}