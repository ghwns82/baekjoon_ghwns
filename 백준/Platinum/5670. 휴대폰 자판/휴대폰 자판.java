import java.io.*;
import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord;
}

class Trie {
    private final TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isEndOfWord = true;
    }
    
    public int countPresses(String word) {
        TrieNode node = root;
        int presses = 1; // 첫 글자는 무조건 입력해야 함
        node = node.children.get(word.charAt(0));
        
        for (int i = 1; i < word.length(); i++) {
            if (node.children.size() != 1 || node.isEndOfWord) {
                presses++;
            }
            node = node.children.get(word.charAt(i));
        }
        return presses;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            int n = Integer.parseInt(line);
            Trie trie = new Trie();
            List<String> words = new ArrayList<>();
            
            for (int i = 0; i < n; i++) {
                String word = br.readLine();
                words.add(word);
                trie.insert(word);
            }
            
            int totalPresses = 0;
            for (String word : words) {
                totalPresses += trie.countPresses(word);
            }
            
            double average = (double) totalPresses / n;
            sb.append(String.format("%.2f\n", average));
        }
        System.out.print(sb);
    }
}