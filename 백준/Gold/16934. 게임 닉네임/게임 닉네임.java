import java.io.*;
import java.util.*;

/**
 * BOJ 16934 유형: 이름 등록
 * - 처음 등장한 이름: 트라이를 이용해 "처음으로 새 노드가 생성되는 지점까지"의 접두사를 출력
 *   (즉, 최소 고유 접두사; 전부 존재하면 전체 이름 출력)
 * - 동일 이름이 다시 등장하면 name + 등장횟수(2부터) 출력
 */
public class Main {
    // Trie Node
    static class Node {
        char data;
        int size = 1; // 지나간 횟수 (로직상 크게 쓰이진 않지만 원 코드에 맞춰 유지)
        HashMap<Character, Node> next = new HashMap<>();
        Node(char data) { this.data = data; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 첫 줄 N (token 사용)
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        // 이름 등장 횟수 카운터 (파이썬 Counter 대체)
        HashMap<String, Integer> ct = new HashMap<>();

        // 트라이 루트
        Node root = new Node('\0');

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String name = br.readLine(); // 이름 한 줄

            // 이미 동일한 이름이 있었으면: 이름+새로운 횟수 출력
            if (ct.containsKey(name)) {
                int cnt = ct.get(name) + 1;
                ct.put(name, cnt);
                out.append(name).append(cnt).append('\n');
                continue;
            }

            // 처음 보는 이름이면: 트라이에 삽입하며 최소 고유 접두사 생성
            Node cur = root;
            StringBuilder secondName = new StringBuilder();
            boolean flag = true; // 새 노드가 처음 생성되기 전까지 true

            for (int idx = 0; idx < name.length(); idx++) {
                char c = name.charAt(idx);

                if (flag) secondName.append(c);

                Node nx = cur.next.get(c);
                if (nx == null) {
                    // 처음으로 새 노드가 생기는 순간 이후로는 접두사 추가 중단
                    nx = new Node(c);
                    cur.next.put(c, nx);
                    cur = nx;
                    flag = false;
                } else {
                    // 기존 경로면 size 증가 (원 코드 유지)
                    nx.size++;
                    cur = nx;
                }
            }

            // 첫 등장 처리
            ct.put(name, 1);

            // 전부 기존 경로였다면 secondName == 전체 name (원 파이썬과 동일)
            out.append(secondName).append('\n');
        }

        System.out.print(out.toString());
    }
}
