import java.math.BigInteger;

public class Solution {
    public long solution(int w, int h) {
        // 최대공약수 계산
        int g = BigInteger.valueOf(w).gcd(BigInteger.valueOf(h)).intValue();

        // 기울기 비율 계산
        int a = w / g;
        int b = h / g;

        // 사용할 수 없는 정사각형 개수를 전체 구간에 곱해서 빼기
        return (long) w * h - (long) (a + b - 1) * g;
    }
}
