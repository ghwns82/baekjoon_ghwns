import java.io.*;
import java.util.*;

public class Main {

    static int[] parent;

    // Find function with path compression
    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union function
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[Math.max(rootA, rootB)] = Math.min(rootA, rootB);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()); // Number of cities
        int m = Integer.parseInt(br.readLine()); // Number of cities in the travel plan

        // Initialize parent array
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // Read connectivity matrix and perform unions
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    union(i, j);
                }
            }
        }

        // Read the travel plan
        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<Integer> resultSet = new HashSet<>();
        while (st.hasMoreTokens()) {
            resultSet.add(find(Integer.parseInt(st.nextToken())));
        }

        // If all cities in the travel plan belong to the same set, it's possible
        System.out.println(resultSet.size() == 1 ? "YES" : "NO");
    }
}