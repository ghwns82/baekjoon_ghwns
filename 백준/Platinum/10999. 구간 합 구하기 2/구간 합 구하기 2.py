import sys
input = lambda : sys.stdin.readline().strip()
n,m,k = map(int,input().split())
arr = [int(input()) for i in range(n)]

class Node:
    def __init__(self,start,end,left=None,right=None):
        self.range = (start,end)
        if left:
            self.data = left.data+right.data
        else:
            self.data=arr[start]
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
        
    def search(self,cur:Node,s,e):
        start,end=cur.range
        mid = (start+end)//2
        if cur.lazy!=0:
            cur.data+=cur.lazy
            if cur.left_child:
                cur.left_child.lazy += cur.lazy//(end-start+1) *(mid-start+1)
                cur.right_child.lazy += cur.lazy//(end-start+1) *(end-mid)
            cur.lazy=0
        if start==s and e== end:
            return cur.data
        if mid<s:
            return self.search(cur.right_child,s,e)
        elif mid>=e:
            return self.search(cur.left_child,s,e)
        else:
            return self.search(cur.left_child,s,mid) +\
                 self.search(cur.right_child,mid+1,e)
    
    def show(self, cur:Node):
        print(cur.range, [cur.data, cur.lazy])
        if cur.left_child:
            self.show(cur.left_child)
            self.show(cur.right_child)
            

        

segtree=Seg()

for _ in range(m+k):
    ctype, *content = list(map(int,input().split()))
    if ctype==1:
        b,c,d = content
        segtree.modify(segtree.root,b-1,c-1,d)
    else:
        b,c = content
        print(segtree.search(segtree.root,b-1,c-1))
    # segtree.show(segtree.root)