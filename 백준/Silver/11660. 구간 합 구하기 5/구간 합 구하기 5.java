import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	private static StringBuilder output = new StringBuilder();

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		int n = Integer.parseInt(tokens.nextToken());
		int m = Integer.parseInt(tokens.nextToken());
		int arr[][] = new int[n][n];
		int ns[][] = new int[n + 1][1 + n];

		int x1, y1, x2, y2;
		for (int i = 0; i < n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(tokens.nextToken());
				ns[i + 1][j + 1] = arr[i][j] + ns[i][j + 1] + ns[i + 1][j] - ns[i][j];
			}
		}

		for (int i = 0; i < m; i++) {
			tokens = new StringTokenizer(input.readLine());
			x1 = Integer.parseInt(tokens.nextToken());
			y1 = Integer.parseInt(tokens.nextToken());
			x2 = Integer.parseInt(tokens.nextToken());
			y2 = Integer.parseInt(tokens.nextToken());
//			System.out.printf("%d %d %d %d ", x1,y1,x2,y2);
			int tmp = ns[x2][y2] + ns[x1 - 1][y1 - 1] - ns[x1 - 1][y2] - ns[x2][y1 - 1];
			output.append(tmp).append("\n");
		}
		System.out.println(output);
	}
}