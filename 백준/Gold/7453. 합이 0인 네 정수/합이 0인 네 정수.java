import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 배열 크기 입력
        int n = Integer.parseInt(br.readLine().trim());
        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        int[] D = new int[n];

        // 배열 값 입력
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(fourSumZero(A, B, C, D));
    }

    public static long fourSumZero(int[] A, int[] B, int[] C, int[] D) {
        int n = A.length;

        // A + B와 C + D의 합을 저장할 배열 생성
        int[] sumAB = new int[n * n];
        int[] sumCD = new int[n * n];

        int idx = 0;

        // A + B의 모든 합 계산
        for (int a : A) {
            for (int b : B) {
                sumAB[idx++] = a + b;
            }
        }

        idx = 0;

        // C + D의 모든 합 계산
        for (int c : C) {
            for (int d : D) {
                sumCD[idx++] = c + d;
            }
        }

        // 두 배열 정렬
        Arrays.sort(sumAB);
        Arrays.sort(sumCD);

        // 투 포인터로 0에 가까운 합 계산
        long result = 0;
        int left = 0;
        int right = sumCD.length - 1;

        while (left < sumAB.length && right >= 0) {
            int sum = sumAB[left] + sumCD[right];

            if (sum == 0) {
                // sumAB[left]와 sumCD[right]의 같은 값의 빈도 계산
                long countAB = 1;
                long countCD = 1;

                // sumAB의 같은 값 개수 계산
                while (left + 1 < sumAB.length && sumAB[left] == sumAB[left + 1]) {
                    left++;
                    countAB++;
                }

                // sumCD의 같은 값 개수 계산
                while (right - 1 >= 0 && sumCD[right] == sumCD[right - 1]) {
                    right--;
                    countCD++;
                }

                // 결과에 곱 추가
                result += countAB * countCD;

                // 다음 값으로 이동
                left++;
                right--;
            } else if (sum < 0) {
                left++; // 합이 음수면 왼쪽 포인터 증가
            } else {
                right--; // 합이 양수면 오른쪽 포인터 감소
            }
        }

        return result;
    }
}