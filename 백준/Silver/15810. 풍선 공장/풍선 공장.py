import sys

input = lambda: sys.stdin.readline().strip()

N,M = map(int,input().split())
staff = list(map(int,input().split()))

def get(t):
    result = 0
    for s in staff:
        result+=t//s
    return result

s,e = 1, 1_000_000*1_000_000
# s,e = 1, 1_00
result =e
while s<=e:
    m = (s+e)//2
    
    v = get(m)
    if v>=M:
        result = min(result,m)
    # print((s,m,e),v,['left','right'][v<M])
    if v<M:
        s = m+1
    else:
        e = m-1
        
        
        
print(result)