import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static List<Integer>[] child;
    static int[][] treeRange;
    static int[] order;
    static int visited = 0;

    static void ett(int person) {
        order[person] = visited;
        visited++;
        treeRange[person][0] = visited;

        for (int c : child[person]) {
            ett(c);
        }

        treeRange[person][1] = visited;
    }

    static class Node {
        int start, end;
        long data, lazy;
        Node left, right;

        Node(int s, int e) {
            this.start = s;
            this.end = e;
            this.data = 0;
            this.lazy = 0;
        }
    }

    static class Seg {
        Node root;

        Seg() {
            root = create(0, n - 1);
        }

        Node create(int start, int end) {
            Node node = new Node(start, end);
            if (start == end) return node;
            int mid = (start + end) / 2;
            node.left = create(start, mid);
            node.right = create(mid + 1, end);
            return node;
        }

        void modify(Node cur, int s, int e, long k) {
            if (cur.start == s && cur.end == e) {
                cur.lazy += (e - s + 1) * k;
                return;
            } else {
                cur.data += (e - s + 1) * k;
            }

            int mid = (cur.start + cur.end) / 2;
            if (mid < s) {
                modify(cur.right, s, e, k);
            } else if (mid >= e) {
                modify(cur.left, s, e, k);
            } else {
                modify(cur.left, s, mid, k);
                modify(cur.right, mid + 1, e, k);
            }
        }

        long search(Node cur, int index) {
            int mid = (cur.start + cur.end) / 2;
            if (cur.lazy != 0) {
                cur.data += cur.lazy;
                if (cur.start != cur.end) {
                    long total = cur.lazy;
                    int len = cur.end - cur.start + 1;
                    cur.left.lazy += total / len * (mid - cur.start + 1);
                    cur.right.lazy += total / len * (cur.end - mid);
                }
                cur.lazy = 0;
            }
            if (cur.start == cur.end && cur.start == index) {
                return cur.data;
            }
            if (index <= mid) return search(cur.left, index);
            else return search(cur.right, index);
        }
    }

    public static void main(String[] args) throws IOException {
        // 빠른 입출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 입력
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        child = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) child[i] = new ArrayList<>();

        treeRange = new int[n + 1][2];
        order = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) continue;
            child[parent].add(i);
        }

        ett(1); // 루트부터 Euler Tour 수행

        Seg segtree = new Seg();

        for (int q = 0; q < m; q++) {
            st = new StringTokenizer(br.readLine());
            int ctype = Integer.parseInt(st.nextToken());
            if (ctype == 1) {
                int i = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                int s = treeRange[i][0] - 1;
                int e = treeRange[i][1] - 1;
                segtree.modify(segtree.root, s, e, w);
            } else {
                int i = Integer.parseInt(st.nextToken());
                bw.write(segtree.search(segtree.root, order[i]) + "\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
