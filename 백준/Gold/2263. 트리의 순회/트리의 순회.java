import java.io.*;
import java.util.*;

public class Main {
    static int[] inorder, postorder;
    static Map<Integer, Integer> where = new HashMap<>();
    static int postIdx;
    static StringBuilder sb = new StringBuilder();

    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        inorder = new int[n];
        postorder = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
            where.put(inorder[i], i);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        postIdx = n - 1;
        Node root = search(0, n - 1);
        preorder(root);
        System.out.println(sb.toString());
    }

    static Node search(int s, int e) {
        if (s > e) return null;

        int data = postorder[postIdx--];
        Node node = new Node(data);

        int index = where.get(data);

        node.right = search(index + 1, e);
        node.left = search(s, index - 1);

        return node;
    }

    static void preorder(Node node) {
        if (node == null) return;
        sb.append(node.data).append(" ");
        preorder(node.left);
        preorder(node.right);
    }
}