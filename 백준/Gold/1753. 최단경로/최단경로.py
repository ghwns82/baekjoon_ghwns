import sys

input = lambda: sys.stdin.readline().strip()
from heapq import heappop, heappush
INF = float('inf')
v,e = map(int, input().split())
start = int(input())
graph = [{} for i in range(v+1)]
for _ in range(e):
    a, b, c = map(int, input().split())
    if b in graph[a]:
        graph[a][b] = min(c,graph[a][b])
    else:
        graph[a][b] = c

dis_list = [INF]*(v+1)
hq = [(0,start)]
while hq:
    d,p=heappop(hq)
    if d >= dis_list[p]:
        continue
    dis_list[p] = d
    for dst in graph[p]:
        if graph[p][dst]<dis_list[dst]:
            heappush(hq,(d+graph[p][dst],dst))
for i in dis_list[1:]:
    if i>=INF:
        print('INF')
    else:
        print(i)
# print(*,sep='\n')