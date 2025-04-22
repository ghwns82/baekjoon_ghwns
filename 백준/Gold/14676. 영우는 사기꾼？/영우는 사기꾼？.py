import sys
input = lambda : sys.stdin.readline().rstrip()
from collections import defaultdict, Counter
n, m, k = map(int,input().split())
info = defaultdict(set)
pre = [0]*(n+1)
for i in range(m):
    a,b = map(int,input().split())
    info[a].add(b)
    pre[b]+=1
mine = Counter()
result = "King-God-Emperor"
for _ in range(k):
    a,b = map(int,input().split())
    if a==1:
        if not pre[b]:
            if mine[b]==0:
                if b in info:
                    for p in info[b]:
                        pre[p]-=1
            mine[b]+=1
        else:
            result = "Lier!"
    else:
        if mine[b]>0:
            mine[b]-=1
            if mine[b] ==0:
                if b in info:
                    for p in info[b]:
                        pre[p]+=1
        else:
            result = "Lier!"
print(result)