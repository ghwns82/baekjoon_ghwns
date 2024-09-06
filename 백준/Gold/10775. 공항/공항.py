import sys
input = lambda : sys.stdin.readline().strip()
g = int(input())
p = int(input())

    
parent = list(range(g+1))
def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])
    return parent[x]

def union(a,b):
    ra,rb = find(a),find(b)
    parent[max(ra,rb)] = min(ra,rb)
    find(a),find(b)

for i in range(p):
    x = int(input())
    # print(parent)
    if find(x)==0:
        print(i)
        sys.exit()
    union(find(x)-1,x)
else:
    print(p)