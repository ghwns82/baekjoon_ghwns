import java.io.*;
import java.math.BigInteger;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // BigInteger로 2^N - 1 계산
        BigInteger result = BigInteger.valueOf(2).pow(N).subtract(BigInteger.ONE);
        bw.write(result.toString());
        bw.newLine();

        // N이 20 이하일 때만 수행 과정 출력
        if (N <= 20) {
            move(N, 1, 3, 2);
        }

        bw.flush();
    }

    public static void move(int n, int start, int goal, int other) throws IOException {
        if (n == 1) {
            bw.write(start + " " + goal);
            bw.newLine();
        } else {
            move(n - 1, start, other, goal);
            bw.write(start + " " + goal);
            bw.newLine();
            move(n - 1, other, goal, start);
        }
    }
}
