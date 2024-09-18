# 삭제 우선순위
# 1. 나중에 쓸일이 없다
# 2. k안에 들지 않는다.
# 3. 가장 나중에 쓰인다.

from bisect import bisect
from collections import defaultdict, deque
n,k = map(int,input().split())
arr = list(map(int,input().split()))
freq = defaultdict(deque)
using = set()
for i,v in enumerate(arr):
    freq[v].appendleft(i)
cnt = 0
for i in arr:
    freq[i].pop()
    if len(using)<n or i in using:
        using.add(i)
        continue
    tmp = []
    cnt+=1
    for j in using:
        if len(freq[j])==0:
            tmp.append([k,j])
        else:
            tmp.append([freq[j][-1],j])
    index, item=max(tmp)
    using.remove(item)
    using.add(i)
    # print(using)
print(cnt)
        





# 2 3 4 1 2 4