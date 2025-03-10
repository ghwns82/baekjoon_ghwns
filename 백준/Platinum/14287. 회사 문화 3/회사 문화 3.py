import sys
input = lambda : sys.stdin.readline().rstrip()
n,m = map(int,input().split())
sys.setrecursionlimit(10**5)
child = [[] for i in range(n+1)]
index=0
for parent in map(int,input().split()):
    index+=1
    if parent==-1:
        continue
    child[parent].append(index)

tree_range = [[0,0] for i in range(n+1)]
order = [0]*(n+1)

visited = 0
def ett(person):
    global time,visited
    order[person]= visited
    visited+=1
    tree_range[person][0] = visited
    
    
    for c in child[person]:
        ett(c)
    
    tree_range[person][1] = visited
    
ett(1)
# print('order',order)
# print('tree_range',tree_range)

class Node:
    def __init__(self,start,end,left=None,right=None):
        self.range = (start,end)
        self.data=0
        self.lazy = 0
        self.left_child = left
        self.right_child = right
        
class Seg:
    def __init__(self):
        self.root = self.create(0,n-1)
    
    def create(self,start,end):
        if start==end:
            return Node(start,end)
        mid = (start+end)//2
        return Node(start,end,
            left = self.create(start,mid),
            right = self.create(mid+1,end)
            )
    
    def modify(self, cur:Node, index, k):
        start,end=cur.range
        cur.data +=k
        if start==end:
            return
        mid = (start+end)//2
        if mid<index:
            self.modify(cur.right_child,index,k)
        else:
            self.modify(cur.left_child,index,k)
        
    def search(self,cur:Node,s,e):
        start,end=cur.range
        mid = (start+end)//2
        
        if start == s and end==e:
            return cur.data
        elif mid<s:
            return self.search(cur.right_child,s,e)
        elif mid>=e:
            return self.search(cur.left_child,s,e)
        else:
            return self.search(cur.right_child,mid+1,e)\
            + self.search(cur.left_child,s,mid)
    def show(self, cur:Node):
        print(cur.range, [cur.data, cur.lazy])
        if cur.left_child:
            self.show(cur.left_child)
            self.show(cur.right_child)     
        
segtree=Seg()

for _ in range(m):
    ctype, *content = list(map(int,input().split()))
    if ctype==1:
        i,w = content
        segtree.modify(segtree.root,order[i],w)
    else:
        s,e=tree_range[content[0]]
        if s==e:
            print(0)
        else:
            print(segtree.search(segtree.root, s-1,e-1))
            
    # segtree.show(segtree.root)