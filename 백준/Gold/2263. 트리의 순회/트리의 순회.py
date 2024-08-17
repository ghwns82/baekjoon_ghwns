import sys
input = sys.stdin.readline
sys.setrecursionlimit(1000003)

n = int(input())
inorder = list(map(int,input().split()))
postorder = list(map(int,input().split()))

class trees:
    def __init__(self):
        self.root=None
class node:
    def __init__(self,data):
        self.parent=None
        self.data = data
        self.left=None
        self.right=None


where = dict()
for i in range(n):
    where[inorder[i]]=i

def search(s,e):
    data = postorder.pop()
    # print(s,e,data)
    # print(postorder)
    if s==e:
        return node(data)
    else:
        nd = node(data)
        index = where[data]
        # print('index',index)
        if index+1<=e:
            nd.right = search(index+1,e)
        if index -1 >=s:
            nd.left= search(s,index-1)
        return nd
        
        
tree = trees()
tree.root = search(0,n-1)

def preorder(x:node):
    print(x.data, end=' ')
    if x.left:
        preorder(x.left)
    if x.right:
        preorder(x.right)

preorder(tree.root)

# (s,e)
# index,x
# x.right = (index+1,e)
# x.left = (s,index-1)
