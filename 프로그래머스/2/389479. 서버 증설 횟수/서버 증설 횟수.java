import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public int solution(int[] players, int m, int k) {
        int cnt = 0;
        Deque<Integer> liveServer = new LinkedList<>();
        
        for (int i = 0; i < players.length; i++) {
            while (!liveServer.isEmpty() && liveServer.peekFirst() == i) {
                liveServer.pollFirst();
            }
            
            int diff = (players[i] / m) - liveServer.size();
            
            if (diff > 0) {
                cnt += diff;
                for (int j = 0; j < diff; j++) {
                    liveServer.add(i + k);
                }
            }
        }
        
        return cnt;
    }
}
