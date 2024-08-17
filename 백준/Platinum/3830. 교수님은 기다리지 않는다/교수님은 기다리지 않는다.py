import sys
sys.setrecursionlimit(10**6)
input = lambda: sys.stdin.readline().strip()


def union2(a,b,c):
    ra,rac=find(a)
    rb,rbc =find(b)
    parent[max(ra,rb)]=min(ra,rb)
    diff = value[a]-c-value[b]
    if ra<rb:
        value[rb] = diff
        # value[a]+=value[ra]
    else:
        value[ra] = -diff
        # value[b]+=value[rb]
    find(a)
    find(b)


# 5 -> 3 = 8

# value[b]-value[a]+diff =c 
# 1 -> 3
# 0 -> 4   

# 1-> 4 -> 5
# ?-> 0 ->-2
# 0->-2 ->-4

def abs_cal(a,b):
    ra,rac=find(a)
    rb,rbc =find(b)
    if ra==rb:
        return rac-rbc
    else:
        return "UNKNOWN"

def find(x):
    if x==parent[x]:
        return parent[x],value[x]
    else:
        parent[x],parent_v = find(parent[x])
        value[x] += parent_v
        return parent[x],value[x]
while 1:
    n, m = map(int, input().split())
    if n==m==0:
        break
    parent = list(range(n + 1))
    value = [0]*(n+1)
    for _ in range(m):
        cmd = input().split()
        if cmd[0] == "!":
            _, a, b, c = cmd
            a,b,c = int(a),int(b),int(c)
            union2(a,b,c)

        else:
            _, a, b = cmd
            a,b = int(a),int(b)
            print(abs_cal(a,b))
        # print("parent",parent)
        # print("value",value)