import sys
from math import ceil
input = lambda : sys.stdin.readline().rstrip()
from heapq import heappop, heappush

tc = int(input())
for _ in range(tc):
    n = int(input())
    left,mid,right=[],[],[]
    index=0
    result=[]
    print(n//2+1)
    nums = []
    for i in range(ceil(n/10)):
        nums+=list(map(int,input().split()))
    for i in nums:
        if not mid:
            mid.append(i)
        else:
            if i>=mid[0]:
                heappush(right,i)
            else:
                heappush(left,-i)
            if len(left)< len(right):
                heappush(left, -mid.pop())
                mid.append(heappop(right))
            elif len(left) > len(right):
                heappush(right,mid.pop())
                mid.append(-heappop(left))

        if index%2==0:
            # print(left,mid,right)
            result.append(mid[0])
        if len(result)==10:
            print(*result)
            result=[]
        index+=1

    if result:
        print(*result)