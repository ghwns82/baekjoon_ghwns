import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        Map<Integer, List<Integer>> graphOut = new HashMap<>();
        Map<Integer, List<Integer>> graphIn = new HashMap<>();
        Set<Integer> points = new HashSet<>();

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            graphOut.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            graphIn.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
            points.add(a);
            points.add(b);
        }

        int created = 0;
        for (int point : points) {
            if (!graphIn.containsKey(point) && graphOut.getOrDefault(point, new ArrayList<>()).size() >= 2) {
                if (created == 0 || graphOut.get(created).size() < graphOut.get(point).size()) {
                    created = point;
                }
            }
        }

        int[] result = new int[4];
        result[0] = created;

        for (int start : graphOut.get(created)) {
            int next = start;
            Set<Integer> visited = new HashSet<>();
            while (true) {
                if (graphOut.getOrDefault(next, new ArrayList<>()).size() == 2) {
                    result[3]++;
                    break;
                } else if (graphOut.getOrDefault(next, new ArrayList<>()).size() == 1) {
                    if (visited.contains(next)) {
                        result[1]++;
                        break;
                    }
                    visited.add(next);
                    next = graphOut.get(next).get(0);
                } else {
                    result[2]++;
                    break;
                }
            }
        }

        return result;
    }
}
