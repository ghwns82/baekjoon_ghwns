import java.io.*;
import java.util.*;

public class Main {
    static int N, M;

    static int check(int[] arr, int maxSum) {
        int cnt = 1, groupSum = 0;
        for (int num : arr) {
            if (groupSum + num > maxSum) {
                cnt++;
                groupSum = num;
            } else {
                groupSum += num;
            }
        }
        return cnt;
    }

    static List<Integer> divide_groups(int[] arr, int maxSum, int M) {
        List<Integer> groups = new ArrayList<>();
        int currentSum = 0, currentCount = 0;
        int extraGroups = check(arr, maxSum) - M;
        
        for (int num : arr) {
            if (currentSum + num > maxSum || (extraGroups > 0 && groups.size() + (arr.length - groups.stream().mapToInt(Integer::intValue).sum()) > M)) {
                groups.add(currentCount);
                currentSum = num;
                currentCount = 1;
                extraGroups--;
            } else {
                currentSum += num;
                currentCount++;
            }
        }
        groups.add(currentCount);
        
        while (groups.size() < M) {
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i) > 1) {
                    groups.set(i, groups.get(i) - 1);
                    groups.add(i + 1, 1);
                    if (groups.size() == M) break;
                }
            }
        }
        return groups;
    }

    static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int low = Arrays.stream(arr).max().getAsInt();
        int high = Arrays.stream(arr).sum();
        int result = high;
        
        while (low <= high) {
            int mid = (low + high) / 2;
            if (check(arr, mid) <= M) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        List<Integer> bestGroups = divide_groups(arr, result, M);
        
        System.out.println(result);
        for (int i = 0; i < bestGroups.size(); i++) {
            System.out.print(bestGroups.get(i));
            if (i < bestGroups.size() - 1) System.out.print(" ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        solve();
    }
}