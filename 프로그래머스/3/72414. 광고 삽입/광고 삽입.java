import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playSeconds = strToInt(play_time);
        int advSeconds = strToInt(adv_time);
        long[] imos = new long[playSeconds + 2]; // 최대 360,000 초 이상

        for (String log : logs) {
            String[] times = log.split("-");
            int start = strToInt(times[0]);
            int end = strToInt(times[1]);
            imos[start] += 1;
            imos[end] -= 1;
        }

        // 누적 시청자 수 계산
        for (int i = 1; i < imos.length; i++) {
            imos[i] += imos[i - 1];
        }

        // 누적 재생시간 계산
        for (int i = 1; i < imos.length; i++) {
            imos[i] += imos[i - 1];
        }

        long maxTime = imos[advSeconds - 1];
        int startTime = 0;

        for (int i = advSeconds; i < playSeconds; i++) {
            long current = imos[i] - imos[i - advSeconds];
            if (current > maxTime) {
                maxTime = current;
                startTime = i - advSeconds + 1;
            }
        }

        return intToStr(startTime);
    }

    // HH:MM:SS -> 초(int) 변환
    private int strToInt(String time) {
        String[] parts = time.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int s = Integer.parseInt(parts[2]);
        return h * 3600 + m * 60 + s;
    }

    // 초(int) -> HH:MM:SS 변환
    private String intToStr(int time) {
        int h = time / 3600;
        time %= 3600;
        int m = time / 60;
        int s = time % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}
