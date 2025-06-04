# import sys
# input = sys.stdin.readline

p,w = map(int,input().split())
c,v = map(int,input().split())
roads = [list(map(int,input().split())) for i in range(w)]
roads.sort(key = lambda x: x[2])

parent = list(range(p))
def find(x):
    if x!=parent[x]:
        parent[x] = find(parent[x])
    return parent[x]

def union(a,b):
    ra,rb = find(a),find(b)
    parent[max(ra,rb)]=min(ra,rb)

while True:
    x,y,z=roads.pop()
    union(x,y)
    if find(v) == find(c):
        print(z)
        break


