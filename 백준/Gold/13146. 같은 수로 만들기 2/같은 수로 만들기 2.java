import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(sc.nextInt());
        }

        long result = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i : a) {
            int diff = 0;
            while (!stack.isEmpty() && stack.peekLast() <= i) {
                diff = Math.max(diff, i - stack.pollLast());
            }
            result += diff;
            stack.addLast(i);
        }

        if (stack.size() > 1) {
            result += stack.peekFirst() - stack.peekLast();
        }

        System.out.println(result);
    }
}
