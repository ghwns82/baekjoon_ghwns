import sys

input = lambda: sys.stdin.readline().strip()

n = int(input())
arr = list(map(int, input().split()))


class Node:
    def __init__(self, start, end, left=None, right=None):
        self.range = (start, end)
        if left:
            self.data = left.data ^ right.data
        else:
            self.data = arr[start]
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

    def propagate(self, cur: Node, search=False):
        start, end = cur.range
        if cur.lazy:
            if (end - start + 1) % 2:  # 구간 길이가 홀수일 때만 XOR 적용
                cur.data ^= cur.lazy
            if cur.left_child:  # 리프 노드가 아닐 경우 lazy 값 전파
                cur.left_child.lazy ^= cur.lazy
                cur.right_child.lazy ^= cur.lazy
            cur.lazy = 0  # 현재 노드의 lazy 값을 초기

    def modify(self, cur: Node, s, e, k):
        start, end = cur.range
        self.propagate(cur)
        
        if s > end or e < start:  # 범위를 벗어난 경우
            return
        if s <= start and end <= e:  # 범위 전체가 포함되는 경우
            cur.lazy ^= k  # Lazy 값 반영
            self.propagate(cur)  # 현재 노드에 즉시 반영
            return

        # 구간을 나누어 좌우 자식에 대해 처리
        self.modify(cur.left_child, s, e,k)
        self.modify(cur.right_child, s, e,k)

        # 자식 노드들의 데이터를 합산하여 갱신
        cur.data = cur.left_child.data ^ cur.right_child.data

    def search(self, cur: Node, s, e):
        start, end = cur.range
        self.propagate(cur, search=True)  # Lazy 전파

        istart, end = cur.range
        self.propagate(cur)  # Lazy 값을 현재 노드에 적용

        if s > end or e < start:  # 범위를 벗어난 경우
            return 0
        if s <= start and end <= e:  # 범위 전체가 포함되는 경우
            return cur.data

        # 부분적으로 겹치는 경우 좌우 자식 탐색 후 합산
        return self.search(cur.left_child, s, e) ^ self.search(cur.right_child, s, e)

    def show(self, cur: Node):
        print(cur.range, 'data, lazy:', [cur.data, cur.lazy])
        if cur.left_child:
            self.show(cur.left_child)
            self.show(cur.right_child)


segtree = Seg()
for _ in range(int(input())):
    ctype, *content = list(map(int, input().split()))
    if ctype == 1:
        i, j, k = content
        segtree.modify(segtree.root, min(i, j),max(i,j), k)
    else:
        i, j = content
        print(segtree.search(segtree.root, min(i, j),max(i,j)))
    # print(segtree.show(segtree.root))