import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static boolean[] visited;
    static int[] cards;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 철수가 가진 카드 입력 및 정렬
        cards = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cards);

        // 민수가 낼 카드 입력
        int[] minsu = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            minsu[i] = Integer.parseInt(st.nextToken());
        }

        // parent 배열 및 visited 배열 초기화
        parent = new int[N + 1];
        visited = new boolean[N + 1];

        int cardIndex = 0;
        for (int i = 1; i <= N; i++) {
            if (cards[cardIndex] <= i) {
                cardIndex++;
                if (cardIndex == M) break;
            }
            parent[i] = cards[cardIndex];
        }

        // 민수가 낼 카드 결정
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int chulsuCard = find(minsu[i]);
            visited[chulsuCard] = true;
            sb.append(chulsuCard).append("\n");
        }

        // 결과 출력
        System.out.print(sb);
    }

    // find 함수 (철수가 낼 카드 찾기)
    static int find(int x) {
        if (visited[parent[x]]) {
            parent[x] = find(parent[x]);
            return parent[x];
        }
        return parent[x];
    }
}