import math
import sys
sys.setrecursionlimit(3 * 10**6)
input = sys.stdin.readline
n = int(input())

graph = [set() for i in range(n)]

for i in range(n-1):
    a,b = map(int,input().split())
    graph[a-1].add(b-1)
    graph[b-1].add(a-1)
    
def dfs(cur):
    global n, result
    dots = 1
    for next in graph[cur]:
        if not visited[next]:
            visited[next] = 1
            dots += dfs(next)
    if cur!=0:
        result+=math.comb(n,2)-math.comb(n-dots,2)
    return dots

visited = [0]*n
result = 0
visited[0] = 1
dfs(0)
print(result)