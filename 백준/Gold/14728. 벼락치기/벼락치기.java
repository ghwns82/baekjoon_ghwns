import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 아이템 개수
        int t = sc.nextInt(); // 배낭의 최대 무게

        int[] preBag = new int[t + 1];

        for (int i = 0; i < n; i++) {
            int k = sc.nextInt(); // 무게
            int s = sc.nextInt(); // 가치

            int[] bag = Arrays.copyOf(preBag, t + 1); // deepcopy

            for (int j = k; j <= t; j++) {
                bag[j] = Math.max(preBag[j], preBag[j - k] + s);
            }

            preBag = bag; // 다음 반복을 위한 갱신
        }

        System.out.println(preBag[t]);
    }
}
