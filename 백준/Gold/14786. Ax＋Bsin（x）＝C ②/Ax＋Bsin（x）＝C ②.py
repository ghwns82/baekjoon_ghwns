import sys
from math import sin
input = lambda: sys.stdin.readline().strip()

a,b,c = map(int,input().split())

s,e = (c-b)/a, (c+b)/a

cnt=0
while s<e and cnt<100:
    m =(s+e)/2
    v = a*m+b*sin(m)
    if c==v:
        break
    elif c>v:
        s=m
    else:
        e=m
    cnt+=1
    # print([s,m,e],v)
print(m)