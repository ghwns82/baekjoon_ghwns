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
    
    def modify(self, cur:Node, s,e, k):
        start,end=cur.range
        if start==s and end ==e:
            cur.lazy+=(e-s+1)*k
            return
        else:
            cur.data+=(e-s+1)*k
        mid = (start+end)//2
        if mid<s:
            self.modify(cur.right_child,s,e,k)
        elif mid>=e:
            self.modify(cur.left_child,s,e,k)
        else:
            self.modify(cur.left_child,s,mid,k)
            self.modify(cur.right_child,mid+1,e,k)
        
    def search(self,cur:Node,index):
        start,end=cur.range
        mid = (start+end)//2
        if cur.lazy!=0:
            cur.data+=cur.lazy
            if cur.left_child:
                cur.left_child.lazy += cur.lazy//(end-start+1) *(mid-start+1)
                cur.right_child.lazy += cur.lazy//(end-start+1) *(end-mid)
            cur.lazy=0
        if start == end==index:
            return cur.data
        if mid<index:
            return self.search(cur.right_child,index)
        else:
            return self.search(cur.left_child,index)
        
        
segtree=Seg()

for _ in range(m):
    ctype, *content = list(map(int,input().split()))
    if ctype==1:
        i,w = content
        s,e = tree_range[i]
        segtree.modify(segtree.root,s-1,e-1,w)
    else:
        print(segtree.search(segtree.root, order[content[0]]))