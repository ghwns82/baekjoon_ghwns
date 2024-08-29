import java.util.*;

public class Main {

    static int N, M;
    static int[][] city;
    static List<int[]> houses = new ArrayList<>();
    static List<int[]> chickenStores = new ArrayList<>();
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        city = new int[N][N];

        // 도시 정보 입력 및 집과 치킨집 위치 저장
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                city[r][c] = sc.nextInt();
                if (city[r][c] == 1) {
                    houses.add(new int[]{r, c});
                } else if (city[r][c] == 2) {
                    chickenStores.add(new int[]{r, c});
                }
            }
        }

        // 치킨집 조합 선택
        combination(new int[M], 0, 0);

        // 결과 출력
        System.out.println(result);
        sc.close();
    }

    // 치킨집 선택을 위한 조합 생성
    static void combination(int[] selected, int start, int count) {
        if (count == M) {
            // 선택된 치킨집 조합에 대해 치킨 거리 계산
            int totalDistance = 0;
            for (int[] house : houses) {
                int minDistance = Integer.MAX_VALUE;
                for (int idx : selected) {
                    int[] chicken = chickenStores.get(idx);
                    int distance = Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]);
                    minDistance = Math.min(minDistance, distance);
                }
                totalDistance += minDistance;
            }
            result = Math.min(result, totalDistance);
            return;
        }

        // 조합 생성
        for (int i = start; i < chickenStores.size(); i++) {
            selected[count] = i;
            combination(selected, i + 1, count + 1);
        }
    }
}