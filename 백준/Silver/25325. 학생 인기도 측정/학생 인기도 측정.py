import sys
n=int(input())
a = {i : 0 for i in input().split()}

for i in range(n):
    for j in input().split():
        a[j]+=1

for k,v in sorted(a.items(), key = lambda x: (-x[1],x[0])):
    print(k,v)