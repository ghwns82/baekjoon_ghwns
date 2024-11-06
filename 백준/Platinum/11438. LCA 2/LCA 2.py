import sys
import math
from collections import deque
input = lambda: sys.stdin.readline().rstrip()

n =int(input())
work = deque()
dp = [[-1]*n for i in range(int(math.log2(n)+1))]
visited=[False]*n
depth=[-1]*n
graph = [[] for i in range(n)]

for i in range(n-1):
    a,b = map(int,input().split())
    graph[a-1].append(b-1)
    graph[b-1].append(a-1)

work.append(0)
visited[0]=True
depth[0]=0
while work:
    dot = work.popleft()
    for d in graph[dot]:
        if not visited[d]:
            dp[0][d] = dot
            visited[d]=True
            depth[d]=depth[dot]+1
            # print('depth',d,depth[dot]+1)
            work.append(d)
for i in range(1,len(dp)):
    for j in range(n):
        if dp[i-1][j]==-1:
            continue
        dp[i][j] = dp[i-1][dp[i-1][j]]
# print(*dp,sep='\n')
# print(depth)
def lca(a,b):
    # 같은 높이 맞추기
    if depth[a]>depth[b]:
        a,b = b,a
#     print("start",a, b, depth[a],depth[b])
    while depth[a]<depth[b]:
        h =int(math.log2(depth[b]-depth[a]))
#         print("h",h)
        b=dp[h][b]
#         print(a+1,b+1)
    if a==b:
        return a
    
    while a!=b:
        for i in range(len(dp)-1,0,-1):
            if dp[i][a] == -1 or dp[i][b]==-1:
                continue
            if dp[i][a] != dp[i][b]:
                a,b = dp[i][a], dp[i][b]
                break
        else:
            a = dp[0][a]
            b = dp[0][b]
    return a
                
for i in range(int(input())):
    a,b = map(int,input().split())
    print(lca(a-1,b-1)+1)
    
# // input
# 5
# 1 5
# 5 4
# 4 3
# 3 2
# 1
# 1 2

# // ans
# 1

# // wrong output
# 0

# // input
# 2
# 1 2
# 2
# 1 1
# 1 2

# // ans
# 1
# 1

# // wrong output
# 0
# 0