import sys

input = lambda: sys.stdin.readline().rstrip()
from collections import defaultdict

n, q = map(int, input().split())
arr = list(map(int, input().split()))


class node:
    def __init__(self):
        self.range = None
        self.left = None
        self.right = None
        self.data = None

    def __str__(self):
        return f"{self.range}, {self.data}"


class segment:
    def __init__(self):
        self.tree = None

    def create(self, l, r):
        nd = node()
        nd.range = (l, r)
        if l < r:
            m = (l + r) // 2
            nd.left = self.create(l, m)
            nd.right = self.create(m + 1, r)
            nd.data = nd.left.data + nd.right.data
        else:
            nd.data = arr[l]
        return nd

    def search(self, cnd, l, r):
        left, right = cnd.range
        if l == left and r == right:
            return cnd.data

        mid = (left + right) // 2
        if r <= mid:
            return self.search(cnd.left, l, r)
        elif l > mid:
            return self.search(cnd.right, l, r)
        else:
            return self.search(cnd.left, l, mid) + self.search(cnd.right, mid + 1, r)

    def modify(self, cnd, index, value):
        left, right = cnd.range
        if left == right:
            diff = value - cnd.data
            cnd.data = value
            return diff
        mid = (left + right) // 2
        if index <= mid:
            diff = self.modify(cnd.left, index, value)
        else:
            diff = self.modify(cnd.right, index, value)
        cnd.data += diff
        return diff


seg = segment()
seg.tree = seg.create(0, len(arr) - 1)

for _ in range(q):
    a, b, c, d = map(int, input().split())
    a,b = min(a,b),max(a,b)
    print(seg.search(seg.tree, a - 1, b - 1))
    seg.modify(seg.tree, c - 1, d)