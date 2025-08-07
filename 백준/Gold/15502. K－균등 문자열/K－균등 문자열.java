import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    // 경로 압축을 포함한 find 함수
    static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    // 유니온 함수
    static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra != rb) {
            // 항상 작은 쪽을 루트로 설정
            if (ra > rb) {
                int tmp = ra;
                ra = rb;
                rb = tmp;
            }
            parent[rb] = ra;
        }
    }

    public static void main(String[] args) throws IOException {
        final int MOD = 1_000_000_007;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int k = 0; k < m; k++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            for (int i = a - 1; i <= b - c - 1; i++) {
                union(i, i + c);
            }
        }

        // 모든 노드의 루트를 최신화
        for (int i = 0; i < n; i++) find(i);

        // 고유 루트 개수 세기
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(parent[i]);
        }

        // 결과 출력
        System.out.println(modPow(2, set.size(), MOD));
    }

    // 거듭제곱을 MOD로 계산
    static long modPow(long base, long exp, int mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }
}
