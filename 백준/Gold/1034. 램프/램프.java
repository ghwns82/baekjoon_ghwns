import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 줄 수
        int m = sc.nextInt(); // 열 수
        sc.nextLine(); // 개행 처리

        String[] lamps = new String[n];
        for (int i = 0; i < n; i++) {
            lamps[i] = sc.nextLine();
        }

        int onoff = sc.nextInt();

        Map<String, Integer> memo = new HashMap<>();

        for (String lamp : lamps) {
            List<Integer> zeros = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                if (lamp.charAt(i) == '0') {
                    zeros.add(i);
                }
            }

            // 0의 개수가 onoff와 같은 짝수/홀수이고, 켤 수 있는 범위 내라면
            if (zeros.size() % 2 == onoff % 2 && zeros.size() <= onoff) {
                // 키로 쓰기 위해 0의 위치를 문자열로 변환
                String key = zeros.toString();  // 예: "[0, 3, 5]"
                memo.put(key, memo.getOrDefault(key, 0) + 1);
            }
        }

        if (memo.isEmpty()) {
            System.out.println(0);
        } else {
            int max = Collections.max(memo.values());
            System.out.println(max);
        }
    }
}
