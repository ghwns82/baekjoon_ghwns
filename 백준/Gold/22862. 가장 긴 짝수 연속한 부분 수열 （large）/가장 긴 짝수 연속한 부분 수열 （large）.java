import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0, e = 0, result = 0, sum = 0, ability = k;

        while (e < n) {
            if (arr[e] % 2 == 0) {
                sum++;
                result = Math.max(result, sum);
                e++;
            } else {
                if (ability > 0) {
                    ability--;
                    e++;
                } else {
                    if (arr[s] % 2 != 0) {
                        ability++;
                    } else {
                        sum--;
                    }
                    s++;
                }
            }
        }
        System.out.println(result);
    }
}