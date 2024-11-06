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
        self.data = None


segtree = tree()


def create(s, e):
    nd = node(s, e)
    if e - s + 1 == n:
        segtree.root = nd
    if s == e:
        nd.data = a[s]
    else:
        m = (s + e) // 2
        nd.left_child = create(s, m)
        nd.right_child = create(m + 1, e)
        nd.data = nd.left_child.data + nd.right_child.data
    return nd


def search(s, e, cnd):
    cs, ce = cnd.range
    cm = (cs + ce) // 2
    if s == cs and e == ce:
        return cnd.data
    elif cm < s:
        return search(s, e, cnd.right_child)
    elif cm >= e:
        return search(s, e, cnd.left_child)
    else:
        return search(s, cm, cnd.left_child) + search(cm + 1, e,
                                                      cnd.right_child)


def update(cnd, target, new):
    cs, ce = cnd.range
    cm = (cs + ce) // 2
    if target == cs == ce:
        tmp = new - cnd.data
        cnd.data = new
    elif cs <= target <= cm:
        tmp = update(cnd.left_child, target, new)
        cnd.data += tmp
    else:
        tmp = update(cnd.right_child, target, new)
        cnd.data += tmp
    return tmp


n = int(input())
a = list(map(int,input().split()))
create(0, n - 1)
m = int(input())
modify = []
calsum = []
cnt=0
for i in range(m):
    cmd = list(map(int,input().split())) + [i+1]
    if cmd[0]==1:
        cnt+=1
        modify.append(cmd+[cnt])
    else:
        calsum.append(cmd)

calsum.sort(key=lambda x : -x[1])

# print('calsum')
# print(calsum)
result = []
while calsum and calsum[-1][1] == 0:
    *_, i,j, cindex = calsum.pop()
    result.append([search(i-1,j-1, segtree.root), cindex])
for mc in modify:
    _, i, v, __ ,index = mc
    update(segtree.root, i-1, v)
    # print('mc',mc)
    while calsum and calsum[-1][1] == index:
        *_, i,j, cindex = calsum.pop()
        result.append([search(i-1,j-1, segtree.root), cindex])
    # print(result)

result.sort(key=lambda x: x[1])

for i,_ in result:
    print(i)