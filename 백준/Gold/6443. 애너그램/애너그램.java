import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            String word = br.readLine();
            Map<Character, Integer> charCountMap = new TreeMap<>();
            for (char c : word.toCharArray()) {
                charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
            }
            generateAnagrams(charCountMap, new char[word.length()], 0);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void generateAnagrams(Map<Character, Integer> charCountMap, char[] currentWord, int depth) throws IOException {
        if (depth == currentWord.length) {
            bw.write(currentWord);
            bw.newLine();
            return;
        }

        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            if (entry.getValue() > 0) {
                currentWord[depth] = entry.getKey();
                entry.setValue(entry.getValue() - 1);
                generateAnagrams(charCountMap, currentWord, depth + 1);
                entry.setValue(entry.getValue() + 1); // 백트래킹
            }
        }
    }
}