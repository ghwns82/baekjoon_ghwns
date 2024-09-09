import sys
input = lambda : sys.stdin.readline().strip()
from heapq import heappop, heappush
INF = float('inf')
n,m,x = map(int,input().split())
graph = [{} for i in range(n+1)]
for _ in range(m):
    a,b,c = map(int,input().split())
    if b in graph[a]:
        graph[a][b] = min(graph[a][b],c)
    else:
        graph[a][b]=c
    

def dak(s):
    distance = [INF]*(n+1)
    hq = [(0,s)]
    while hq:
        d,dot=heappop(hq)
        if d> distance[dot]:
            continue
        distance[dot] = d
        for dst in graph[dot]:
            if distance[dst]>graph[dot][dst]+d:
                heappush(hq,(graph[dot][dst]+d,dst))
    return distance
result = [0]*(n+1)
for s in range(1,n+1):
    result[s] += dak(s)[x]
# print(result)    
for i,d in enumerate(dak(x)[1:],1):
    result[i]+=d
# print(result)
print(max(result))