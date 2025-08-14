import sys
input = sys.stdin.readline

from collections import defaultdict,deque
n,q = map(int,input().split())

graph = [defaultdict(int) for i in range(n+1)]

for i in range(n-1):
    a,b,c = map(int,input().split())
    graph[a][b] = c
    graph[b][a] = c

for i in range(q):
    k,v = map(int,input().split())
    visited=[0]*(n+1)
    work = deque([v])
    visited[v]=1
    cnt=0
    while work:
        now=work.popleft()
        for next in graph[now]:
            if visited[next]:
                continue
            visited[next] = 1
            if graph[now][next]>=k:
                cnt+=1
                work.append(next)
    print(cnt)


