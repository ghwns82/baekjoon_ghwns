import java.util.*;

class Solution {
    // 단어 길이별로 정방향과 역방향 단어를 저장하는 맵
    Map<Integer, List<String>> wordsByLength = new HashMap<>();
    Map<Integer, List<String>> reversedWordsByLength = new HashMap<>();

    public int[] solution(String[] words, String[] queries) {
        // 단어를 길이별로 분류하고 저장
        for (String word : words) {
            int length = word.length();
            wordsByLength.computeIfAbsent(length, k -> new ArrayList<>()).add(word);
            reversedWordsByLength.computeIfAbsent(length, k -> new ArrayList<>()).add(new StringBuilder(word).reverse().toString());
        }

        // 각 길이별 단어 리스트를 정렬
        for (List<String> wordList : wordsByLength.values()) {
            Collections.sort(wordList);
        }
        for (List<String> reversedWordList : reversedWordsByLength.values()) {
            Collections.sort(reversedWordList);
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            int length = query.length();

            // 해당 길이의 단어가 없으면 0 반환
            if (!wordsByLength.containsKey(length)) {
                answer[i] = 0;
                continue;
            }

            // 접두사가 '?'인 경우 (역방향 단어 리스트 사용)
            if (query.charAt(0) == '?') {
                String reversedQuery = new StringBuilder(query).reverse().toString();
                String left = reversedQuery.replace('?', 'a');
                String right = reversedQuery.replace('?', 'z');
                answer[i] = countByRange(reversedWordsByLength.get(length), left, right);
            } else { // 접미사가 '?'인 경우 (정방향 단어 리스트 사용)
                String left = query.replace('?', 'a');
                String right = query.replace('?', 'z');
                answer[i] = countByRange(wordsByLength.get(length), left, right);
            }
        }

        return answer;
    }

    // 이진 탐색을 사용해 [left, right] 범위에 속하는 단어의 개수를 반환
    private int countByRange(List<String> wordList, String left, String right) {
        int leftIndex = Collections.binarySearch(wordList, left);
        int rightIndex = Collections.binarySearch(wordList, right);

        // binarySearch는 요소를 찾지 못하면 음수 인덱스를 반환하므로, 삽입 위치를 계산
        if (leftIndex < 0) leftIndex = -(leftIndex + 1);
        if (rightIndex < 0) rightIndex = -(rightIndex + 1);
        else rightIndex += 1; // rightIndex가 정확히 일치하는 경우, 범위를 포함시키기 위해 +1

        return rightIndex - leftIndex;
    }
}
