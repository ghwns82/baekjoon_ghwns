import sys
from collections import Counter
input = lambda: sys.stdin.readline().strip()
n,q = map(int,input().split())
arr = list(map(int,input().split()))
sq = n**0.5
queries = sorted([list(map(int,input().split()))+[i] for i in range(q)],
                key = lambda x : (x[0]//sq, x[1]))
answer=[0]*q
ct= Counter()
left,right=1,1
ct[arr[left-1]]+=1
# table = [0]*(n+1)
table = [0]*(n+1)
table[1]=1
res=1

for q in queries:
    while left > q[0]:
        left-=1
        v=arr[left-1]
        if res == ct[v]:
            res+=1
        table[ct[v]]-=1
        ct[v]+=1
        table[ct[v]]+=1
        
    while right < q[1]:
        right+=1
        v=arr[right-1]
        if res == ct[v]:
            res+=1
        table[ct[v]]-=1
        ct[v]+=1
        table[ct[v]]+=1
    
    while left < q[0]:
        v=arr[left-1]
        table[ct[v]]-=1
        if table[res]==0:
            res-=1
        ct[v]-=1
        table[ct[v]]+=1
        left+=1
    
    while right>q[1]:
        v=arr[right-1]
        table[ct[v]]-=1
        if table[res]==0:
            res-=1
        ct[v]-=1
        table[ct[v]]+=1
        right-=1
    # print(q, table, res)
    answer[q[2]]=res
        
for v in answer:
    print(v)