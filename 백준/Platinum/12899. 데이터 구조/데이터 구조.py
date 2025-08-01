import sys
from math import ceil,log2
input = sys.stdin.readline

n = int(input())
p=2000000
N = 2**ceil(log2(p))

seg = [0]*(N*2+1)


def modify(list,num,v):
    index = N + num
    while index>0:
        list[index]+=v
        index>>=1

def search(list,goal):
    index = 1
    while index < N:
        l,r = index<<1, (index<<1) + 1
        if list[l]>=goal:
            index = l
        else:
            goal -= list[l]
            index = r
    return index - N


for i in range(n):
    a,b = map(int,input().split())
    if a==1:
        modify(seg,b,1)
    else:
        c = search(seg,b)
        print(c)
        modify(seg,c,-1)