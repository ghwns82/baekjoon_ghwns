import java.io.*;
import java.util.*;

/**
 * Python 코드를 Java로 변환한 버전입니다.
 * 입력:
 *  - 첫 줄: 정점 수 n
 *  - 둘째 줄: 길이 n의 정수들 (각 노드의 부모 인덱스). 0번 노드의 값은 사용하지 않고,
 *             1..n-1에 대해 parent[c]가 c의 부모입니다.
 * 출력:
 *  - search(0) 결과값
 */
public class Main {

    static List<Integer>[] children; // 각 노드의 자식 목록
    static int[] dp;                 // 메모이제이션: dp[u] = 서브트리 결과
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine().trim());
        int[] parent = new int[n];

        // 부모 배열 읽기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) parent[i] = Integer.parseInt(st.nextToken());

        // children 리스트 초기화
        children = new ArrayList[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();

        // 1..n-1만 사용 (파이썬 코드의 [1:]과 동일)
        for (int c = 1; c < n; c++) {
            int p = parent[c];
            children[p].add(c);
        }

        dp = new int[n];
        Arrays.fill(dp, -1);

        System.out.println(search(0));
    }

    // 파이썬의 search(index)와 동일
    static int search(int u) {
        if (dp[u] != -1) return dp[u];

        // 리프면 0
        if (children[u].isEmpty()) {
            dp[u] = 0;
            return 0;
        }

        // 자식들의 값 계산 후 내림차순 정렬
        List<Integer> vals = new ArrayList<>();
        for (int v : children[u]) vals.add(search(v));
        vals.sort(Comparator.reverseOrder());

        // cal(cvalues)와 동일한 계산
        int tmp = 1 + vals.get(0);
        for (int i = 0; i < vals.size(); i++) {
            tmp = Math.max(tmp, (i + 1) + vals.get(i)); // i는 0부터이므로 (i+1)
        }

        dp[u] = tmp;
        return dp[u];
    }
}
