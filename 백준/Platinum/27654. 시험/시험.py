import sys
from heapq import heappop, heappush
input = lambda: sys.stdin.readline().strip()
n,k = map(int,input().split())
tests = [list(map(int,input().split())) for i in range(n)]
start,end = 0,1

for i in range(20):
    # print('se',start,end)
    mid = (start+end)/2
    queue = []
    for p,q in tests:
        heappush(queue, (mid*q-p))
    res=0
    for i in range(k):
        res-=heappop(queue)
    # print(res)
    if res > 0:
        start = mid
    elif res <0:
        end = mid
    else:
        break
print(mid)