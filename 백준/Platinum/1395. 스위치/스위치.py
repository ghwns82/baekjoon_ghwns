import sys
input = lambda: sys.stdin.readline().strip()
n, m = map(int, input().split())

class Node:
    def __init__(self, start, end, left=None, right=None):
        self.range = (start, end)
        self.data = 0  # 해당 구간에서 켜진 전구의 개수
        self.lazy = 0  # lazy propagation을 위한 값
        self.left_child = left
        self.right_child = right

class Seg:
    def __init__(self):
        self.root = self.create(0, n - 1)

    def create(self, start, end):
        if start == end:
            return Node(start, end)
        mid = (start + end) // 2
        return Node(start, end,
            left=self.create(start, mid),
            right=self.create(mid + 1, end)
        )

    def propagate(self, cur: Node):
        """ Lazy propagation을 수행하여 현재 노드의 lazy 값을 반영하고 자식에게 전달 """
        start, end = cur.range
        if cur.lazy:
            cur.data = (end - start + 1) - cur.data  # 토글 연산
            if cur.left_child:
                cur.left_child.lazy ^= 1  # XOR 연산으로 토글 (0->1, 1->0)
                cur.right_child.lazy ^= 1
            cur.lazy = 0  # 현재 노드의 lazy 값을 초기화

    def modify(self, cur: Node, s, e):
        """ 범위 [s, e]의 전구 상태를 토글 (0->1, 1->0) """
        start, end = cur.range
        self.propagate(cur)  # Lazy 값을 현재 노드에 적용 후 진행

        if s > end or e < start:  # 범위를 벗어난 경우
            return
        if s <= start and end <= e:  # 범위 전체가 포함되는 경우
            cur.lazy ^= 1  # Lazy 값 반영
            self.propagate(cur)  # 현재 노드에 즉시 반영
            return

        # 구간을 나누어 좌우 자식에 대해 처리
        self.modify(cur.left_child, s, e)
        self.modify(cur.right_child, s, e)

        # 자식 노드들의 데이터를 합산하여 갱신
        cur.data = cur.left_child.data + cur.right_child.data

    def search(self, cur: Node, s, e):
        """ 범위 [s, e]의 전구 개수를 반환 """
        start, end = cur.range
        self.propagate(cur)  # Lazy 값을 현재 노드에 적용

        if s > end or e < start:  # 범위를 벗어난 경우
            return 0
        if s <= start and end <= e:  # 범위 전체가 포함되는 경우
            return cur.data

        # 부분적으로 겹치는 경우 좌우 자식 탐색 후 합산
        return self.search(cur.left_child, s, e) + self.search(cur.right_child, s, e)

segtree = Seg()

for _ in range(m):
    o, s, t = map(int, input().split())
    if o == 0:
        segtree.modify(segtree.root, s - 1, t - 1)
    else:
        print(segtree.search(segtree.root, s - 1, t - 1))