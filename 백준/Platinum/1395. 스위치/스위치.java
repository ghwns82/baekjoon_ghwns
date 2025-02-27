import java.io.*;
import java.util.*;

class Node {
    int start, end, data, lazy;
    Node left, right;

    Node(int start, int end) {
        this.start = start;
        this.end = end;
        this.data = 0;
        this.lazy = 0;
    }
}

class Seg {
    Node root;

    Seg(int n) {
        root = create(0, n - 1);
    }

    private Node create(int start, int end) {
        if (start == end) {
            return new Node(start, end);
        }
        int mid = (start + end) / 2;
        Node node = new Node(start, end);
        node.left = create(start, mid);
        node.right = create(mid + 1, end);
        return node;
    }

    private void propagate(Node node) {
        if (node.lazy == 0) return;
        node.data = (node.end - node.start + 1) - node.data;
        if (node.left != null) {
            node.left.lazy ^= 1;
            node.right.lazy ^= 1;
        }
        node.lazy = 0;
    }

    void modify(Node node, int s, int e) {
        propagate(node);
        if (s > node.end || e < node.start) return;
        if (s <= node.start && node.end <= e) {
            node.lazy ^= 1;
            propagate(node);
            return;
        }
        modify(node.left, s, e);
        modify(node.right, s, e);
        node.data = node.left.data + node.right.data;
    }

    int search(Node node, int s, int e) {
        propagate(node);
        if (s > node.end || e < node.start) return 0;
        if (s <= node.start && node.end <= e) return node.data;
        return search(node.left, s, e) + search(node.right, s, e);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader buffed = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(buffed.readLine());
        int n = Integer.parseInt(token.nextToken());
        int m = Integer.parseInt(token.nextToken());

        Seg segtree = new Seg(n);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            token = new StringTokenizer(buffed.readLine());
            int o = Integer.parseInt(token.nextToken());
            int s = Integer.parseInt(token.nextToken()) - 1;
            int t = Integer.parseInt(token.nextToken()) - 1;

            if (o == 0) {
                segtree.modify(segtree.root, s, t);
            } else {
                sb.append(segtree.search(segtree.root, s, t)).append("\n");
            }
        }
        System.out.print(sb);
    }
}