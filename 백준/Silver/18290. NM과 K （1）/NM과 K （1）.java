import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	private static StringBuilder output = new StringBuilder();
	static int result = Integer.MIN_VALUE;
	static int[][] d = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
//	static ArrayList<Integer> arrlist = new ArrayList<>();
	public static void rec(int depth, int last_x, int last_y, int sum, int n, int m, boolean visited[][], int arr[][]) {
		if (depth == 0) {
//			System.out.printf("%d, %s\n", sum, arrlist.toString());
			result = Math.max(result, sum);
			return;
		}
		int i = last_x;
		int j = last_y;

		while (i < n) {
			

			boolean flag = true;
			for (int[] dd : d) {
				int di = i + dd[0];
				int dj = j + dd[1];
//					System.out.println(di);
//					System.out.println(dj);
				if (0 <= di && di < n && 0 <= dj && dj < m) {
					if (visited[di][dj]) {
						flag = false;
						break;
					}

				}
			}
//				System.out.println(flag);
			if (flag) {
				visited[i][j] = true;
				
//				System.out.println(i);
//				arrlist.add(i);
//				arrlist.add(j);
//				System.out.printf("%d %d %d %d\n",i,j,i+(j+1)/m,(j+1)%m);
				rec(depth - 1, i + (j + 1) / m, (j + 1) % m, sum + arr[i][j], n, m, visited, arr);
//				arrlist.removeLast();
//				arrlist.removeLast();
				visited[i][j] = false;

			}
			j++;
			i=i+j/m;
			j%=m;

		}
	}

	

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		int n = Integer.parseInt(tokens.nextToken());
		int m = Integer.parseInt(tokens.nextToken());
		int k = Integer.parseInt(tokens.nextToken());

		int arr[][] = new int[n][m];
		boolean visited[][] = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}

		rec(k, 0, 0, 0, n, m, visited, arr);
		System.out.println(result);
	}
}