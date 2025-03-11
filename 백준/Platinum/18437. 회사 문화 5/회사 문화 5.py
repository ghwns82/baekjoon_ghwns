import sys
sys.setrecursionlimit(10**5)

input = lambda: sys.stdin.readline().strip()
n = int(input())
child = [[] for i in range(n+1)]
person = 0
for parent in map(int,input().split()):
    person += 1
    if parent == 0:
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
        self.data=(end-start+1)
        self.lazy = -1 # on : 1, off: 0, None : -1
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
    
    def propagate(self, cur: Node):
        start, end = cur.range
        if cur.lazy>-1:
            cur.data = (end-start+1) * cur.lazy
            if cur.left_child:  # 리프 노드가 아닐 경우 lazy 값 전파
                cur.left_child.lazy = cur.lazy
                cur.right_child.lazy = cur.lazy
            cur.lazy = -1
     
    def modify(self, cur: Node, s, e, k):
        start, end = cur.range
        self.propagate(cur)
        
        if s > end or e < start:  # 범위를 벗어난 경우
            return
        if s <= start and end <= e:  # 범위 전체가 포함되는 경우
            cur.lazy = k  # Lazy 값 반영
            self.propagate(cur)  # 현재 노드에 즉시 반영
            return
        
        self.modify(cur.left_child, s, e,k)
        self.modify(cur.right_child, s, e,k)
        cur.data = cur.left_child.data + cur.right_child.data
        
    def search(self, cur: Node, s, e):
        start, end = cur.range
        self.propagate(cur)
        
        if s > end or e < start:  # 범위를 벗어난 경우
            return 0
        if s <= start and end <= e:  # 범위 전체가 포함되는 경우
            return cur.data
        
        return self.search(cur.left_child, s, e) + self.search(cur.right_child, s, e)
    
    def show(self, cur: Node):
        print(cur.range, 'data, lazy:', [cur.data, cur.lazy])
        if cur.left_child:
            self.show(cur.left_child)
            self.show(cur.right_child)
    
    
segtree=Seg()
for _ in range(int(input())):
    ctype, employee = list(map(int, input().split()))
    s,e= tree_range[employee]
    if ctype==3:
        if s+1>e-1:
            print(0)
            continue
        print(segtree.search(segtree.root, s+1,e-1))
        
        
    else:
        if s+1>e-1:
            continue
        segtree.modify(segtree.root, s+1, e-1, ctype==1)
        
    # segtree.show(segtree.root)
    