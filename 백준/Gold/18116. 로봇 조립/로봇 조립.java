import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_VALUE = 1000001;
    static int[] parent = new int[MAX_VALUE];
    static int[] cntList = new int[MAX_VALUE];

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return;
        
        int minIdx = Math.min(ra, rb);
        int maxIdx = Math.max(ra, rb);
        
        cntList[minIdx] += cntList[maxIdx];
        cntList[maxIdx] = 0;
        
        parent[maxIdx] = minIdx;
        find(a);
        find(b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < MAX_VALUE; i++) {
            parent[i] = i;
            cntList[i] = 1;
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            
            if (cmd.equals("I")) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            } else {
                int c = Integer.parseInt(st.nextToken());
                sb.append(cntList[find(c)]).append("\n");
            }
        }
        System.out.print(sb);
    }
}