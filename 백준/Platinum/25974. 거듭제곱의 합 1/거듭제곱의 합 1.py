mod = 10**9+7
dp = [[0 for i in range(1001)] for j in range(1001)]
for i in range(1001):
    for j in range(1001):
        if j==0:
            dp[i][j]=0
        elif i==j or j==1:
            dp[i][j]=1
        else:
            dp[i][j] = (dp[i-1][j-1]+j*dp[i-1][j])%mod
dp[0][0]=1


def falling_factorial(x,m):
    if m==0:
        return 1
    result = 1
    for i in range(m):
        result = (result*(x-i))%mod
    return result

def pow_mod(base,exp):
    result=1
    while exp>0:
        if exp%2:
            result=(result*base)%mod
        base = (base*base) %mod
        exp //=2
    return result

n,p = map(int,input().split())
if p==0:
    print(n)
else:
    result = 0
    exp = 10**9+5
    for k in range(1,p+1):
        tmp =dp[p][k]
        tmp *= falling_factorial(n+1,k+1)
        tmp%=mod
        tmp *= pow_mod(k+1, exp)
        tmp%=mod
        result+=tmp
        result%=mod
        
    print(result)