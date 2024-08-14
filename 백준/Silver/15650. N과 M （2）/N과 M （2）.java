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
	
	public static void recursion(int index, int n, int m, int []arr, int last) {
		if (index==m) {
			for (int i : arr) {
				System.out.printf("%d ",i);
			}
			System.out.println();
			return;
		}
		for (int i = last+1; i <= n; i++) {
			arr[index] = i;
			recursion(index+1, n, m, arr,i);
		}
	}
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		int n = Integer.parseInt(tokens.nextToken());
		int m = Integer.parseInt(tokens.nextToken());
		recursion(0,n,m,new int[m],0);
	}
	
}