import java.io.*;
import java.util.*;

public class Main {
    static int n, q;
    static int[] korean, western;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        korean = new int[n];
        western = new int[n];
        arr = new int[2 * n][4];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            korean[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            western[i] = Integer.parseInt(st.nextToken());
        }

        int p1 = 0, p2 = 0, index = 0;
        if (korean[p1] < western[p2]) {
            arr[index][0] = korean[p1];
            arr[index][1] = 1;
            arr[index][3] = 1;
            p1++;
        } else {
            arr[index][0] = western[p2];
            arr[index][2] = 1;
            arr[index][3] = 2;
            p2++;
        }
        index++;

        while (index < 2 * n) {
            while (p1 >= n && index < 2 * n) {
                arr[index][0] = western[p2];
                arr[index][1] = arr[index - 1][1];
                arr[index][2] = arr[index - 1][2] + 1;
                arr[index][3] = 2;
                p2++;
                index++;
            }

            while (p2 >= n && index < 2 * n) {
                arr[index][0] = korean[p1];
                arr[index][1] = arr[index - 1][1] + 1;
                arr[index][2] = arr[index - 1][2];
                arr[index][3] = 1;
                p1++;
                index++;
            }

            if (index < 2 * n && p2 < n && p1 < n) {
                if (korean[p1] < western[p2]) {
                    arr[index][0] = korean[p1];
                    arr[index][1] = arr[index - 1][1] + 1;
                    arr[index][2] = arr[index - 1][2];
                    arr[index][3] = 1;
                    p1++;
                } else {
                    arr[index][0] = western[p2];
                    arr[index][1] = arr[index - 1][1];
                    arr[index][2] = arr[index - 1][2] + 1;
                    arr[index][3] = 2;
                    p2++;
                }
                index++;
            }
        }

        q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int v1 = korean[i - 1];
            int v2 = western[j - 1];
            int bisectW1 = bisect(western, v1, j);
            int bisectK2 = bisect(korean, v2, i);

            if (i + bisectW1 < k) {
                // sb.append("hey1 ").append("\n");
                sb.append("2 ").append(k - i).append("\n");
                continue;
            }

            if (j + bisectK2 < k) {
                // sb.append("hey2 ").append("\n");
                sb.append("1 ").append(k - j).append("\n");
                continue;
            }

            if (i + bisectW1 == k) {
                // sb.append("hey3 ").append("\n");
                if (bisectW1 == 0 || korean[i - 1] > western[bisectW1 - 1]){
                    sb.append("1 ").append(i).append("\n");
                } else {
                    sb.append("2 ").append(bisectW1).append("\n");
                }
                continue;
            }

            if (j + bisectK2 == k) {
                // sb.append("hey4 ").append("\n");
                if (western[j - 1] > korean[bisectK2 - 1]) {
                    sb.append("2 ").append(j).append("\n");
                } else {
                    sb.append("1 ").append(bisectK2).append("\n");
                }
                continue;
            }

            int answerType = arr[k - 1][3];
            int answerIndex = arr[k - 1][answerType];

            sb.append(answerType).append(" ").append(answerIndex).append("\n");

        }

        System.out.print(sb);
    }

    static int bisect(int[] arr, int value, int limit) {
        int s = 0, e = limit;
        while (s < e) {
            int mid = (s + e) / 2;
            if (arr[mid] < value) {
                s = mid + 1;
            } else {
                e = mid;
            }
        }
        return s;
    }
}