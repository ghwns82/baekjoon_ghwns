import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Task[] tasks = new Task[n];

        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");
            int t = Integer.parseInt(parts[0]);
            int s = Integer.parseInt(parts[1]);
            tasks[i] = new Task(t, s);
        }

        // 마감 시간을 기준으로 내림차순 정렬
        Arrays.sort(tasks, (a, b) -> b.s - a.s);

        int now = 1_000_000;

        for (Task task : tasks) {
            now = Math.min(now, task.s) - task.t;
        }

        // 결과 출력
        if (now < 0) {
            System.out.println(-1);
        } else {
            System.out.println(now);
        }
    }

    // 작업 클래스
    static class Task {
        int t; // 소요 시간
        int s; // 마감 시간

        Task(int t, int s) {
            this.t = t;
            this.s = s;
        }
    }
}
