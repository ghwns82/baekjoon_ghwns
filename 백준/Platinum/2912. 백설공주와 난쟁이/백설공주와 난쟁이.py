import sys
from collections import Counter
input = lambda: sys.stdin.readline().strip()
n,c = map(int,input().split())
arr = list(map(int,input().split()))
m = int(input())
sq = n**0.5
queries = sorted([list(map(int,input().split()))+[i] for i in range(m)],
                 key = lambda x : (x[0]//sq, x[1]))
answer=[0]*m
ct= Counter()
left,right=1,1
ct[arr[left-1]]+=1
# table = [set() for i in range(n+1)]
# table[1].add(arr[left-1])
# res = 1

def plus(hat):
    # print(table, ct[hat])
    # global res
    # if res == ct[hat]:
        # res+=1
    # table[ct[hat]].discard(hat)
    ct[hat]+=1
    # table[ct[hat]].add(hat)
    
def minus(hat):
    # global res
    # table[ct[hat]].discard(hat)
    # if not table[ct[hat]]:
        # res-=1
    ct[hat]-=1
    # table[ct[hat]].add(hat)
    

for q in queries:
    # print('query',q)
    while left > q[0]:
        left-=1
        plus(arr[left-1])
    while right < q[1]:
        # print('right plus')
        right+=1
        plus(arr[right-1])
    
    while left < q[0]:
        minus(arr[left-1])
        left+=1
    
    while right>q[1]:
        minus(arr[right-1])
        right-=1
        
    for k,v in ct.items():    
        if v>(q[1]-q[0]+1)/2:
            answer[q[2]]=k
            break
    
        
for v in answer:
    if v:
        print('yes',v)
    else:
        print('no')