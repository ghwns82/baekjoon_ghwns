import sys

input = sys.stdin.readline


class tree:

    def __init__(self):
        self.root = None


class node:

    def __init__(self, s, e):
        self.parent = None
        self.left_child = None
        self.right_child = None
        self.range = (s, e)
        self.min_value = None
        self.min_index=None
    def __lt__(self,other):
        if self.min_value==other.min_value:
            if self.min_index<other.min_index:
                return [self.min_value, self.min_index]
            else:
                return [other.min_value,other.min_index]
        else:
            if self.min_value<other.min_value:
                return [self.min_value, self.min_index]
            else:
                return [other.min_value,other.min_index]

segtree = tree()


def create(s, e):
    nd = node(s, e)
    if e - s + 1 == n:
        segtree.root = nd
    if s == e:
        nd.min_value = a[s]
        nd.min_index = s
    else:
        m = (s + e) // 2
        nd.left_child = create(s, m)
        nd.right_child = create(m + 1, e)
        # print('lt',nd.left_child < + nd.right_child)
        nd.min_value,nd.min_index = nd.left_child < nd.right_child
        
    return nd




def update(cnd,target,new):
    cs, ce = cnd.range
    cm = (cs + ce) // 2
    if cs==ce:
        cnd.min_value = new
    else:
        if target<=cm:
            update(cnd.left_child,target,new)
        else:
            update(cnd.right_child,target,new)
        cnd.min_value,cnd.min_index = cnd.left_child < cnd.right_child
    



n = int(input())
a = list(map(int,input().split()))
create(0, n - 1)
m = int(input())
for _ in range(m):
    cmd = list(map(int,input().split()))
    if cmd[0]==2:
        print(segtree.root.min_index+1)
    else:
        update(segtree.root,cmd[1]-1,cmd[2])