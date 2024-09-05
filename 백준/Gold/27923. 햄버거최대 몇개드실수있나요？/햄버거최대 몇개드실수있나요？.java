import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	private static StringBuilder output = new StringBuilder();

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		int n = Integer.parseInt(tokens.nextToken());
		int k = Integer.parseInt(tokens.nextToken());
		int l = Integer.parseInt(tokens.nextToken());
		long[] bg_array = new long[n];
		long[] kola_ns = new long[n + 1];
		long[] kola_effect = new long[n];

		// input
		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < bg_array.length; i++) {
			bg_array[i] = Integer.parseInt(tokens.nextToken());
		}
		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < k; i++) {
			kola_ns[Integer.parseInt(tokens.nextToken())]++;
		}

		// create ns
		for (int i = 0; i < n; i++) {
			kola_ns[i + 1] += kola_ns[i];
		}
		for (int i = 0; i < n; i++) {
			if (i < l)
				kola_effect[i] = kola_ns[i + 1];
			else
				kola_effect[i] = kola_ns[i + 1] - kola_ns[i + 1 - l];
		}

		// grid search
		Arrays.sort(kola_effect);
//		System.out.println(Arrays.toString(kola_effect));
		Arrays.sort(bg_array);
//		System.out.println(Arrays.toString(bg_array));

		long result = 0;
		for (int i = 0; i < n; i++) {
			if (kola_effect[i]<=64) {
				result += bg_array[i] / (long)(Math.pow(2, kola_effect[i]));
			}
			
		}
		System.out.println(result);

	}
}