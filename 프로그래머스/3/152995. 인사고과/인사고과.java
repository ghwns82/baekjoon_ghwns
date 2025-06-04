import java.util.*;

class Solution {
    class Node {
        int data;
        int start, end;
        Node left, right;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
            this.data = 0;
        }
    }

    class MergeSortTree {
        Node root;
        List<Integer> arr;
        Map<Integer, Integer> data;

        MergeSortTree(int start, int end, List<Integer> arr, Map<Integer, Integer> data) {
            this.arr = arr;
            this.data = data;
            root = create(start, end);
        }

        Node create(int start, int end) {
            Node nd = new Node(start, end);
            if (start == end) {
                nd.data = data.get(arr.get(start));
            } else {
                int mid = (start + end) / 2;
                nd.left = create(start, mid);
                nd.right = create(mid + 1, end);
                nd.data = Math.max(nd.left.data, nd.right.data);
            }
            return nd;
        }

        boolean search(int index, int value, Node node) {
            if (index == node.start) {
                return node.data > value;
            } else {
                int mid = (node.start + node.end) / 2;
                if (index > mid) {
                    return search(index, value, node.right);
                } else {
                    return search(mid + 1, value, node.right) || search(index, value, node.left);
                }
            }
        }
    }

    public int solution(int[][] scores) {
        Map<Integer, Integer> data = new HashMap<>();
        for (int[] score : scores) {
            int a = score[0], b = score[1];
            data.put(a, Math.max(data.getOrDefault(a, 0), b));
        }

        List<Integer> arr = new ArrayList<>(data.keySet());
        Collections.sort(arr);
        // System.out.println("arr: " + arr);

        MergeSortTree mst = new MergeSortTree(0, arr.size() - 1, arr, data);

        List<Integer> rank = new ArrayList<>();
        int a0 = scores[0][0], b0 = scores[0][1];
        int index0 = Collections.binarySearch(arr, a0)+1;
        
//         System.out.println(index0);
//         System.out.println("a0 != arr.get(last): " + (a0 != arr.get(arr.size() - 1)));
//         System.out.println("mst.search(index0, b0, mst.root): " + mst.search(index0, b0, mst.root));

        
        if (a0 != arr.get(arr.size() - 1) && mst.search(index0, b0, mst.root)) {
            return -1;
        }

        for (int[] score : scores) {
            int a = score[0], b = score[1];
            if (a == arr.get(arr.size() - 1)) {
                rank.add(a + b);
                continue;
            }
            int index = Collections.binarySearch(arr, a)+1;
            boolean r = mst.search(index, b, mst.root);
            if (!r) {
                rank.add(a + b);
            }
        }

        rank.sort(Collections.reverseOrder());
        // System.out.println("rank: " + rank);

        int targetSum = scores[0][0] + scores[0][1];
        int answer = rank.indexOf(targetSum) + 1;
        // System.out.println("rank index: " + rank.indexOf(targetSum));
        return answer;
    }
}
