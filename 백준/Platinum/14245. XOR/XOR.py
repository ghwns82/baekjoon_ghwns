import sys

input = lambda: sys.stdin.readline().strip()

n = int(input())
arr = list(map(int, input().split()))


class Node:
    def __init__(self, start, end, left=None, right=None):
        self.range = (start, end)
        self.lazy = 0
        self.left_child = left
        self.right_child = right


class Seg:
    def __init__(self):
        self.root = self.create(0, n - 1)

    def create(self, start, end):
        if start == end:
            return Node(start, end)
        mid = (start + end) // 2
        return Node(
            start, end, left=self.create(start, mid), right=self.create(mid + 1, end)
        )

    def modify(self, cur: Node, s, e, k):
        start, end = cur.range
        if start == s and end == e:
            cur.lazy ^= k
            return
        if cur.lazy:
            cur.left_child.lazy ^= cur.lazy
            cur.right_child.lazy ^= cur.lazy
            cur.lazy = 0
        mid = (start + end) // 2
        if mid < s:
            self.modify(cur.right_child, s, e, k)
        elif mid >= e:
            self.modify(cur.left_child, s, e, k)
        else:
            self.modify(cur.left_child, s, mid, k)
            self.modify(cur.right_child, mid + 1, e, k)

    def search(self, cur: Node, index):
        start, end = cur.range
        mid = (start + end) // 2
        if start == end == index:
            return cur.lazy
        if cur.lazy:
            cur.left_child.lazy ^= cur.lazy
            cur.right_child.lazy ^= cur.lazy
            cur.lazy = 0

        if mid < index:
            return self.search(cur.right_child, index)
        else:
            return self.search(cur.left_child, index)

    def show(self, cur: Node):
        print(cur.range, [cur.data, cur.lazy])
        if cur.left_child:
            self.show(cur.left_child)
            self.show(cur.right_child)


segtree = Seg()

for _ in range(int(input())):
    ctype, *content = list(map(int, input().split()))
    if ctype == 1:
        i, j, k = content
        segtree.modify(segtree.root, i, j, k)
    else:
        index = content[0]
        print(arr[index] ^ segtree.search(segtree.root, index))
    # segtree.show(segtree.root)