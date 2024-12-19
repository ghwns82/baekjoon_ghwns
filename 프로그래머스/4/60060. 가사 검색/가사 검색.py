from collections import defaultdict
from bisect import bisect_left, bisect_right

def solution(words, queries):
    # 단어 길이별로 정방향과 역방향 단어 저장
    words_by_length = defaultdict(list)
    reversed_words_by_length = defaultdict(list)
    
    # 단어를 길이별로 분류하고 정렬
    for word in words:
        words_by_length[len(word)].append(word)
        reversed_words_by_length[len(word)].append(word[::-1])
    
    # 각 길이별 단어 리스트를 정렬
    for length in words_by_length:
        words_by_length[length].sort()
        reversed_words_by_length[length].sort()
    
    def count_by_range(words, left, right):
        """이진 탐색을 통해 [left, right] 범위에 속하는 단어의 개수를 반환"""
        return bisect_right(words, right) - bisect_left(words, left)
    
    answer = []
    for query in queries:
        length = len(query)
        
        # 해당 길이의 단어가 없으면 0을 반환
        if length not in words_by_length:
            answer.append(0)
            continue
        
        # 접두사가 '?'인 경우 -> 접미사를 확인 (뒤집은 단어 리스트 사용)
        if query[0] == '?':
            left = query[::-1].replace('?', 'a')
            right = query[::-1].replace('?', 'z')
            answer.append(count_by_range(reversed_words_by_length[length], left, right))
        else:  # 접미사가 '?'인 경우 -> 접두사를 확인 (정방향 단어 리스트 사용)
            left = query.replace('?', 'a')
            right = query.replace('?', 'z')
            answer.append(count_by_range(words_by_length[length], left, right))
    
    return answer