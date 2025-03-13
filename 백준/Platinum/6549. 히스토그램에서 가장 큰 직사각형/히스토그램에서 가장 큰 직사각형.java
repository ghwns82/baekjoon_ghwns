import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) break;
            
            int[] hList = new int[n + 1];
            for (int i = 0; i < n; i++) {
                hList[i] = Integer.parseInt(st.nextToken());
            }
            
            long result = 0;
            Stack<int[]> stack = new Stack<>();
            
            for (int i = 0; i <= n; i++) {
                int h = (i == n) ? 0 : hList[i];
                int tmp = i;
                
                while (!stack.isEmpty() && stack.peek()[0] > h) {
                    int[] top = stack.pop();
                    tmp = top[1];
                    result = Math.max(result, (long) top[0] * (i - tmp));
                }
                stack.push(new int[]{h, tmp});
            }
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
}