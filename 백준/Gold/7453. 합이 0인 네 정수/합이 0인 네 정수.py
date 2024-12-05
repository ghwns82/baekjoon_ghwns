import sys
input = sys.stdin.readline
a,b,c,d = [],[],[],[]
for _ in range(int(input())):
    t1,t2,t3,t4 = map(int,input().rstrip().split())
    a.append(t1)
    b.append(t2)
    c.append(t3)
    d.append(t4)

def sum(a,b,c,d):
    x = dict()
    
    for i in a:
        for j in b:
            t = i+j
            x[t] = x.get(t,0)+1
    result = 0
    for i in c:
        for j in d:
            t = -i-j
            result += x.get(t,0)
    return result
print(sum(a,b,c,d))