import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	private static StringBuilder output = new StringBuilder();
	static int n, limit;
	static int bag_old[];
	static int bag_new[];

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		int tcs = Integer.parseInt(tokens.nextToken());
		for (int tc = 1; tc <= tcs; tc++) {
			init();
			output.append("#"+tc).append(" ").append(yummyBurger()).append("\n");
		}
		System.out.println(output);

	}

	private static void init() throws IOException {
		tokens = new StringTokenizer(input.readLine());
		n = Integer.parseInt(tokens.nextToken());
		limit = Integer.parseInt(tokens.nextToken());
		bag_old = new int[limit + 1];
	}

	private static int yummyBurger() throws IOException {
		for (int i = 0; i < n; i++) {
			tokens = new StringTokenizer(input.readLine());
			int score = Integer.parseInt(tokens.nextToken());
			int cal = Integer.parseInt(tokens.nextToken());
			bag_new = new int[limit + 1];
			for (int j = 0; j < bag_new.length; j++) {
				if (j >= cal && (bag_old[j - cal] + score > bag_old[j]))
					bag_new[j] = bag_old[j - cal] + score;
				else
					bag_new[j] = bag_old[j];
			}
			bag_old = bag_new;
		}
		return bag_new[limit];
		
	}
}