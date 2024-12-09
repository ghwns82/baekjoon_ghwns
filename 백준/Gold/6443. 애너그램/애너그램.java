import java.util.*;

public class Main {
    private static TreeMap<Character, Integer> charCountMap;
    private static char[] currentWord;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        scanner.nextLine();  // 개행 문자 처리
        
        for (int i = 0; i < n; i++) {
            String word = scanner.nextLine();
            charCountMap = new TreeMap<>();
            
            // 문자 개수 세기
            for (char c : word.toCharArray()) {
                charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
            }
            
            currentWord = new char[word.length()];
            generateAnagrams(word.length());
        }
        
        scanner.close();
    }
    
    private static void generateAnagrams(int remaining) {
        if (remaining == 0) {
            System.out.println(new String(currentWord));
            return;
        }
        
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            char letter = entry.getKey();
            int count = entry.getValue();
            
            if (count > 0) {
                charCountMap.put(letter, count - 1);
                currentWord[currentWord.length - remaining] = letter;
                
                generateAnagrams(remaining - 1);
                
                // 백트래킹: 문자 개수 복원
                charCountMap.put(letter, count);
            }
        }
    }
}