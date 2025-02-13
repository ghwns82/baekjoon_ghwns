dp = {(0,0):1}
mod = 10**9+7
def stirling2(n,k):
    if n*k==0 and n+k!=0:
        return 0
    if n==k or k==1:
        return 1
    
    if (n,k) not in dp:
        dp[(n,k)]= (k*stirling2(n-1,k) + stirling2(n-1,k-1))%mod
    return dp[(n,k)]

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
        tmp = stirling2(p,k) 
        tmp *= falling_factorial(n+1,k+1)
        tmp%=mod
        tmp *= pow_mod(k+1, exp)
        tmp%=mod
        result+=tmp
        result%=mod
        
    print(result)