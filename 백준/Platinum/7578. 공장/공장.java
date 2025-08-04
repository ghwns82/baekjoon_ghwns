import java.util.*;

public class Main {
    static int[] seg;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        sc.nextLine(); // 개행 제거

        // 첫 번째 문자열 입력 (dic 만들기용)
        String[] firstInput = sc.nextLine().split(" ");
        Map<String, Integer> dic = new HashMap<>();
        for (String s : firstInput) {
            dic.putIfAbsent(s, dic.size());
        }

        // 세그먼트 트리 초기화
        seg = new int[n * 2 + 1];

        // 두 번째 문자열 입력
        String[] secondInput = sc.nextLine().split(" ");
        long result = 0;
        for (String s : secondInput) {
            int num = dic.get(s);
            result += search(num, n - 1);
            modify(num, 1);
        }

        System.out.println(result);
    }

    // 세그먼트 트리 갱신 함수
    static void modify(int idx, int val) {
        int index = n + idx;
        while (index > 0) {
            seg[index] += val;
            index >>= 1;
        }
    }

    // 세그먼트 트리 구간합 조회 함수
    static int search(int l, int r) {
        int res = 0;
        l += n;
        r += n;
        while (l <= r) {
            if (l == r) {
                res += seg[l];
                break;
            }
            if ((l & 1) == 1) {
                res += seg[l++];
            }
            if ((r & 1) == 0) {
                res += seg[r--];
            }
            l >>= 1;
            r >>= 1;
        }
        return res;
    }
}
