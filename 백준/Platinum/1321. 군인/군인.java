import java.io.*;
import java.util.*;

class Node {
    int data; // 구간 합
    int start, end; // 구간 범위
    Node left, right; // 좌, 우 자식 노드

    public Node(int start, int end) {
        this.start = start;
        this.end = end;
        this.data = 0;
    }
}

class SegmentTree {
    Node root;

    // Segment Tree 생성
    public Node create(int[] arr, int start, int end) {
        Node node = new Node(start, end);
        if (start == end) {
            node.data = arr[start];
        } else {
            int mid = (start + end) / 2;
            node.left = create(arr, start, mid);
            node.right = create(arr, mid + 1, end);
            node.data = node.left.data + node.right.data;
        }
        return node;
    }

    // Segment Tree 수정
    public void modify(Node node, int index, int value) {
        node.data += value;
        if (node.start == node.end) {
            return;
        }
        int mid = (node.start + node.end) / 2;
        if (index <= mid) {
            modify(node.left, index, value);
        } else {
            modify(node.right, index, value);
        }
    }

    // 특정 군번이 속한 부대 찾기
    public int search(Node node, int index) {
        if (node.start == node.end) {
            return node.start;
        }
        if (index > node.left.data) {
            return search(node.right, index - node.left.data);
        } else {
            return search(node.left, index);
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 부대의 개수
        int[] army = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            army[i] = Integer.parseInt(st.nextToken());
        }

        int q = Integer.parseInt(br.readLine()); // 명령의 개수
        SegmentTree tree = new SegmentTree();
        tree.root = tree.create(army, 0, n - 1);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 1) {
                int unit = Integer.parseInt(st.nextToken()) - 1; // 부대 번호 (0-indexed)
                int value = Integer.parseInt(st.nextToken()); // 증원/감원 수
                tree.modify(tree.root, unit, value);
            } else {
                int soldierNumber = Integer.parseInt(st.nextToken()); // 군번
                bw.write((tree.search(tree.root, soldierNumber) + 1) + "\n"); // 1-indexed 출력
            }
        }
        bw.flush();
        bw.close();
    }
}