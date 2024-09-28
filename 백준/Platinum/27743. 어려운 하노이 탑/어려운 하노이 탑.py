n,m = map(int,input().split())
mod = 10**9 + 7
if m == 1:
    print(pow(2,n,mod)-1)

else:
    result = 1
    result *= 4*m
    result %=mod
    if n%2:
        result *= (n-1)//2
        result %=mod
        result *= n
        result %= mod
    else:
        result *= n//2
        result %=mod
        result *= (n-1)
        result %= mod
    result += 2*m-1
    result %=mod
    print(result)