import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine(); 
        String bomb = br.readLine(); 

        Stack<Character> stack = new Stack<>();
        int bombLength = bomb.length();
        char lastBombChar = bomb.charAt(bombLength - 1);

        for (char c : input.toCharArray()) {
            stack.push(c);

            if (c == lastBombChar && stack.size() >= bombLength) {
                boolean isBomb = true;
                for (int j = 0; j < bombLength; j++) {
                    if (stack.get(stack.size() - bombLength + j) != bomb.charAt(j)) {
                        isBomb = false;
                        break;
                    }
                }

                if (isBomb) {
                    for (int j = 0; j < bombLength; j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }

        if (result.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(result);
        }
    }
}