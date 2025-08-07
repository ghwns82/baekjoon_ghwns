import sys
input = lambda : sys.stdin.readline().rstrip()
n,k = map(int,input().split())
arr = list(map(int,input().split()))
n+=1


def modify(list,num,v):
    index = n+ num
    while index>0:
        list[index]+=v
        list[index] %=(10**9+7)
        index>>=1

def search(list,l,r):
    l = n+ l
    r = n + r
    res = 0
    while l<=r:
        if l==r:
            res+=list[l]
            res %=(10**9+7)
            break
        if l%2==1:
            res+=list[l]
            res %=(10**9+7)
            l+=1
        if r%2==0:
            res+=list[r]
            res %=(10**9+7)
            r-=1
        l>>=1
        r>>=1
    return res
seg = [[0]*n*2 for i in range(k)]
for num in arr:
    modify(seg[0],num,1)
    for i in range(k-1):
        s = search(seg[i], 0, num-1)

        modify(seg[i+1], num, s)
print(seg[-1][1])