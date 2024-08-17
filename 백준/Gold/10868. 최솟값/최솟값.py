import sys
input= sys.stdin.readline

class tree:
    def __init__(self):
        self.root = None


class node:
    def __init__(self, s, e):
        self.parent = None
        self.left_child = None
        self.right_child = None
        self.range = (s, e)
        self.data = None


segtree = tree()


def create(s, e):
    nd = node(s, e)
    if e - s + 1 == n:
        segtree.root = nd
    if s == e:
        nd.data1 = a[s]
        nd.data2 = a[s]
    else:
        m = (s + e) // 2
        nd.left_child = create(s, m)
        nd.right_child = create(m + 1, e)
        nd.data1 = min(nd.left_child.data1, nd.right_child.data1)
        # nd.data2 = max(nd.left_child.data2, nd.right_child.data2)
    # print((s,e),nd.data)
    return nd

def search(s,e,cnd):
    cs,ce = cnd.range
    cm = (cs+ce)//2
    # print([s,e],[cs,ce])
    if s==cs and e==ce:
        return cnd.data1
    elif cm<s:
        return search(s,e,cnd.right_child)
    elif cm>=e:
        return search(s,e,cnd.left_child)
    else:
        tmp1,tmp2=search(s,cm,cnd.left_child), search(cm+1,e,cnd.right_child)
        return min(tmp1,tmp2)


n,m = map(int,input().split())
a = [int(input())  for i in range(n)]
create(0,n-1)

for i in range(m):
    x,y = map(int,input().split())
    print(search(x-1,y-1,segtree.root))