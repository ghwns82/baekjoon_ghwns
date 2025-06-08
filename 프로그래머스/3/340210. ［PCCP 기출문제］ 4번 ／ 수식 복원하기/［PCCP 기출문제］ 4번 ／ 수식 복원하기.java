import java.util.*;

public class Solution {

    // c2n: 문자 숫자를 10진수 정수로 변환
    private int c2n(int unit, String num) {
        int d = 1;
        int result = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            result += (num.charAt(i) - '0') * d;
            d *= unit;
        }
        return result;
    }

    // n2c: 10진수 정수를 unit 진수 문자열로 변환
    private String n2c(int unit, int num) {
        int d = 1;
        List<String> result = new ArrayList<>();
        while (num >= d) {
            d *= unit;
        }
        d /= unit;

        while (d > 0) {
            result.add(String.valueOf(num / d));
            num %= d;
            d /= unit;
        }

        return result.isEmpty() ? "0" : String.join("", result);
    }

    public String[] solution(String[] expressions) {
        int maxNum = 0;
        List<String> info = new ArrayList<>();
        List<String> answer = new ArrayList<>();

        // 분리 및 최대 숫자 찾기
        for (String ex : expressions) {
            if (ex.endsWith("X")) {
                answer.add(ex.substring(0, ex.length() - 1));
            } else {
                info.add(ex);
            }
            for (char ch : ex.toCharArray()) {
                if (Character.isDigit(ch)) {
                    maxNum = Math.max(maxNum, ch - '0');
                }
            }
        }

        List<Integer> units = new ArrayList<>();
        for (int i = maxNum + 1; i < 10; i++) {
            units.add(i);
        }

        for (String ex : info) {
            String[] data = ex.split(" ");
            List<Integer> next = new ArrayList<>();
            for (int unit : units) {
                int a = c2n(unit, data[0]);
                int b = c2n(unit, data[2]);
                int c = c2n(unit, data[4]);
                int d = data[1].equals("+") ? a + b : a - b;
                if (d == c) {
                    next.add(unit);
                }
            }
            units = next;
        }

        for (int i = 0; i < answer.size(); i++) {
            String ex = answer.get(i);
            String[] data = ex.split(" ");
            Set<String> results = new HashSet<>();
            for (int unit : units) {
                int a = c2n(unit, data[0]);
                int b = c2n(unit, data[2]);
                int d = data[1].equals("+") ? a + b : a - b;
                String r = n2c(unit, d);
                results.add(r);
            }
            if (results.size() != 1) {
                answer.set(i, ex + "?");
            } else {
                answer.set(i, ex + results.iterator().next());
            }
        }

        return answer.toArray(new String[0]);
    }
}
