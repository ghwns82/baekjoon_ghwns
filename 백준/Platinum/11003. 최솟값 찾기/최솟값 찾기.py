import sys
from heapq import heappush, heappop
input = lambda: sys.stdin.readline().rstrip()
n,m =map(int,input().split())
arr = []
now = []
i=0
for v in map(int,input().split()):
    # print(i,v)
    heappush(arr,(v,i))
    # print(arr.queue[0][0],arr.qsize)
    if not now:
        now=heappop(arr)
    while now[1]<i-m+1:
        now=heappop(arr)
    if arr and arr[0][0]<now[0]:
        now=heappop(arr)
    
    print(now[0], end=' ')
    i+=1


# 7 3
# 1 3 2 4 5 6 7
# 1 1 1 2 2 4 5

# 12 3
# 1 5 2 3 6 6 3 12 1 2 5 5

# answer: 
# 1 1 1 2 2 3 3 3 1 1 1 2

# 5 3
# 1 3 2 4 4
# 답: 1 1 1 2 2

# 12 3
# 2 5 1 3 6 2 3 7 3 5 2 6
# 답: 2 2 1 1 1 2 2 2 3 3 2 2