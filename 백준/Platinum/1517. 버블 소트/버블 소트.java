import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static int[] seg;

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        // 좌표 압축
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        Map<Integer, Integer> map = new HashMap<>();
        int index = 0;
        for (int num : sorted) {
            if (!map.containsKey(num)) {
                map.put(num, index++);
            }
        }
        for (int i = 0; i < n; i++) {
            arr[i] = map.get(arr[i]);
        }

        // 세그먼트 트리 초기화
        seg = new int[n * 2];

        long result = 0;
        for (int i = 0; i < n; i++) {
            int val = arr[i];
            if (val < n - 1) {
                result += search(val + 1, n - 1);
            }
            modify(val, 1);
        }

        System.out.println(result);
    }

    // 세그먼트 트리 업데이트
    static void modify(int pos, int value) {
        pos += n;
        while (pos > 0) {
            seg[pos] += value;
            pos >>= 1;
        }
    }

    // 세그먼트 트리 구간 합 조회
    static int search(int left, int right) {
        left += n;
        right += n;
        int res = 0;
        while (left <= right) {
            if (left == right) {
                res += seg[left];
                break;
            }
            if ((left & 1) == 1) {
                res += seg[left++];
            }
            if ((right & 1) == 0) {
                res += seg[right--];
            }
            left >>= 1;
            right >>= 1;
        }
        return res;
    }
}
