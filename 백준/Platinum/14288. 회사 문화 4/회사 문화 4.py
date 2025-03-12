import sys
input = lambda: sys.stdin.readline().strip()
n,m = map(int,input().split())
child = [[] for i in range(n+1)]
person = 0
for parent in map(int,input().split()):
    person += 1
    if parent == -1:
        continue
    child[parent].append(person)

tree_range = [[-1,-1] for i in range(n+1)]
order = [-1]*(n+1)

time = 0
def ett(person):
    global time
    order[person]= time # index
    tree_range[person][0] = time # start_index
    time+=1
    
    for c in child[person]:
        ett(c)
    
    tree_range[person][1] = time # end_index + 1
    
ett(1)

class Node:
    def __init__(self,start,end,left=None,right=None):
        self.range = (start,end)
        self.data=[0,0]
        self.lazy = [0,0]
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
    
    def mdf0(self, cur:Node, s,e, k):
        start,end=cur.range
        if start==s and end ==e:
            cur.lazy[0]+=(e-s+1)*k
            return
        else:
            cur.data[0]+=(e-s+1)*k
        mid = (start+end)//2
        if mid<s:
            self.mdf0(cur.right_child,s,e,k)
        elif mid>=e:
            self.mdf0(cur.left_child,s,e,k)
        else:
            self.mdf0(cur.left_child,s,mid,k)
            self.mdf0(cur.right_child,mid+1,e,k)
    
    def src0(self,cur:Node,index):
        start,end=cur.range
        mid = (start+end)//2
        if cur.lazy[0]!=0:
            cur.data[0]+=cur.lazy[0]
            if cur.left_child:
                cur.left_child.lazy[0] += cur.lazy[0]//(end-start+1) *(mid-start+1)
                cur.right_child.lazy[0] += cur.lazy[0]//(end-start+1) *(end-mid)
            cur.lazy[0]=0
        if start == end==index:
            return cur.data[0]
        if mid<index:
            return self.src0(cur.right_child,index)
        else:
            return self.src0(cur.left_child,index)
    
    def mdf1(self, cur:Node, index, k):
        start,end=cur.range
        cur.data[1] +=k
        if start==end:
            return
        mid = (start+end)//2
        if mid<index:
            self.mdf1(cur.right_child,index,k)
        else:
            self.mdf1(cur.left_child,index,k)
        
    def src1(self,cur:Node,s,e):
        start,end=cur.range
        mid = (start+end)//2
        
        if start == s and end==e:
            return cur.data[1]
        elif mid<s:
            return self.src1(cur.right_child,s,e)
        elif mid>=e:
            return self.src1(cur.left_child,s,e)
        else:
            return self.src1(cur.right_child,mid+1,e)\
            + self.src1(cur.left_child,s,mid)
    
    
    
    def show(self, cur: Node):
        print(cur.range, 'data, lazy:', [cur.data, cur.lazy])
        if cur.left_child:
            self.show(cur.left_child)
            self.show(cur.right_child)

direction = 0    

segtree=Seg()
for _ in range(m):
    ctype, *content = list(map(int, input().split()))

    if ctype==3:
        direction ^= 1
        
    elif ctype == 1:
        i, w = content
        if direction:
            segtree.mdf1(segtree.root, order[i], w)
        else:
            s,e = tree_range[i]
            segtree.mdf0(segtree.root, s,e-1, w)
        
    else:
        v0 = segtree.src0(segtree.root, order[content[0]])
        s,e=tree_range[content[0]]
        if s>e-1:
            v1=0
        else:
            v1 = segtree.src1(segtree.root, s,e-1)
        print(v0+v1)
            
        
    # segtree.show(segtree.root)
    