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
    	int tmp[] = new int [3];
    	int tmp2[]= new int [3];;
    	for (int i = 0; i < n; i++) {
    		tmp = tmp2;
    		tmp2 = new int [3];
    		tokens = new StringTokenizer(input.readLine());
    		tmp2[0] = Integer.parseInt(tokens.nextToken()) + Math.min(tmp[1],tmp[2]);
    		tmp2[1] = Integer.parseInt(tokens.nextToken()) + Math.min(tmp[0],tmp[2]);
    		tmp2[2] = Integer.parseInt(tokens.nextToken()) + Math.min(tmp[1],tmp[0]);
//    		System.out.println(Arrays.toString(tmp2));
    		
		}
    	System.out.println(Math.min(Math.min(tmp2[0], tmp2[1]),tmp2[2]));
    		
		
	}
}