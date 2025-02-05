import sys
from bisect import bisect_right
input = lambda: sys.stdin.readline().strip()
n=int(input())
arr = list(map(int,input().split()))
m = int(input())
queries = [list(map(int,input().split())) for i in range(m)]

class Node:
    def __init__(self,start,end,left=None,right=None,list=None):
        self.range = (start,end)
        if list:
            self.data = list
        else:
            self.data = sorted(left.data+right.data)
        self.left=left
        self.right=right

class MS:
    def __init__(self):
        self.root=self.create(0,n-1)
    def create(self, start,end):
        if start==end:
            return Node(start,end,list=[arr[start]])
        mid = (start+end)//2
        return Node(start,end,self.create(start,mid),self.create(mid+1,end))
    def search(self, nd:Node,s,e,k):
        
        start,end =nd.range
        if start==s and end ==e:
            return len(nd.data)-bisect_right(nd.data, k)
        
        mid = (start+end)//2
        # print(nd.range,mid,[s,e])
        if mid<s:
            return self.search(nd.right, s,e,k)
        elif e<=mid:
            return self.search(nd.left,s,e,k)
        else:
            return self.search(nd.left, s,mid,k) + self.search(nd.right,mid+1,e,k)
        
    
mstree = MS()
last_ans = 0
for i,j,k in queries:
    last_ans = mstree.search(mstree.root,
                             (i^last_ans)-1,
                             (j^last_ans)-1,
                             k^last_ans)
    print(last_ans)