public class Solution {
    public static int check(String[] place) {
        int[][] direction = {{0,1},{1,0},{0,-1},{-1,0}};
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int pcnt = 0;
                for (int[] d : direction) {
                    int dx = i + d[0];
                    int dy = j + d[1];
                    if (0 <= dx && dx < 5 && 0 <= dy && dy < 5 && place[dx].charAt(dy) == 'P') {
                        pcnt++;
                    }
                }
                if ((place[i].charAt(j) == 'P' && pcnt > 0) || (pcnt > 1 && place[i].charAt(j) == 'O')) {
                    return 0;
                }
            }
        }
        return 1;
    }

    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < places.length; i++) {
            answer[i] = check(places[i]);
        }
        return answer;
    }
}
