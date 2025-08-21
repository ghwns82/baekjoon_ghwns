import sys
input = sys.stdin.readline

import math
n = int(input())
queries = [int(input()) for i in range(n)]
N = 2**math.ceil(math.log2(n))
# print('N',N)
seg = [0]*(N*2 + 1)
def modify(list,num,v):
    index = N+ num
    while index>0:
        list[index]+=v
        index>>=1
def search(list,goal):
    # print('search')
    index = 1
    while index*2+1 < len(seg):
        l,r = seg[index<<1], seg[(index<<1) +1]
        # print('goal','lr',[goal,(l,r)])
        if r==0 or l>=goal and l>0:
            index<<=1
        else:
            index<<=1
            index+=1
            goal-=l
        # print('index',index)
    return index-N

for i in range(n):
    modify(seg,i,1)
answer = [0]*n
# print('seg',seg)
for i in range(n):
    q = queries[i]
    index = search(seg,q+1)
    # print(q,index, i+1)
    answer[index]=i+1
    # print(answer)
    modify(seg,index,-1)
print(*answer,sep='\n')