import sys
input = lambda : sys.stdin.readline().strip()

class Node:
    def __init__(self,s,e) -> None:
        self.data = 0
        self.range = (s,e)
        self.left = None
        self.right = None


def create(node):
    s,e =node.range
    if s!=e:
        m=(s+e)//2
        node.left = Node(s,m)
        create(node.left)
        node.right= Node(m+1,e)
        create(node.right)
def modify(node, target_index, value):
    s,e =node.range
    m=(s+e)//2
    if s==e==target_index:
        diff = value-node.data
        node.data = value
    elif s<=target_index<=m:
        diff = modify(node.left, target_index,value)
        node.data +=diff
    elif m<target_index<=e:
        diff =modify(node.right, target_index,value)
        node.data +=diff
    # print(node.range,node.data)
    return diff
def search(node,ts,te):

    s,e =node.range
    m=(s+e)//2
    if ts==s and te==e:
        return node.data
    elif te<=m:
        return search(node.left,ts,te)
    elif ts>m:
        return search(node.right,ts,te)
    else:
        return search(node.left,ts,m) + search(node.right,m+1,te)

    

n,m = map(int,input().split())
arr = [0]*n
root = Node(0,n-1)
create(root)
# print('done')
for _ in range(m):
    a,b,c = map(int,input().split())
    if a==1:
        modify(root,b-1,c)
    else:
        print(search(root,min(b-1,c-1),max(b-1,c-1)))
        

# 3 5
# 1 1 3
# 1 3 2
# 1 3 5
# 1 2 4
# 0 1 2
#ans: 7