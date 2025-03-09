from collections import defaultdict
import sys

input = lambda: sys.stdin.readline().strip()


class Node:
    def __init__(self, start, end, left=None, right=None):
        self.data = 0
        self.range = (start, end)
        self.left = left
        self.right = right


class seg:
    def __init__(self):
        self.root = self.create(0, n - 1)

    def create(self, start, end):
        if start == end:
            return Node(start, end)
        mid = (start + end) // 2
        left = self.create(start, mid)
        right = self.create(mid + 1, end)
        data = 0  # pass
        return Node(start, end, left, right)

    def add(self, cur, index, show=False):
        start, end = cur.range
        cur.data += 1
        if show:
            # print("range", [start, end])
            # print("cur_data", cur.data)
            pass
        if start == end:
            return
        mid = (start + end) // 2
        if index <= mid:
            self.add(cur.left, index)
        else:
            self.add(cur.right, index)

    def search(self, cur, s, e):
        start, end = cur.range
        if start == s and end == e:
            return cur.data
        mid = (start + end) // 2
        if mid < s:
            return self.search(cur.right, s, e)
        elif mid + 1 > e:
            return self.search(cur.left, s, e)
        else:
            return self.search(cur.left, s, mid) + self.search(cur.right, mid + 1, e)


n = int(input())
arr = list(map(int, input().split()))
queries = [list(map(int, input().split())) + [i] for i in range(int(input()))]
queries.sort(key=lambda x: -x[1])
answer = [0] * len(queries)

nxt_dict = defaultdict(list)
num_dict = {}
for i in range(n - 1, -1, -1):
    # print("cur_value", arr[i])
    if arr[i] not in num_dict:
        nxt_dict[n].append(i)
    else:
        nxt_dict[num_dict[arr[i]]].append(i)
    num_dict[arr[i]] = i

tree = seg()
cur_right = n + 1
for l, r, index in queries:
    # print(l, r, index)
    while r < cur_right:
        cur_right -= 1
        for dot in nxt_dict[cur_right]:
            # print('add',f'arr[{dot}] : {arr[dot]}')
            tree.add(tree.root, dot, True)

    answer[index] = tree.search(tree.root, l - 1, r - 1)

print(*answer, sep="\n")
