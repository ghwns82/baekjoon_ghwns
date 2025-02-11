import sys

input = lambda: sys.stdin.readline().strip()
n = int(input())
arr = list(map(int, input().split()))


class Node:
    def __init__(self, start, end, left=None, right=None):
        self.range = (start, end)
        if left:
            self.data = left.data + right.data
        else:
            self.data = arr[start]
        self.lazy = 0
        self.lazy_count = 0
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
        mid = (start + end) // 2

        if start == s and end == e:
            cur.lazy += k
            cur.lazy_count += 1
            return
        else:
            ae = e - s + 1
            cur.data += ae * (2 * cur.lazy + (ae - 1) * cur.lazy_count) // 2
            cur.left_child.lazy += cur.lazy
            cur.left_child.lazy_count += cur.lazy_count
            cur.right_child.lazy += cur.lazy + (mid - start + 1) * cur.lazy_count
            cur.right_child.lazy_count += cur.lazy_count
            cur.lazy = 0
            cur.lazy_count = 0
        if mid < s:
            self.modify(cur.right_child, s, e, k)
        elif mid >= e:
            self.modify(cur.left_child, s, e, k)
        else:
            self.modify(cur.left_child, s, mid, k)
            self.modify(cur.right_child, mid + 1, e, k + mid - s + 1)

    def search(self, cur: Node, index):
        start, end = cur.range
        mid = (start + end) // 2
        if cur.lazy != 0:
            ae = end - start + 1
            cur.data += ae * (2 * cur.lazy + (ae - 1) * cur.lazy_count) // 2
            if cur.left_child:
                cur.left_child.lazy += cur.lazy
                cur.left_child.lazy_count += cur.lazy_count
                cur.right_child.lazy += cur.lazy + (mid - start + 1) * cur.lazy_count
                cur.right_child.lazy_count += cur.lazy_count
            cur.lazy = 0
            cur.lazy_count = 0
        if start == end == index:
            return cur.data
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
        i, j = content
        segtree.modify(segtree.root, i - 1, j - 1, 1)
    else:
        print(segtree.search(segtree.root, content[0] - 1))
    # segtree.show(segtree.root)