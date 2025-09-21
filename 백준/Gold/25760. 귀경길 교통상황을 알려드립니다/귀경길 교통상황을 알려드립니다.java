import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<Set<Integer>> graph;
    static int[] cars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) graph.add(new HashSet<>());

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        cars = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cars[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> result = dfs(0);

        int answer = 0, last = 0;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) > 0) {
                answer = Math.max(answer, i);
                answer += result.get(i);
            }
        }

        System.out.println(answer);
    }

    static List<Integer> dfs(int point) {
        List<Integer> order = new ArrayList<>();
        order.add(cars[point]);

        while (graph.get(point).size() == 1) {
            int next = graph.get(point).iterator().next();
            graph.get(point).remove(next);
            graph.get(next).remove(point);

            order.add(cars[next]);
            point = next;
        }

        if (graph.get(point).isEmpty()) {
            return order;
        }

        int index = order.size();

        // 복사본을 순회해야 ConcurrentModification 방지
        List<Integer> neighbors = new ArrayList<>(graph.get(point));
        for (int next : neighbors) {
            graph.get(point).remove(next);
            graph.get(next).remove(point);

            List<Integer> subresult = dfs(next);

            for (int i = 0; i < subresult.size(); i++) {
                if (order.size() > index + i) {
                    order.set(index + i, order.get(index + i) + subresult.get(i));
                } else {
                    order.add(subresult.get(i));
                }
            }
        }

        return order;
    }
}
