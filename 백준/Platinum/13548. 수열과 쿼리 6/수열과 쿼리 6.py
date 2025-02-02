import sys
from collections import Counter
input = lambda: sys.stdin.readline().strip()
n= int(input())
arr =list(map(int,input().split()))
m = int(input())
sq = n**0.5
queries = sorted([list(map(int,input().split()))+[i,0] for i in range(m)], key=lambda x: (x[0]//sq,x[1]) )
ct = Counter()
table = [0]*100_001
ct[arr[0]]+=1
table[1]=1
left,right=1,1
res = 0
def plus(v,res):
    table[ct[v]]-=1
    if ct[v]==res:
        res+=1
    ct[v]+=1
    table[ct[v]]+=1
    return res
    
def minus(v,res):
    table[ct[v]]-=1
    if res==ct[v] and table[ct[v]]==0:
        res-=1
    ct[v]-=1
    table[ct[v]]+=1
    return res

for query in queries:
    while left < query[0]:
        res=minus(arr[left-1],res)
        left+=1
    while left > query[0]:
        left-=1
        res=plus(arr[left-1],res)
        
    while right < query[1]:
        right+=1
        res=plus(arr[right-1],res)
    while right > query[1]:
        res = minus(arr[right-1],res)
        right-=1
    query[3]=res

for query in sorted(queries, key=lambda x: x[2]):
    print(query[3])