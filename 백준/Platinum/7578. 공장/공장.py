import sys
input = sys.stdin.readline

n = int(input())
dic = {}
for i in input().split():
    dic[i] = len(dic)

seg = [0]*(n*2+1)

def modify(list,num,v):
    index = n + num
    while index>0:
        list[index]+=v
        index>>=1

def search(list,l,r):
    if l==r:
        return list[N + 1 + l]
    l = n + l
    r = n + r
    res = 0
    while l<=r:
        if l==r:
            res+=list[l]
            break
        if l%2==1:
            res+=list[l]
            l+=1
        if r%2==0:
            res+=list[r]
            r-=1
        l>>=1
        r>>=1
    return res
result = 0
for i in input().split():
    num = dic[i]
    result += search(seg,num,n)
    modify(seg,num,1)
print(result)