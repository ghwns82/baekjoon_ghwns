import sys
import math
input = lambda : sys.stdin.readline().rstrip()
n = int(input())
mod = 10**9 + 7
fibo = [0,1]
for i in range(n):
    fibo.append((fibo[-1]+fibo[-2])%mod)
s = 0
for i in range(1,n+1):
    s+=fibo[math.gcd(i+1,n+1)]
    s%=mod
print(s)