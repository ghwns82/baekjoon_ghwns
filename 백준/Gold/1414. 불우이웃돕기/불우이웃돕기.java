import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        List<Edge> edges = new ArrayList<>();
        int totalCableLength = 0;

        // 랜선 정보를 입력 받음
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                char ch = line.charAt(j);
                if (ch != '0') {
                    int length = getCableLength(ch);
                    edges.add(new Edge(i, j, length));
                    totalCableLength += length;
                }
            }
        }

        // MST를 찾기 위한 초기 설정
        Collections.sort(edges);
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        int mstLength = 0;
        int edgeCount = 0;

        // 크루스칼 알고리즘을 사용하여 MST 길이 계산
        for (Edge edge : edges) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                mstLength += edge.length;
                edgeCount++;
            }
        }

        // 모든 컴퓨터가 연결되어 있는지 확인
        if (edgeCount == N - 1) {
            System.out.println(totalCableLength - mstLength);
        } else {
            System.out.println(-1);
        }
    }

    // 각 문자의 길이를 반환
    static int getCableLength(char ch) {
        if ('a' <= ch && ch <= 'z') {
            return ch - 'a' + 1;
        } else if ('A' <= ch && ch <= 'Z') {
            return ch - 'A' + 27;
        }
        return 0;
    }

    // 유니온-파인드 알고리즘 (Find)
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // 유니온-파인드 알고리즘 (Union)
    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x;
        }
    }

    // 간선 클래스 정의
    static class Edge implements Comparable<Edge> {
        int from, to, length;

        public Edge(int from, int to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
        }

        @Override
        public int compareTo(Edge o) {
            return this.length - o.length;
        }
    }
}