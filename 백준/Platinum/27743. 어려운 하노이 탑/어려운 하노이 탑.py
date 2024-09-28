n,m = map(int,input().split())
mod = 10**9 + 7
if m == 1:
    print((pow(2,n,mod)-1)%mod)

else:
    result = 2*m-1
    result %=mod
    result += 4*m* (pow(2,n-1,mod)-1)
    result %=mod
    print(result)