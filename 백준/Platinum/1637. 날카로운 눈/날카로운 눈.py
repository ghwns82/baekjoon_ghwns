import sys
from collections import Counter
input = lambda: sys.stdin.readline().strip()

dummies = [list(map(int,input().split())) for _ in range(int(input()))]

def search(n):
    result = 0
    for a,b,c in dummies:
        if a>n or a>b:
            continue
        result +=(min(b,n)-a)//c+1
    return result

# print(search(1))
s,e = 1, 2_147_483_647

if search(e)%2==0:
    print('NOTHING')
    sys.exit()
result = e
while s<=e:
    m = (s+e)//2
    v = search(m)
    # print(s,m,e)
    if v%2:
        e = m-1
        result = min(result, m)
    else:
        s = m+1
print(result, search(result)-search(result-1))