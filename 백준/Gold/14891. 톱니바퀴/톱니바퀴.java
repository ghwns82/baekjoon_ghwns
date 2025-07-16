import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] gears = new String[4];
        int[] up = new int[4]; // 각 톱니바퀴의 12시 방향 인덱스

        for (int i = 0; i < 4; i++) {
            gears[i] = sc.next();
        }

        int k = sc.nextInt();
        while (k-- > 0) {
            int start = sc.nextInt() - 1;
            int direction = sc.nextInt();

            int[] d = new int[4]; // 회전 방향
            d[start] = direction;

            // 왼쪽 확인
            for (int i = start - 1; i >= 0; i--) {
                if (d[i + 1] == 0) break;

                int rightGearLeftTooth = (up[i + 1] - 2 + 8) % 8;
                int leftGearRightTooth = (up[i] + 2) % 8;

                if (gears[i + 1].charAt(rightGearLeftTooth) != gears[i].charAt(leftGearRightTooth)) {
                    d[i] = -d[i + 1];
                } else {
                    break;
                }
            }

            // 오른쪽 확인
            for (int i = start + 1; i < 4; i++) {
                if (d[i - 1] == 0) break;

                int leftGearRightTooth = (up[i - 1] + 2) % 8;
                int rightGearLeftTooth = (up[i] - 2 + 8) % 8;

                if (gears[i].charAt(rightGearLeftTooth) != gears[i - 1].charAt(leftGearRightTooth)) {
                    d[i] = -d[i - 1];
                } else {
                    break;
                }
            }

            // 회전 반영
            for (int i = 0; i < 4; i++) {
                up[i] = (up[i] - d[i] + 8) % 8;
            }
        }

        // 결과 계산
        StringBuilder result = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            result.append(gears[i].charAt(up[i]));
        }
        System.out.println(Integer.parseInt(result.toString(), 2));
    }
}
