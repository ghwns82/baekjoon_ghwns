import sys
from heapq import heappush, heappop
input = lambda: sys.stdin.readline().rstrip()
left =[]
mid=[]
right=[]

n = int(input())
for i in range(n):
    num = int(input())
    if not mid:
        mid.append(num)
    else:
        if mid[0]<=num:
            heappush(right,num)
        else:
            heappush(left,num*-1)

    if len(left)>len(right):
        heappush(right,mid.pop())
        mid.append(heappop(left)*-1)
    if len(right)>len(left)+1:
        heappush(left,mid.pop()*-1)
        mid.append(heappop(right))
    print(mid[0])

