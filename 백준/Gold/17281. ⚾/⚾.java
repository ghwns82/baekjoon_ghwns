import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] iningList = new int[n][9];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                iningList[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int finalScore = 0;
        List<Integer> players = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            players.add(i);
        }

        for (List<Integer> perm : permutations(players)) {
            List<Integer> order = new ArrayList<>(perm.subList(0, 3));
            order.add(0);
            order.addAll(perm.subList(3, perm.size()));

            int score = 0;
            int index = 0;

            for (int[] ining : iningList) {
                int cntOut = 0;
                int s1 = 0, s2 = 0, s3 = 0;

                while (cntOut < 3) {
                    int result = ining[order.get(index)];
                    switch (result) {
                        case 1:
                            score += s3;
                            s3 = s2;
                            s2 = s1;
                            s1 = 1;
                            break;
                        case 2:
                            score += s3 + s2;
                            s3 = s1;
                            s2 = 1;
                            s1 = 0;
                            break;
                        case 3:
                            score += s3 + s2 + s1;
                            s3 = 1;
                            s2 = 0;
                            s1 = 0;
                            break;
                        case 4:
                            score += s3 + s2 + s1 + 1;
                            s3 = s2 = s1 = 0;
                            break;
                        default:
                            cntOut++;
                            break;
                    }
                    index = (index + 1) % 9;
                }
            }
            finalScore = Math.max(finalScore, score);
        }

        System.out.println(finalScore);
    }

    private static List<List<Integer>> permutations(List<Integer> list) {
        List<List<Integer>> result = new ArrayList<>();
        permuteHelper(list, 0, result);
        return result;
    }

    private static void permuteHelper(List<Integer> list, int start, List<List<Integer>> result) {
        if (start == list.size()) {
            result.add(new ArrayList<>(list));
        } else {
            for (int i = start; i < list.size(); i++) {
                Collections.swap(list, i, start);
                permuteHelper(list, start + 1, result);
                Collections.swap(list, i, start);
            }
        }
    }
}