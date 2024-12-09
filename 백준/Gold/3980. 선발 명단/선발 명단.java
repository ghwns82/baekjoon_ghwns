import java.io.*;
import java.util.*;

public class Main {
    private static int[][] info = new int[11][11];
    private static boolean[] visited = new boolean[11];
    private static int result;
    private static int tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());

        while (testCases-- > 0) {
            for (int i = 0; i < 11; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    info[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Arrays.fill(visited, false);
            tmp = 0;
            result = 0;
            recursion(0);
            System.out.println(result);
        }
    }

    private static void recursion(int n) {
        if (n == 11) {
            result = Math.max(result, tmp);
            return;
        }

        for (int i = 0; i < 11; i++) {
            if (info[n][i] != 0 && !visited[i]) {
                visited[i] = true;
                tmp += info[n][i];
                recursion(n + 1);
                tmp -= info[n][i];
                visited[i] = false;
            }
        }
    }
}