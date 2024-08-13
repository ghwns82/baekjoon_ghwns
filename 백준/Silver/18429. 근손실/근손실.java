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
	static int result = 0;

	static void recursion(Set<Integer> last, int n, int k, int index, int stack, int myfit, int[] arr) {
		if (index == n) {
			if (stack == 0) {
				result += 1;
			}
//			System.out.println("result "+result+" stack: "+stack);
//			System.out.println();
			return;
		}
		for (int i = 0; i < n; i++) {
			if (!last.contains(i)) {
				last.add(i);
				
				if (index==0) {stack=0; myfit=500;}
				myfit += (arr[i] - k);
//				System.out.println(index+" "+arr[i]+" "+stack);
				if (myfit<500) {
					recursion(last, n, k, index + 1, stack+1, myfit, arr);
				}else {
					recursion(last, n, k, index + 1, stack, myfit, arr);
				}
				
				myfit -= (arr[i] - k);
				last.remove(i);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		int n = Integer.parseInt(tokens.nextToken());
		int k = Integer.parseInt(tokens.nextToken());
		int arr[] = new int[n];
		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}

		recursion(new HashSet<>(), n, k, 0, 0, 500, arr);
		System.out.println(result);
	}
}