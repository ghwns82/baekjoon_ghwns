import java.io.*;
import java.util.*;

public class Main {
    static class Pair {
        int idx, val;
        Pair(int i, int v) { idx = i; val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        Pair[] a = new Pair[n];
        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(br.readLine().trim());
            a[i] = new Pair(i, v);
        }

        Arrays.sort(a, Comparator.comparingInt(p -> p.val));

        int[] runner = new int[n];
        int num = 0;
        for (Pair p : a) runner[p.idx] = num++;

        int unit = (int) Math.sqrt(n) + 1;
        int[] cnt = new int[n];
        int[] part = new int[n / unit + 1];

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int value = runner[i];
            int result = 0;

            int blockIdx = Math.floorDiv(value - 1, unit);
            for (int j = 0; j < blockIdx; j++) result += part[j];

            int start = Math.max(blockIdx * unit, 0);
            for (int j = start; j < value; j++) result += cnt[j];

            out.append(i - result + 1).append('\n');

            cnt[value] += 1;
            part[value / unit] += 1;
        }

        System.out.print(out.toString());
    }
}
