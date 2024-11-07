import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 받기
        int h = scanner.nextInt();
        int w = scanner.nextInt();

        // 각 높이에 맞게 블록 위치 저장
        List<List<Integer>> wall = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            wall.add(new ArrayList<>());
        }

        // 블록 입력 및 위치 저장
        for (int i = 0; i < w; i++) {
            int v = scanner.nextInt();
            for (int j = 0; j < v; j++) {
                wall.get(j).add(i);
            }
        }

        int result = 0;
        // 빗물 계산
        for (List<Integer> level : wall) {
            for (int i = 0; i < level.size() - 1; i++) {
                result += level.get(i + 1) - level.get(i) - 1;
            }
        }

        System.out.println(result);
        scanner.close();
    }
}