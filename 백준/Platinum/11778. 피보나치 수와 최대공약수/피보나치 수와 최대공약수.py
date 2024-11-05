from math import gcd
n = gcd(*map(int,input().split()))

MOD = 1_000_000_007


def cal(n):
    if n==1:
        return [[1,1],[1,0]]

    tmp=cal(n//2)
    if n%2==0:
        return mul(tmp, tmp)
    else:
        return mul(mul(tmp,tmp),  [[1,1],[1,0]])
def mul(a,b):
    c=[[0,0],[0,0]]
    c[0][0] = (a[0][0]*b[0][0] + a[0][1]*b[1][0])%MOD
    c[0][1] = (a[0][0]*b[0][1] + a[0][1]*b[1][1])%MOD
    c[1][0] = (a[1][0]*b[0][0] + a[1][1]*b[1][0])%MOD
    c[1][1] = (a[1][0]*b[0][1] + a[1][1]*b[1][1])%MOD
    return c

print(cal(n)[0][1]%MOD)