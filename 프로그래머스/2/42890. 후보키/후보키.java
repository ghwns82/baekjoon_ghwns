import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        List<Integer> result = new ArrayList<>();
        int length = relation[0].length;
        int rowCount = relation.length;

        for (int num = 1; num < (1 << length) + 1; num++) {
            boolean isSubset = false;

            for (int existing : result) {
                if ((num & existing) == existing) {
                    isSubset = true;
                    break;
                }
            }

            if (isSubset) continue;

            Set<String> attributeSet = new HashSet<>();
            boolean isUnique = true;

            for (int i = 0; i < rowCount; i++) {
                StringBuilder key = new StringBuilder();
                for (int j = 0; j < length; j++) {
                    if ((num & (1 << j)) != 0) {
                        key.append(relation[i][j]).append("|"); // 구분자 추가
                    }
                }

                if (!attributeSet.add(key.toString())) {
                    isUnique = false;
                    break;
                }
            }

            if (isUnique) {
                result.add(num);
            }
        }

        return result.size();
    }
}
