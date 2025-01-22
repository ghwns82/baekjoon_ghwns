import sys
sys.setrecursionlimit(1_000_000)
input = lambda: sys.stdin.readline().strip()
n = int(input())
graph = [[] for i in range(n)]

visited = [False]*n
dp = [[0,1] for i in range(n)]
for i in range(n-1):
    a,b = map(int,input().split())
    graph[b-1].append(a-1)
    graph[a-1].append(b-1)
    
    
def recursion(cur):
    # print('cur',cur)
    for next in graph[cur]:
        if not visited[next]:    
            visited[next]=True
            recursion(next)
            dp[cur][0]+=dp[next][1]
            dp[cur][1]+=min(dp[next])
    # print('cur done',cur, dp[cur])
visited[0]=True
recursion(0)
print(min(dp[0]))