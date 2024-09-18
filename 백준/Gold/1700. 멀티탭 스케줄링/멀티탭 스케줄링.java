import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());  // 플러그 개수
        int k = Integer.parseInt(st.nextToken());  // 기기 수

        // 기기 배열 입력
        int[] arr = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // defaultdict 역할을 하는 HashMap
        HashMap<Integer, Deque<Integer>> freq = new HashMap<>();
        // 현재 사용 중인 기기를 저장하는 HashSet
        HashSet<Integer> using = new HashSet<>();

        // 각 기기의 인덱스를 저장하는 deque 초기화
        for (int i = 0; i < k; i++) {
            freq.putIfAbsent(arr[i], new ArrayDeque<>());
            freq.get(arr[i]).addFirst(i);
        }

        int cnt = 0;  // 플러그를 뽑은 횟수

        for (int i = 0; i < k; i++) {
            freq.get(arr[i]).pollLast();  // 현재 인덱스 삭제

            if (using.size() < n || using.contains(arr[i])) {
                using.add(arr[i]);
                continue;
            }

            List<int[]> tmp = new ArrayList<>();
            cnt++;

            for (int j : using) {
                if (freq.get(j).isEmpty()) {
                    tmp.add(new int[] { k, j });
                } else {
                    tmp.add(new int[] { freq.get(j).peekLast(), j });
                }
            }

            // 가장 나중에 사용될 기기 또는 더 이상 사용되지 않을 기기 선택
            int[] max = tmp.stream().max(Comparator.comparingInt(a -> a[0])).get();
            int item = max[1];

            using.remove(item);  // 해당 기기 제거
            using.add(arr[i]);  // 새로운 기기 추가
        }

        System.out.println(cnt);  // 플러그 뽑은 횟수 출력
    }
}