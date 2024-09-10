import sys
input = lambda : sys.stdin.readline().strip()
MOD = 1337377
def solve():
    # 입력 받기
    long_word = input().strip()  # 긴 단어
    N = int(input().strip())     # 집합 S의 크기
    word_set = set(input().strip() for _ in range(N))  # 단어 집합 S

    # DP 배열 초기화
    dp = [0] * (len(long_word) + 1)
    dp[0] = 1  # 아무것도 없을 때 경우의 수는 1

    # 동적 계획법 적용
    for i in range(1, len(long_word) + 1):
        # 최대 100글자까지만 확인한다.
        for j in range(max(0, i - 100), i):
            if long_word[j:i] in word_set:
                dp[i] = (dp[i] + dp[j]) % MOD

    # 최종 답 출력
    print(dp[len(long_word)])
solve()