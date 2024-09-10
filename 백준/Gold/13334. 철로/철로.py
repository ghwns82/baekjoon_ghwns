import sys
input = sys.stdin.readline
from heapq import heappop,heappush,nsmallest

n = int(input())
check=set()
# arr = []
arr2 = []
for i in range(n):
    a,b = sorted(map(int,input().split()))
    check.add(a)
    # heappush(arr, (a,b,i))
    heappush(arr2, (b,a))
d = int(input())
check = sorted(check)
hq=[]
result = 0
for ps in check:
    pe = ps+d
    # print(ps,pe)
    while arr2:
        e,s = arr2[0]
        if e<=pe:
            heappush(hq, heappop(arr2)[::-1])
            continue
        if s==ps and e>pe:
            heappop(arr2)
            continue
        break
    while hq and hq[0][0]<ps:
        heappop(hq)
    result= max(result, len(hq))
    # print(len(hq))
    # print(hq)
print(result)





# 10
# 23 89
# 34 41
# 2 17
# 47 91
# 89 98
# 85 85
# 70 82
# 1 3
# 48 68
# 18 28
# 36

# ans : 3