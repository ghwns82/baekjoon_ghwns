import sys
input = lambda : sys.stdin.readline().rstrip()
for i in range(int(input())):
    n = int(input())
    ys = set()
    island=[]
    for i in range(n):
        a,b=map(int,input().split())
        island.append([a,b])
        ys.add(b)
    ys = sorted(ys)
    yd = {}
    for i in range(len(ys)):
        yd[ys[i]]=i
    island.sort(key=lambda x:(x[0],-x[1]))    
    N = 1<<17
    seg = [0]*N*2
    def modify(list,num,v):
        index = N + num
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
    result = 0
    for a,b in island:
        result+=search(seg,yd[b],N-1)
        modify(seg, yd[b], 1)
    print(result)