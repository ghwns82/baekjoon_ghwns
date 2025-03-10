import sys
input = lambda: sys.stdin.readline().strip()
sys.setrecursionlimit(200000)

n,c = map(int,input().split())
graph = [[] for i in range(n+1)]
depth = [0]*(n+1)
for i in range(n-1):
    a,b = map(int,input().split())
    graph[a].append(b)
    graph[b].append(a)
    
visited=[0]*(n+1)
time=0
order = [0]*(n+1)
tree_range=[[0,0] for i in range(n+1)]
work = [[c,1]]
while work:
    node,d = work[-1]
    if visited[node]:
        tree_range[node][1] = time
        work.pop()

        continue
    visited[node]=1
    order[node] = time
    depth[node] = d
    time +=1
    tree_range[node][0] = time
    for next in graph[node]:
        if not visited[next]:
            work.append([next,d+1])

    

class SegmentTree:
    def __init__(self):
        self.n = n
        self.tree = [0]*(4*self.n)

    def update(self, index, node=1, start=0, end=n-1):
        self.tree[node] += 1
        if start == end:
            return
        else:
            mid = (start+end)//2
            if index <=mid:
                self.update(index,node*2,start,mid)
            else:
                self.update(index,node*2+1,mid+1,end)
    def search(self, left,right,node=1,start=0,end=n-1):
        if right<start or left>end:
            return 0
        
        if left<=start and end<=right:
            return self.tree[node]
        mid = (start+end)//2
        left_sum = self.search(left,right,node*2,start,mid)
        right_sum = self.search(left,right,node*2+1,mid+1,end)
        return left_sum+right_sum
        

        
segtree=SegmentTree()

for _ in range(int(input())):
    qtype, a = map(int,input().split())
    if qtype==1:
        segtree.update(order[a])
    else:
        s,e = tree_range[a]
        print(segtree.search(s-1,e-1) * depth[a])
