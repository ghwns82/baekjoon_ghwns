def miller_rabin_test(n, a):
    # n - 1을 2^s * d 형태로 분해
    s, d = 0, n - 1
    while d % 2 == 0:
        d //= 2
        s += 1
    
    # a^d % n 계산
    x = pow(a, d, n)
    if x == 1 or x == n - 1:
        return True

    # s-1번 반복하면서 x^2 % n 확인
    for _ in range(s - 1):
        x = pow(x, 2, n)
        if x == n - 1:
            return True
    return False

def is_prime(n):
    # 2와 3은 소수
    if n == 2 or n == 3:
        return True
    # 1 이하이거나 짝수는 소수가 아님
    if n <= 1 or n % 2 == 0:
        return False

    # 2, 7, 61로만 테스트
    for a in [2, 7, 61]:
        if a >= n:  # a가 n보다 크면 검사할 필요 없음
            break
        if not miller_rabin_test(n, a):
            return False
    return True

# 예제
cnt=0
for i in range(int(input())):
    n = int(input())
    if is_prime(2*n+1):
        cnt+=1
print(cnt)