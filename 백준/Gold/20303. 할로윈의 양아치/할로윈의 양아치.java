import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[] candies = new int[n];
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
        
        Map<Integer, int[]> result = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int key = find(i);
            if (!result.containsKey(key)) {
                result.put(key, new int[]{1, candies[i - 1]});
            } else {
                result.get(key)[0]++;
                result.get(key)[1] += candies[i - 1];
            }
        }
        
        List<int[]> items = new ArrayList<>();
        for (int[] val : result.values()) {
            if (val[0] < k) {
                items.add(val);
            }
        }
        
        if (items.isEmpty()) {
            System.out.println(0);
            return;
        }
        
        int[][] bag = new int[items.size()][k];
        for (int i = 0; i < items.size(); i++) {
            int s = items.get(i)[0];
            int v = items.get(i)[1];
            
            for (int j = 0; j < s; j++) {
                bag[i][j] = i > 0 ? bag[i - 1][j] : 0;
            }
            for (int j = s; j < k; j++) {
                bag[i][j] = Math.max((i > 0 ? bag[i - 1][j - s] : 0) + v, i > 0 ? bag[i - 1][j] : 0);
            }
        }
        
        System.out.println(bag[items.size() - 1][k - 1]);
    }
    
    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        parent[Math.max(ra, rb)] = Math.min(ra, rb);
    }
}
