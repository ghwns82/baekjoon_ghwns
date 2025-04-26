import sys
input = lambda : sys.stdin.readline().rstrip()
n,m = map(int,input().split())
space = [input() for i in range(n)]
parent = list(range(n*m))
rank = [1]*n*m
def union(a,b):
    ra,rb = find(a),find(b)
    if ra==rb:
        return
    x,y = min(ra,rb), max(ra,rb)
    parent[y]=x
    find(a)
    find(b)

def find(x):
    if x!=parent[x]:
        # print(x,'rank update')
        rank[parent[x]]+=rank[x]
        rank[x]=0
        parent[x]=find(parent[x])
    return parent[x]
    
        

for i in range(n):
    for j in range(m):
        if space[i][j]=='1':
            continue
        index = m*i+j
        if j>0 and space[i][j-1]=='0':
            union(index-1,index)
            # print('left',i,j, rank)
            # print(parent)
        if i>0 and space[i-1][j]=='0':
            union(index-m,index)
            # print('up  ',i,j, rank)
        
result = [[0]*m for i in range(n)]
direction = [[0,1],[1,0],[-1,0],[0,-1]]
for i in range(n):
    for j in range(m):
        if space[i][j]=='0':
            continue
        result[i][j]+=1
        visited=set()
        for a,b in direction:
            dx,dy = i+a, j+b
            if 0<=dx<n and 0<=dy<m and  space[dx][dy]=='0':
                index = dx*m+dy
                pkey = find(index)
                if pkey in visited:
                    continue
                visited.add(pkey)
                result[i][j]+=rank[pkey]
                # print('index',[i,j],'+= rank',[dx,dy],rank[index])
        result[i][j]%=10
for i in result:
    print(*i, sep='')

