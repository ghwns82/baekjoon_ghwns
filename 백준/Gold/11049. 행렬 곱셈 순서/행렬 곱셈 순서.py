import sys
input = lambda: sys.stdin.readline().strip()
n= int(input())
matrix = [list(map(int,input().split())) for i in range(n)]
if n==1:
    print(0)
    sys.exit()

dp = [[0]*n for i in range(n)]
for i in range(n-1):
    dp[i][i+1] = matrix[i][0] * matrix[i][1] * matrix[i+1][1]
    # print(f'dp[{i}][{i+1}]',dp[i][i+1])
def rec(s,e):
    if dp[s][e]==0:
        dp[s][e]=min(
            [matrix[s][0] * matrix[s+1][0] * matrix[e][1]+rec(s+1,e),
            rec(s,e-1)+ matrix[s][0] * matrix[e-1][1] * matrix[e][1]]+[rec(s,m)+rec(m+1,e)+matrix[s][0] * matrix[m][1] * matrix[e][1] for m in range(s+1,e-1)]
            
            )
        # print(f'dp[{s}][{e}]',dp[s][e])
    return dp[s][e]

print(rec(0,n-1))