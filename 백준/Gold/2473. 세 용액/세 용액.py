import sys
input= sys.stdin.readline
n=int(input())
a=sorted(map(int,input().rstrip().split()))
result = [4_000_000_003,0,0,0]
# print('a',a)


if a[0]*a[-1]<0:
    for std in range(n-1):
        # print(std)
        s,e=std+1, n-1
        while s<e:
            tmp=a[std]+a[s]+a[e]
            if abs(result[0])>abs(tmp):
                result = [tmp, a[std],a[s],a[e]]
            if tmp>0:
                e-=1
            elif tmp<0:
                s+=1
            else:
                print(a[std],a[s],a[e])
                sys.exit() 
    print(*result[1:])
else:
    if a[0]>=0:
        print(a[0],a[1],a[2])
    else:
        print(a[-3],a[-2],a[-1])
