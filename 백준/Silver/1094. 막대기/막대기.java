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
		int x = Integer.parseInt(tokens.nextToken());
		int cnt = 0;
		for (int i = 0; i < 7; i++) {
			if ((x & 1<<i) !=0) {
				cnt++;
			}
			
		}
		System.out.println(cnt);
				
	}
}