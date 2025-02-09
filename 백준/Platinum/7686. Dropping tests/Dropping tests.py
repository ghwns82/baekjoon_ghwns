import sys
from heapq import heappop, heappush
input = lambda: sys.stdin.readline().strip()
while True:
    n,k = map(int,input().split())
    if n==k==0:
        break
    a_list = list(map(int,input().split()))
    b_list = list(map(int,input().split()))
    start,end = 0,1

    for _ in range(20):
        # print('se',start,end)
        mid = (start+end)/2
        queue = []
        for i in range(n):
            a,b = a_list[i],b_list[i]
            heappush(queue, (mid*b-a))
        res=0
        for i in range(n-k):
            res-=heappop(queue)
        # print([start,mid,end],res)
        if res > 0:
            start = mid
        elif res <0:
            end = mid
        else:
            break
    print(round(mid*100))