r,c = map(int,input().split())
MAP=[list(input()) for i in range(r)]
from collections import deque
def bfs(r,c,x,y):
    result = {}
    visited=[[-1]*c for i in range(r)]
    visited[x][y] = 0
    work=deque([(x,y,0)])
    while work:
        x,y,d = work.popleft()
        for nx,ny in [(x-1,y),(x+1,y),(x,1+y),(x,y-1)]:
            if not (0<=nx<r and 0<=ny < c):
                continue
            if visited[nx][ny]>-1:
                continue
            if MAP[nx][ny]=='X':
                continue
            visited[nx][ny] = d+1
            work.append([nx,ny,d+1])
            if MAP[nx][ny]=='P':
                result[nx*c+ny] = d+1
    return result



import sys
graph = {}
for i in range(r):
    for j in range(c):
        if MAP[i][j]=='C':
            graph[i*c+j] = bfs(r,c,i,j)
if not graph:
    print(0)
    sys.exit()
def bimatch(cur,limit):
    global visited,seleted
    if cur in visited:
        # print('visited',cur)
        return False
    visited.add(cur)

    for next in graph[cur]:
        if graph[cur][next]>limit:
            continue
        if next not in selected or bimatch(selected[next],limit):
            # print('input',selected)
            selected[next] = cur
            return True
def possible(limit):
    global visited,seleted
    for key in graph:
        visited=set()
        # print('key',key)
        if not bimatch(key,limit):
            return False
    return True
s,e = 1, r*c
result = -1
while s<e:
    m = (s+e)//2
    # print(s,m,e)
    visited,selected = set(),{}
    if possible(m):
        e = m
        result = m
    else:
        s = m+1
    # print(selected)
print(result)