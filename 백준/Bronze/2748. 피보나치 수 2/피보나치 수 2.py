memo = {}
def fibo(n):
    if n not in memo:
        if n==1:
            memo[n]=1
        elif n==2:
            memo[n]=1
        else:
            memo[n] = fibo(n-1) + fibo(n-2)

    return memo[n]
print(fibo(int(input())))