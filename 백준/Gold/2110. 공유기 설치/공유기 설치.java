import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main {

  private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
  private static StringBuilder output = new StringBuilder();
  private static StringTokenizer tokens;

  public static void main(String[] args) throws IOException {

    tokens = new StringTokenizer(input.readLine());
    int n = Integer.parseInt(tokens.nextToken());
    int c = Integer.parseInt(tokens.nextToken());
    int[] house = new int[n];
    for (int index = 0; index < n; index++) {
      tokens = new StringTokenizer(input.readLine());
      house[index] = Integer.parseInt(tokens.nextToken());
    }
    Arrays.sort(house);
    int left = 0;
    int right = house[n - 1] - house[0];
    int mid;
    int success = 0;
    ArrayList<Integer> work; 
    while (left < right) {
      mid = (left + right + 1) / 2;
      // System.out.println(left + right + 1 + " " +left+" "+ mid+" "+right);
      work = new ArrayList();
      work.add(house[0]);
      boolean s = true;
      for (int h : house) {
        if (h - work.get(work.size() - 1) >= mid) {
          work.add(h);
        }
        if (work.size() == c) {
          left = mid;
          success = mid;
          s = false;
          break;
        }
      }
      if (s==true) {
        right = mid - 1;
      }
    }
    System.out.println(success);
  }

}