import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 1. 모기 입장 시각과 퇴장 시각을 저장할 리스트
        List<Integer> enterTimes = new ArrayList<>();
        List<Integer> exitTimes = new ArrayList<>();
        
        int N = Integer.parseInt(br.readLine()); // 모기의 수
        
        // 2. 모기 입장/퇴장 시간을 모두 하나의 HashSet에 저장
        Set<Integer> times = new HashSet<>();
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int enter = Integer.parseInt(st.nextToken());
            int exit = Integer.parseInt(st.nextToken());
            enterTimes.add(enter);
            exitTimes.add(exit);
            
            // HashSet에 입장 시간과 퇴장 시간 추가
            times.add(enter);
            times.add(exit);
        }
        
        // HashSet을 정렬된 리스트로 변환
        List<Integer> sortedTimes = new ArrayList<>(times);
        Collections.sort(sortedTimes);
        
        // 2. 각 시간에 번호를 부여하여 HashMap에 저장
        Map<Integer, Integer> timeToIndex = new HashMap<>();
        for (int i = 0; i < sortedTimes.size(); i++) {
            timeToIndex.put(sortedTimes.get(i), i);
        }
        
        // 3. 정렬된 시간 개수만큼의 배열을 생성 (IMOS 방식)
        int[] imos = new int[sortedTimes.size()];
        
        // 4. 모기 입장 시각과 퇴장 시각을 이용하여 배열 업데이트
        for (int i = 0; i < N; i++) {
            int ha = timeToIndex.get(enterTimes.get(i)); // 입장 시각에 해당하는 인덱스
            int hb = timeToIndex.get(exitTimes.get(i));  // 퇴장 시각에 해당하는 인덱스
            imos[ha]++;  // 입장 시각에서 ++
            imos[hb]--;  // 퇴장 시각에서 --
        }
        
        // 5. IMOS 배열을 이용해 최대 모기 구간을 찾기
        int curMosquitoes = 0;
        int maxMosquitoes = 0;
        int maxStart = 0;
        int maxEnd = 0;
        int startTime = -1;

        for (int i = 0; i < imos.length; i++) {
            curMosquitoes += imos[i];
            
            // 모기가 최대인 구간을 찾음
            if (curMosquitoes > maxMosquitoes) {
                maxMosquitoes = curMosquitoes;
                maxStart = sortedTimes.get(i);
                startTime = sortedTimes.get(i);  // 현재 시작 시간
                maxEnd = 0;  // 새로 최대가 갱신되면 종료 시간 초기화
            }
            
            // 최대 모기 수가 유지되다가 줄어드는 시점에서 종료 시간을 기록
            if (curMosquitoes < maxMosquitoes && maxEnd == 0) {
                maxEnd = sortedTimes.get(i);  // 모기 수가 감소하는 첫 순간을 종료 시간으로 기록
            }
        }

        // 최대 모기 수가 마지막까지 유지되었을 때 maxEnd 업데이트
        if (maxEnd == 0) {
            maxEnd = sortedTimes.get(sortedTimes.size() - 1);
        }

        // 6. 결과 출력
        System.out.println(maxMosquitoes);
        System.out.println(maxStart + " " + maxEnd);
    }
}