import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	private static StringBuilder output = new StringBuilder();
	static int arr[];

	public static void main(String[] args) throws IOException {
		init();
	}

	private static void init() throws IOException {
		tokens = new StringTokenizer(input.readLine());
		int n = Integer.parseInt(tokens.nextToken());
		arr = new int[26];
		for (int tt = 0; tt < n; tt++) {
			tokens = new StringTokenizer(input.readLine());
			String word = tokens.nextToken();
			int digit = 1;
			for (int i = word.length() - 1; i >= 0; i--) {
				arr[word.charAt(i) - 'A'] += digit;
				digit *= 10;
			}
		}
		Arrays.sort(arr);
		int result = 0;
		int number = 9;
		for (int i = 25; i >=0; i--) {
			result += arr[i]*number;
			number--;
		}
		System.out.println(result);

	}
}