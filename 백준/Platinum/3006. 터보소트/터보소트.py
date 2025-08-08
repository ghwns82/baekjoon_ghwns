import sys
input = sys.stdin.readline

n = int(input())
dic ={}
for i in range(n):
    dic[int(input())]=i
    


seg = [0]*(n*2+1)

def modify(list,num,v):
    index = n + num
    while index>0:
        list[index]+=v
        index>>=1

def search(list,l,r):
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
for i in range(n):
    modify(seg, i,1)

answer = [-1]*n
l,r=1,n
for i in range(n):
    if i%2:
        num = dic[r]
        result = search(seg,num,n)-1
        r-=1
    else:
        num=dic[l]
        result = search(seg,0,num)-1
        l+=1
    modify(seg,num,-1)
    print(result)