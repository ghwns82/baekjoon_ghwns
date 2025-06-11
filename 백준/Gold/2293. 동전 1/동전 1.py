n,k=map(int,input().split())
coin=[int(input()) for i in range(n)]
dp=[0]*(k+1)
dp[0]=1

for c in coin:
    for i in range(c,k+1):
        if dp[i-c]:
            dp[i]+=dp[i-c]
    # print(c,dp)
#     dp[c]+=1
#     for i in range()
# for i in range(k+1):
#     if i:
#         for c in coin:
#             if i+c<=k:
#                 dp[i+c]+=dp[i]

print(dp[-1])
# print(dp)