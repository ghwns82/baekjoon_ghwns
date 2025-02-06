from bisect import bisect_left

n = int(input())

t = [0]+list(map(int,input().split()))
b = list(map(int,input().split()))
c = list(map(int,input().split()))

dp =[0]*(n+1)

for i in range(n):
    g=t[i+1]-b[i]
    index = bisect_left(t,g)-1
    # print(g,index)
    dp[i+1] = max(dp[i],dp[index]+c[i])
    # print(dp)

print(dp[-1])
    