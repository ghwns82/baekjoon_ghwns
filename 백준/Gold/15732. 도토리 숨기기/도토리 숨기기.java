import java.io.*;
import java.util.*;

public class Main {
    static int n, k, d;
    static int[][] rules;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        
        rules = new int[k][3];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            rules[i][0] = Integer.parseInt(st.nextToken());
            rules[i][1] = Integer.parseInt(st.nextToken());
            rules[i][2] = Integer.parseInt(st.nextToken());
        }
        
        int s = 1, e = n, result = 0;
        while (s <= e) {
            int mid = (s + e) / 2;
            if (check(mid) >= d) {
                result = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        System.out.println(result);
    }
    
    static long check(int p) {
        long result = 0;
        for (int[] rule : rules) {
            int start = rule[0], end = rule[1], step = rule[2];
            if (p >= start) {
                result += (Math.min(end, p) - start) / step + 1;
            }
        }
        return result;
    }
}