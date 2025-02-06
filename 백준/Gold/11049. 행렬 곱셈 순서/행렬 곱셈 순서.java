import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp;
    static int[][] matrix;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        matrix = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }
        
        if (n == 1) {
            System.out.println(0);
            return;
        }
        
        dp = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = matrix[i][0] * matrix[i][1] * matrix[i + 1][1];
        }
        
        System.out.println(rec(0, n - 1));
    }
    
    static int rec(int s, int e) {
        if (dp[s][e] == 0 && s != e) {
            int minValue = Integer.MAX_VALUE;
            
            minValue = Math.min(minValue, matrix[s][0] * matrix[s + 1][0] * matrix[e][1] + rec(s + 1, e));
            minValue = Math.min(minValue, rec(s, e - 1) + matrix[s][0] * matrix[e - 1][1] * matrix[e][1]);
            
            for (int m = s + 1; m < e; m++) {
                minValue = Math.min(minValue, rec(s, m) + rec(m + 1, e) + matrix[s][0] * matrix[m][1] * matrix[e][1]);
            }
            
            dp[s][e] = minValue;
        }
        return dp[s][e];
    }
}