import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] seg;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());

        int p = 2000000;
        N = 1;
        while (N < p) N <<= 1;  // N = 2^ceil(log2(p))

        seg = new int[N * 2];

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == 1) {
                modify(b, 1);
            } else {
                int c = search(b);
                sb.append(c).append("\n");
                modify(c, -1);
            }
        }

        System.out.print(sb);
    }

    // 세그먼트 트리 수정
    static void modify(int num, int v) {
        int index = N + num;
        while (index > 0) {
            seg[index] += v;
            index >>= 1;
        }
    }

    // 세그먼트 트리 k번째 수 찾기
    static int search(int goal) {
        int index = 1;
        while (index < N) {
            int l = index << 1;
            int r = l + 1;
            if (seg[l] >= goal) {
                index = l;
            } else {
                goal -= seg[l];
                index = r;
            }
        }
        return index - N;
    }
}
