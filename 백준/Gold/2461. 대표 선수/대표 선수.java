import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] school = new int[n][m];
        int[] indexes = new int[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                school[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(school[i]);
            pq.offer(new int[]{school[i][0], i});
        }

        int maxValue = Arrays.stream(school).mapToInt(row -> row[0]).max().orElse(0);
        int result = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int value = current[0];
            int classIndex = current[1];

            result = Math.min(result, maxValue - value);

            indexes[classIndex]++;
            int nextIndex = indexes[classIndex];
            if (nextIndex >= m) {
                break;
            }

            maxValue = Math.max(maxValue, school[classIndex][nextIndex]);
            pq.offer(new int[]{school[classIndex][nextIndex], classIndex});
        }

        System.out.println(result);
    }
}