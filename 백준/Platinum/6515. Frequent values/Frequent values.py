import sys
from collections import Counter
input = lambda: sys.stdin.readline().strip()

def plus(v):
    global res
    table[ct[v]]-=1
    ct[v]+=1
    table[ct[v]]+=1
    res = max(res,ct[v])

def minus(v):
    global res
    table[ct[v]]-=1
    if res == ct[v] and table[res] == 0:
        res-=1
    ct[v]-=1
    table[ct[v]]+=1

while True:
    try:
        n,q = map(int,input().split())
    except:
        break
    arr = list(map(int,input().split()))
    sq = n**0.5
    queries = sorted([list(map(int,input().split()))+[i] for i in range(q)],
                    key = lambda x : (x[0]//sq, x[1]))
    answer=[0]*q
    ct= Counter()
    left,right=1,1
    ct[arr[left-1]]+=1
    table = [0]*(n+1)
    table[1]=1
    res=1

    for q in queries:
        while left > q[0]:
            left-=1
            v=arr[left-1]
            plus(v)
            
        while right < q[1]:
            right+=1
            v=arr[right-1]
            plus(v)
        
        while left < q[0]:
            v=arr[left-1]
            minus(v)
            left+=1
        
        while right>q[1]:
            v=arr[right-1]
            minus(v)
            right-=1
        answer[q[2]]=res
            
    for v in answer:
        print(v)