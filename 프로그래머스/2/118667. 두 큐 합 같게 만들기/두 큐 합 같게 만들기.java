import java.util.*;

public class Solution {
    public static int solution(int[] queue1, int[] queue2) {
        Map<Long, Integer> ns1 = new LinkedHashMap<>();
        Map<Long, Integer> ns2 = new LinkedHashMap<>();
        ns1.put(0L, 0);
        ns2.put(0L, 0);

        long hap1 = 0;
        for (int i = 0; i < queue1.length; i++) {
            hap1 += queue1[i];
            ns1.put(hap1, i + 1);
        }

        long hap2 = 0;
        for (int i = 0; i < queue2.length; i++) {
            hap2 += queue2[i];
            ns2.put(hap2, i + 1);
        }

        long hap = 0;
        for (int i = queue1.length - 1; i >= 0; i--) {
            hap -= queue1[i];
            ns2.put(hap, queue2.length + i);
        }

        hap = 0;
        for (int i = queue2.length - 1; i >= 0; i--) {
            hap -= queue2[i];
            ns1.put(hap, queue1.length + i);
        }

        if ((hap1 + hap2) % 2 != 0) {
            return -1;
        }

        int maxResult = queue1.length * 2 + queue2.length * 2 + 1;
        int result = maxResult;

        int cnt = 0;
        for (Map.Entry<Long, Integer> entry : ns1.entrySet()) {
            long key = entry.getKey();
            long key2 = (hap2 - hap1) / 2 + key;
            if (ns2.containsKey(key2)) {
                result = Math.min(result, ns1.get(key) + ns2.get(key2));
            }
            cnt++;
            if (cnt == queue1.length) break;
        }

        cnt = 0;
        for (Map.Entry<Long, Integer> entry : ns2.entrySet()) {
            long key = entry.getKey();
            long key2 = (hap1 - hap2) / 2 + key;
            if (ns1.containsKey(key2)) {
                result = Math.min(result, ns2.get(key) + ns1.get(key2));
            }
            cnt++;
            if (cnt == queue2.length) break;
        }

        if (result == maxResult) {
            return -1;
        }

        return result;
    }
}
