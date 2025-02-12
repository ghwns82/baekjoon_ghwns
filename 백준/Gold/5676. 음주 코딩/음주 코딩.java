import java.io.*;
import java.util.*;

public class Main {
    static int[] segTree;
    static int N;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        while (true) {
            String line = br.readLine();
            if (line == null || line.isEmpty()) break;
            
            st = new StringTokenizer(line);
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            arr = new int[N];
            segTree = new int[4 * N];
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(st.nextToken());
                arr[i] = x > 0 ? 1 : x < 0 ? -1 : 0;
            }
            
            build(1, 0, N - 1);
            
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                
                if (cmd.equals("C")) {
                    int index = Integer.parseInt(st.nextToken()) - 1;
                    int value = Integer.parseInt(st.nextToken());
                    value = value > 0 ? 1 : value < 0 ? -1 : 0;
                    update(1, 0, N - 1, index, value);
                } else {
                    int l = Integer.parseInt(st.nextToken()) - 1;
                    int r = Integer.parseInt(st.nextToken()) - 1;
                    int result = query(1, 0, N - 1, l, r);
                    sb.append(result > 0 ? "+" : result < 0 ? "-" : "0");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    
    static void build(int node, int start, int end) {
        if (start == end) {
            segTree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(2 * node, start, mid);
            build(2 * node + 1, mid + 1, end);
            segTree[node] = segTree[2 * node] * segTree[2 * node + 1];
        }
    }
    
    static void update(int node, int start, int end, int idx, int value) {
        if (start == end) {
            arr[idx] = value;
            segTree[node] = value;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) {
                update(2 * node, start, mid, idx, value);
            } else {
                update(2 * node + 1, mid + 1, end, idx, value);
            }
            segTree[node] = segTree[2 * node] * segTree[2 * node + 1];
        }
    }
    
    static int query(int node, int start, int end, int l, int r) {
        if (r < start || l > end) return 1;
        if (l <= start && end <= r) return segTree[node];
        int mid = (start + end) / 2;
        return query(2 * node, start, mid, l, r) * query(2 * node + 1, mid + 1, end, l, r);
    }
}