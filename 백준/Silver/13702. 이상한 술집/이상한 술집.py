import sys
input = sys.stdin.readline
n,k = map(int,input().split())
waters=[int(input()) for i in range(n)]
def cal(x):
    s = 0
    for water in waters:
        s+=water//x
    return s

s,e = 0, 2<<31-1
result = 0
while s<=e:
    m = (s+e)//2
    if cal(m)<k:
        e=m-1
    else:
        s=m+1
        result = m
print(result)

    