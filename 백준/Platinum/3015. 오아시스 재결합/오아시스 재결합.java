import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Person> stack = new Stack<>();
        long result = 0;

        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(br.readLine());
            while (true) {
                if (stack.isEmpty() || stack.peek().height > height) {
                    stack.push(new Person(height, 1));
                    break;
                } else if (stack.peek().height == height) {
                    stack.peek().count++;
                    break;
                } else {
                    Person popped = stack.pop();
                    result += (long)popped.count * (popped.count - 1) / 2 + popped.count;
                    if (!stack.isEmpty()) result += popped.count;
                }
            }
        }

        while (!stack.isEmpty()) {
            Person p = stack.pop();
            result += (long)p.count * (p.count - 1) / 2;
            if (!stack.isEmpty()) result += p.count;
        }

        System.out.println(result);
    }

    static class Person {
        int height;
        int count;

        Person(int height, int count) {
            this.height = height;
            this.count = count;
        }
    }
}
