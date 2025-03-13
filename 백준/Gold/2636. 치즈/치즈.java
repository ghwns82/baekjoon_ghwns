import java.io.*;
import java.util.*;

public class Main {
    static int h, w;
    static int[][] space;
    static int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    
    static int bfs() {
        Set<String> cheese = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        Set<String> visited = new HashSet<>();
        visited.add("0,0");
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            
            for (int[] d : directions) {
                int nx = x + d[0], ny = y + d[1];
                String pos = nx + "," + ny;
                if (nx >= 0 && nx < h && ny >= 0 && ny < w && !visited.contains(pos)) {
                    if (space[nx][ny] == 1) {
                        cheese.add(pos);
                    } else {
                        queue.add(new int[]{nx, ny});
                        visited.add(pos);
                    }
                }
            }
        }
        
        for (String c : cheese) {
            String[] parts = c.split(",");
            int cx = Integer.parseInt(parts[0]);
            int cy = Integer.parseInt(parts[1]);
            space[cx][cy] = 0;
        }
        return cheese.size();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        
        space = new int[h][w];
        int cheeseCnt = 0;
        
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                cheeseCnt += space[i][j];
            }
        }
        
        int lastCheese = cheeseCnt, time = 0;
        
        while (cheeseCnt > 0) {
            int cnt = bfs();
            cheeseCnt -= cnt;
            if (cheeseCnt > 0) {
                lastCheese = cheeseCnt;
            }
            time++;
        }
        
        System.out.println(time);
        System.out.println(lastCheese);
    }
}