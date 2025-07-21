import sys
input = lambda : sys.stdin.readline().rstrip()
n = int(input())
arr = list(map(int,input().split()))
dic={}
for i in sorted(arr):
    if i not in dic:
        dic[i] = len(dic)
for i in range(n):
    arr[i] = dic[arr[i]]

seg = [0]*n*2
def modify(list,num,v):
    index = n+ num
    while index>0:
        list[index]+=v
        index>>=1

def search(list,l,r):
    l = n+ l
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
for i in arr:
    if i<n-1:
        result+=search(seg,i+1,n-1)
    modify(seg,i,1)
print(result)