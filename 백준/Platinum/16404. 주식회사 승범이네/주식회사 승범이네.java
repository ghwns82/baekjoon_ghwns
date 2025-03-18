import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static ArrayList<Integer>[] child;
    static int[] order;
    static int[][] tree_range;
    static int visited = 0;
    static SegmentTree segtree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        child = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            child[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int index = 1; index <= n; index++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) continue;
            child[parent].add(index);
        }

        order = new int[n + 1];
        tree_range = new int[n + 1][2];

        ett(1);

        segtree = new SegmentTree();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int ctype = Integer.parseInt(st.nextToken());
            if (ctype == 1) {
                int idx = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                int s = tree_range[idx][0];
                int e = tree_range[idx][1];
                segtree.modify(segtree.root, s - 1, e - 1, w);
            } else {
                int idx = Integer.parseInt(st.nextToken());
                bw.write(segtree.search(segtree.root, order[idx]) + "\n");
            }
        }
        bw.flush();
    }

    static void ett(int person) {
        order[person] = visited;
        visited++;
        tree_range[person][0] = visited;
        for (int c : child[person]) {
            ett(c);
        }
        tree_range[person][1] = visited;
    }

    static class Node {
        int start, end;
        long data, lazy;
        Node left, right;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
            this.data = 0;
            this.lazy = 0;
            this.left = null;
            this.right = null;
        }
    }

    static class SegmentTree {
        Node root;

        SegmentTree() {
            root = create(0, n - 1);
        }

        Node create(int start, int end) {
            Node node = new Node(start, end);
            if (start != end) {
                int mid = (start + end) / 2;
                node.left = create(start, mid);
                node.right = create(mid + 1, end);
            }
            return node;
        }

        void modify(Node cur, int s, int e, int k) {
            if (cur.start == s && cur.end == e) {
                cur.lazy += (long) (e - s + 1) * k;
                return;
            } else {
                cur.data += (long) (e - s + 1) * k;
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
            if (cur.lazy != 0) {
                cur.data += cur.lazy;
                if (cur.left != null) {
                    int mid = (cur.start + cur.end) / 2;
                    cur.left.lazy += cur.lazy / (cur.end - cur.start + 1) * (mid - cur.start + 1);
                    cur.right.lazy += cur.lazy / (cur.end - cur.start + 1) * (cur.end - mid);
                }
                cur.lazy = 0;
            }
            if (cur.start == cur.end && cur.start == index) {
                return cur.data;
            }
            int mid = (cur.start + cur.end) / 2;
            if (mid < index) {
                return search(cur.right, index);
            } else {
                return search(cur.left, index);
            }
        }
    }
}
