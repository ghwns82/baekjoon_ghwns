import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder output = new StringBuilder();
    static int arr[];
    private static int fib(int x) {
    	if (arr[x]==0) 
    		arr[x] = 1+Math.min(fib(x/3)+x%3, fib(x/2)+x%2);
		
    	return arr[x];
}
    	
    
    public static void main(String[] args) throws IOException {
    	tokens = new StringTokenizer(input.readLine());
    	int n = Integer.parseInt(tokens.nextToken());
    	arr = new int[Math.max(n+1, 4)];
    	
    	arr[1]=1;
    	arr[2]=2;
    	arr[3]=2;
    	System.out.println(fib(n)-1);
//    	System.out.println(Arrays.toString(arr));
	}
}