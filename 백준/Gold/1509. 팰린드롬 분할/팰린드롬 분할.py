line=input()
n=len(line)
dp = [[True]*n for i in range(n)]

for j in range(1,n):
    for i in range(j,n):
        dp[i][i-j] = dp[i-1][i-j+1] and line[i]==line[i-j]

# print(*dp, sep='\n')


dp2=[0]*(n+1)


# for i in range(a):
    

for a in range(1,n+1):
    dp2[a]=min([dp2[i]+dp[max(a-1,i)][min(a-1,i)] for i in range(a) if dp[max(a-1,i)][min(a-1,i)]]+[2500])
# print(dp2)
print(dp2[-1])
    