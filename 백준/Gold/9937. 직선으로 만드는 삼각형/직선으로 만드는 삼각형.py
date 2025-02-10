import sys
from collections import Counter
input = lambda: sys.stdin.readline().strip()
import math


data = Counter()
n=int(input())
for i in range(n):
    a,b,c=map(int,input().split())
    if a==0:
        data[('x',0)]+=1
    elif b==0:
        data[('y',0)]+=1
    else:
        g=math.gcd(a,b)
        a//=g
        b//=g
        data[("+-"[a//abs(a) * b//abs(b) < 0],abs(a),abs(b))]+=1
# print(data)
MOD=1_000_000_007
result = math.comb(n,3) % MOD
for k,v in data.items():
    if v>=2:
        result -= (math.comb(v,2)*(n-v) % MOD)
        
    if v>=3:
        result -= (math.comb(v,3) % MOD)
        
    

result %=MOD
print(result)