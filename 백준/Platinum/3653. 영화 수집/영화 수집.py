import sys
input = sys.stdin.readline

for _ in range(int(input())):
    n,m = map(int,input().split())
    queries = list(map(int,input().split()))
    arr = list(range(n-1,-1,-1))
    N = n+m
    seg = [0]*(N*2+2)
    def modify(list,num,v):
        index = N+ num
        while index>0:
            list[index]+=v
            index>>=1
    def search(list,l,r):
        l = N + l
        r = N + r
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
        modify(seg,i,1)

    for i in range(m):
        q = queries[i]
        index = arr[q-1]
        print(search(seg,index+1,N),end = ' ')
        arr[q-1] = i+n
        modify(seg,index,-1)
        modify(seg,i+n,1)
    print()