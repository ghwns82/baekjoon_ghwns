import sys
input = lambda: sys.stdin.readline().strip()
sys.setrecursionlimit(200000)
n = int(input())
parent = list(map(int,input().split()))
children = [[] for i in range(n)]
for c in range(n-1):
    p = parent[c]
    children[p-1].append(c+1)
sng = list(map(int,input().split()))
dp = [[0,0] for i in range(n)]

# print(children)
# 팀이 되는 경우
# 간택 + 나머지 부사수 max 총합
# 팀이 안되는 경우
# 부사수 총합

def recursion(cur):
    # print('cur',cur)
    for c in children[cur]:
        if children[c]:
            recursion(c)
        dp[cur][0] += max(dp[c])
        
    
        
    for c in children[cur]:
        if dp[c][0]< dp[c][1]:
            v=dp[cur][0]-dp[c][1] + dp[c][0] + sng[c] * sng[cur]
        else:
            v = dp[cur][0]+sng[c] * sng[cur]
        dp[cur][1] = max(dp[cur][1], v)
        
    # print('cur done',cur, dp[cur])

recursion(0)
print(max(dp[0]))