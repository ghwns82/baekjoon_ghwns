import sys
input = sys.stdin.readline
m = 1_000_000_007
dp = [1]*(4_000_001)

r=1
for i in range(2,4_000_001):
    r=(r*i)%m
    dp[i]=r

for _ in range(int(input())):
    n,k = map(int,input().split())
    b = (dp[k] * dp[n-k])%m
    print((dp[n] * pow(b,m-2,m))%m)
    
