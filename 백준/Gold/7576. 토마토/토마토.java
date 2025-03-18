import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        String[][] tomatos = new String[n][m];
        ArrayList<int[]> work = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                tomatos[i][j] = st.nextToken();
                if (tomatos[i][j].equals("1")) {
                    work.add(new int[]{i, j});
                }
            }
        }

        int day = -1;
        while (!work.isEmpty()) {
            ArrayList<int[]> crt_work = work;
            work = new ArrayList<>();
            for (int[] crt : crt_work) {
                int x = crt[0];
                int y = crt[1];
                if (x >= 1 && tomatos[x - 1][y].equals("0")) {
                    tomatos[x - 1][y] = "1";
                    work.add(new int[]{x - 1, y});
                }
                if (y >= 1 && tomatos[x][y - 1].equals("0")) {
                    tomatos[x][y - 1] = "1";
                    work.add(new int[]{x, y - 1});
                }
                if (x < n - 1 && tomatos[x + 1][y].equals("0")) {
                    tomatos[x + 1][y] = "1";
                    work.add(new int[]{x + 1, y});
                }
                if (y < m - 1 && tomatos[x][y + 1].equals("0")) {
                    tomatos[x][y + 1] = "1";
                    work.add(new int[]{x, y + 1});
                }
            }
            day++;
        }

        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tomatos[i][j].equals("0")) {
                    System.out.println(-1);
                    break outer;
                }
            }
        }

        if (!containsZero(tomatos)) {
            System.out.println(day);
        }
    }

    static boolean containsZero(String[][] tomatos) {
        for (int i = 0; i < tomatos.length; i++) {
            for (int j = 0; j < tomatos[0].length; j++) {
                if (tomatos[i][j].equals("0")) {
                    return true;
                }
            }
        }
        return false;
    }
}
