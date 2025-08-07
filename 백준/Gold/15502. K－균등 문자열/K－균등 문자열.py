import sys
input = sys.stdin.readline
n,m = map(int,input().split())

parent = list(range(n))
def find(x):
    if x!= parent[x]:
        parent[x] = find(parent[x])
    return parent[x]

def union(a,b):
    ra, rb = find(a), find(b)
    ra,rb=min(ra,rb),max(ra,rb)
    parent[rb]=ra
    find(a),find(b)

for _ in range(m):
    a,b,c = map(int,input().split())
    for i in range(a-1,b-c):
        # print('union',i,i+c)
        union(i,i+c)

for i in range(n):
    find(i)

print(pow(2,len(set(parent)), 10**9+7))