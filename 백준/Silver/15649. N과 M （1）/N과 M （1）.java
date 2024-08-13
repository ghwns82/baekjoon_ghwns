import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	private static StringBuilder output = new StringBuilder();

	static void recursion(int[] arr, Set<Integer> last, int n, int m, int k) {
		if (k >= m) {
			for (int a : arr) {
				output.append(a).append(" ");
				
			}
			output.append("\n");
			return;
		}

		for (int i = 1; i <= n; i++) {
			if (!last.contains(i)) {
				last.add(i);
				arr[k]=i;
				recursion(arr, last, n, m, k+1);
				last.remove(i);
			}
			
		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		int n = Integer.parseInt(tokens.nextToken());
		int m = Integer.parseInt(tokens.nextToken());
		int arr[] = new int[m];
		Set<Integer> last = new HashSet<>();
		recursion(arr,last,n,m,0);
		System.out.println(output);
	}
}