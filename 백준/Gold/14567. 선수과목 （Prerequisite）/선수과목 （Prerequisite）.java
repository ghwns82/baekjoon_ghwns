import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        List<Integer>[] pre = new ArrayList[n + 1];
        int[] wait = new int[n + 1];
        int[] result = new int[n + 1];
        
        for (int i = 0; i <= n; i++) {
            pre[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pre[a].add(b);
            wait[b]++;
        }
        
        Queue<Integer> work = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (wait[i] == 0) {
                work.add(i);
            }
        }
        
        int time = 0;
        while (!work.isEmpty()) {
            time++;
            Queue<Integer> nextWork = new LinkedList<>();
            while (!work.isEmpty()) {
                int study = work.poll();
                result[study] = time;
                for (int next : pre[study]) {
                    wait[next]--;
                    if (wait[next] == 0) {
                        nextWork.add(next);
                    }
                }
            }
            work = nextWork;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
