import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 수열의 길이
        int[] arr = new int[n];

        // 수열 입력
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int left = 0;
        int right = n - 1;
        int result = arr[left] + arr[right];

        while (left < right) {
            int sum = arr[left] + arr[right];

            // 절댓값이 더 작은 합으로 갱신
            if (Math.abs(result) > Math.abs(sum)) {
                result = sum;
            }

            // 투 포인터 이동
            if (sum > 0) {
                right--;
            } else if (sum < 0) {
                left++;
            } else {
                break; // sum == 0인 경우 최적의 값
            }
        }

        System.out.println(result);
    }
}
