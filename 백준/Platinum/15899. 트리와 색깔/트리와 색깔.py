import sys
input = sys.stdin.readline
sys.setrecursionlimit(2 * (10**5))

# import sys
# input = sys.stdin.readline
from collections import defaultdict



n,m,c = map(int,input().split())
array = list(map(int,input().split()))
color_to_node =  defaultdict(list)
for i in range(1, n + 1):
    color_to_node[array[i - 1]].append(i)

graph = [[] for i in range(n+1)]
for i in range(n-1):
    a,b = map(int,input().split())
    graph[a].append(b)
    graph[b].append(a)


# Euler Tour Trick: in_time / out_time 계산
in_time = [0] * (n + 1)
out_time = [0] * (n + 1)
time = 0

def dfs(u, parent):
    global time
    in_time[u] = time
    time += 1
    for v in graph[u]:
        if v != parent:
            dfs(v, u)
    out_time[u] = time - 1

dfs(1, -1)

query=[[] for i in range(c+1)]
for i in range(m):
    a,b = map(int,input().split())
    query[b].append(a)

result = 0

class node:
    def __init__(self,start,end):
        self.data=0
        self.left=None
        self.right=None
        self.range=(start,end)

class seg:
    def __init__(self):
        self.root = self.create(0,n)
    def create(self,start,end):
        nd = node(start,end)
        if start!=end:
            mid = (start+end)//2
            nd.left = self.create(start,mid)
            nd.right = self.create(mid+1,end)
        return nd
    def search(self,cur,start,end):
        s,e=cur.range
        # print('cur',cur.range)
        if s==start and e==end:
            return cur.data
        m=(s+e)//2
        result = 0
        if end > m:
            result += self.search(cur.right,max(m+1,start),end)
        if start<=m:
            result += self.search(cur.left,start,min(m,end))
        return result
    def update(self, cur, index):
        while True:
            s,e=cur.range
            cur.data+=1
            if s==e:
                break
            m=(s+e)//2
            if index<=m:
                cur = cur.left
            else:
                cur = cur.right



tree = seg()
for color in range(1,c+1):
    for node in color_to_node[color]:
        tree.update(tree.root, in_time[node]+1)
    
    for node in query[color]:
        s,e = in_time[node], out_time[node]
        result+=tree.search(tree.root, 0,e+1)
        result-=tree.search(tree.root, 0,s)
        
        result %= 1000000007


print(result)

