import sys
from collections import Counter

input = sys.stdin.readline
import sys
from collections import Counter
n = int(input())
if n==2:
    num = int(input())
    print(*sorted([0,num]))
    sys.exit()

arr = sorted(map(int,input().split()), reverse=True)
tmp = arr[-1]+arr[-2]
# print(arr,tmp)

can = set()

for i in range(len(arr)):
    if arr[i]*2 >= tmp and (tmp-arr[i]) %2==0 and (tmp-arr[i]) <= arr[-1]:
        can.add((tmp-arr[i])//2)


if not can:
    print('Impossible')
    sys.exit()
pool = Counter(arr)
pool[arr[-1]]-=1
def solution():
    index = -2
    for k in range(2,n):
        while not pool2[arr[index]]:
            index-=1
        new = arr[index]-result[0]

        for i in result:
            pool2[i+new]-=1
            if pool2[i+new]<0:
                return []
        
        result.append(new)
        # print(result)
    return result
for c in can:
    result = [c, arr[-1]-c]
    pool2= pool.copy()
    # print('solu',c)
    ans = solution()
    if ans:
        print(*ans)
        sys.exit()
print('Impossible')
