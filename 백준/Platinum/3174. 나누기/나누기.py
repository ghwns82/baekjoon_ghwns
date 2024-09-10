MOD = 1337377

# Trie 노드 클래스 정의
class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_end_of_word = False  # 단어의 끝을 표시

# Trie 클래스 정의
class Trie:
    def __init__(self):
        self.root = TrieNode()
    
    # Trie에 단어 삽입
    def insert(self, word):
        node = self.root
        for char in word:
            if char not in node.children:
                node.children[char] = TrieNode()
            node = node.children[char]
        node.is_end_of_word = True
    
    # Trie에서 주어진 단어로 시작하는 부분 문자열이 있는지 확인
    def search(self, s, start):
        node = self.root
        for i in range(start, len(s)):
            char = s[i]
            if char not in node.children:
                break
            node = node.children[char]
            if node.is_end_of_word:
                yield i + 1  # 단어의 끝인 경우, 해당 위치 반환

# 메인 함수
def solve():
    # 긴 단어와 집합 S의 입력 받기
    long_word = input().strip()
    N = int(input().strip())
    words = [input().strip() for _ in range(N)]
    
    # Trie에 집합 S의 단어 삽입
    trie = Trie()
    for word in words:
        trie.insert(word)
    
    # DP 배열 초기화
    dp = [0] * (len(long_word) + 1)
    dp[0] = 1  # 초기 상태 (아무 것도 없는 경우)
    
    # 동적 계획법을 사용해 긴 단어를 나누는 방법의 수 계산
    for i in range(len(long_word)):
        if dp[i] == 0:
            continue
        # Trie에서 i 위치에서 시작하는 모든 가능한 부분 문자열을 찾음
        for end in trie.search(long_word, i):
            dp[end] = (dp[end] + dp[i]) % MOD
    
    # 결과 출력
    print(dp[len(long_word)])
solve()