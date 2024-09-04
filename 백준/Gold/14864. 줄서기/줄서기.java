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
		boolean visited[] = new boolean[n*2];
		int[] tmp = new int[n + 1];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = i;
		}
		int[] result = new int[n + 1];
		for (int i = 0; i < result.length; i++) {
			result[i] = 1;
		}
		for (int i = 0; i < m; i++) {
			tokens = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			tmp[b] -= 1;
			result[a] += 1;
		}
		for (int i = 1; i < n+1; i++) {
			result[i]+=tmp[i];
		}
		boolean tmp2 = false;
		for (int elm : result) {
			if (visited[elm]==true) {
				tmp2=true;
				break;
			}
			visited[elm] = true;
		}
		if (tmp2==true) {
			System.out.println(-1);
		} else {
			for (int i = 1; i < result.length; i++) {
				output.append(result[i]-1+" ");
			}
			System.out.println(output);
		}
		

	}
}