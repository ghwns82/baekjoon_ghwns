import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder output = new StringBuilder();
    
    public static BigInteger fac(int n, int r) {
        if (r > n) {
            return BigInteger.ZERO; // 잘못된 입력 처리
        }
        r = Math.min(r, n - r); // 조합의 대칭성 이용 (nCr == nC(n-r))
        BigInteger numerator = BigInteger.ONE;
        BigInteger denominator = BigInteger.ONE;
        
        for (int i = 0; i < r; i++) {
            numerator = numerator.multiply(BigInteger.valueOf(n - i));
            denominator = denominator.multiply(BigInteger.valueOf(i + 1));
        }
        
        return numerator.divide(denominator);
    }
    
    
    public static void main(String[] args) throws IOException {
    	tokens = new StringTokenizer(input.readLine());
    	int tc = Integer.parseInt(tokens.nextToken());
    	for (int t = 1; t <= tc; t++) {
    		tokens = new StringTokenizer(input.readLine());
    		int r = Integer.parseInt(tokens.nextToken());
    		int n = Integer.parseInt(tokens.nextToken());
 
			output.append(fac(n,r)).append("\n");
		}
    	System.out.println(output);
	}
}