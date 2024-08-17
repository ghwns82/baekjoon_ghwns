def cal(n,m):
    result=0
    for i in range(1,n+1):
        result+=min(n,m//i)
    return result

n = int(input())
k = int(input())
s,e = 1,n**2
while s<e:
    m = (s+e)//2
    v=cal(n,m)
    if k>v:
        s=m+1
    else:
        e=m
# 1 2 2 3 3 4 4 4 6 6 8 8 9 12 12 16
print(e)