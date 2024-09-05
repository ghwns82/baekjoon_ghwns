import sys
n = int(input())

house = [input().split() for i in range(n)]
dp = [[[0,0,0] for j in range(n)] for i in range(n)]
# 동 남동 남
if house[-1][-1]=='1':
    print(0)
    sys.exit()

dp[0][1][0]=1
for i in range(n):
    for j in range(n):
        # print(i,j)
        
        if 0<=j-1 and house[i][j-1]=='0':
            dp[i][j][0]+=dp[i][j-1][0]+dp[i][j-1][1]
        if 0<=i-1 and house[i-1][j]=='0':
            dp[i][j][2]+= dp[i-1][j][2] + dp[i-1][j][1]
        if 0<=j-1 and 0<=i-1 and '1' not in [house[i][j-1],house[i-1][j],house[i-1][j-1]]:
            dp[i][j][1]+= sum(dp[i-1][j-1])
        # print(*dp,sep='\n')
print(sum(dp[-1][-1]))