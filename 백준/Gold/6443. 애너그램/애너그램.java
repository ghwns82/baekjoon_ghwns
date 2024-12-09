import java.io.*;
import java.util.*;

public class Main {
    private static HashMap<Character, Integer> charCountMap;
    private static char[] currentWord;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 횟수 N 읽기
        int n = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < n; i++) {
            // 각 단어 입력받기
            String word = br.readLine().trim();
            charCountMap = new HashMap<>();

            // 문자 개수 세기
            for (char c : word.toCharArray()) {
                charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
            }

            currentWord = new char[word.length()];
            generateAnagrams(word.length());
        }

        bw.flush();  // 출력 버퍼 비우기
        bw.close();
        br.close();
    }

    private static void generateAnagrams(int remaining) throws IOException {
        if (remaining == 0) {
            bw.write(currentWord);
            bw.newLine();
            return;
        }

        // HashMap 키를 정렬한 리스트 생성
        List<Character> sortedKeys = new ArrayList<>(charCountMap.keySet());
        Collections.sort(sortedKeys);

        for (char letter : sortedKeys) {
            int count = charCountMap.get(letter);

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