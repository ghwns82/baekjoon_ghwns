from collections import deque
import sys
input = lambda : sys.stdin.readline().strip()
n = int(input())
ant = [int(input()) for i in range(n)]
parent = [[] for i in range(n)]
graph = [[] for i in range(n)]
for i in range(n-1):
    a,b,c = map(int,input().split())
    graph[a-1].append((b-1,c))
    graph[b-1].append((a-1,c))

work = deque([[0,[(0,0)]]])
visited = [0]*(n)
visited[0]=1
while work:
    dot,need = work.popleft()
    cur = dot
    # print(need)
    for i in range(len(need)-1,-1,-1):
        if ant[dot] >= need[i][0]:
            ant[dot]-=need[i][0]
            cur = need[i][1]
        else:
            ant[dot] = cur+1
            break
    else:
        ant[dot]=1
    for dst,d in graph[dot]:
        if not visited[dst]:
            visited[dst]=1
            work.append([dst,need+[(d,dot)]])

print(*ant,sep='\n')




# 9
# 49
# 17
# 45
# 27
# 49
# 33
# 34
# 14
# 28
# 2 3 6
# 5 9 10
# 4 1 8
# 5 1 6
# 9 8 3
# 8 3 1
# 2 6 10
# 4 7 10

# # output
# 1
# 9
# 1
# 1
# 1
# 5
# 1
# 5
# 1