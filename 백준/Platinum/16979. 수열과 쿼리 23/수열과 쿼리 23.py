import sys
input = lambda: sys.stdin.readline().rstrip()

n, m = map(int, input().split())
arr = list(map(int, input().split()))

# 좌표 압축 (0부터 시작)
vals = sorted(set(arr))
compress = {v: i for i, v in enumerate(vals)}
arr = [compress[x] for x in arr]
size = len(compress)

# 바텀업 세그먼트 트리 초기화
tree_size = 1
while tree_size < size:
    tree_size <<= 1
tree = [0] * (2 * tree_size)

def update(idx, val):
    idx += tree_size
    tree[idx] += val
    while idx > 1:
        idx >>= 1
        tree[idx] = tree[idx << 1] + tree[idx << 1 | 1]

def query(l, r):
    l += tree_size
    r += tree_size
    res = 0
    while l <= r:
        if l & 1:
            res += tree[l]
            l += 1
        if not r & 1:
            res += tree[r]
            r -= 1
        l >>= 1
        r >>= 1
    return res

# Mo 정렬
sq = int(n ** 0.5)
queries = [list(map(int, input().split())) + [i] for i in range(m)]
queries.sort(key=lambda x: (x[0] // sq, x[1] if (x[0] // sq) % 2 == 0 else -x[1]))

# Mo's 알고리즘
answer = [0] * m
res = 0
l, r = 0, -1

for s, e, idx in queries:
    s -= 1
    e -= 1
    while r < e:
        r += 1
        x = arr[r]
        res += query(x + 1, size - 1)
        update(x, 1)
    while r > e:
        x = arr[r]
        update(x, -1)
        res -= query(x + 1, size - 1)
        r -= 1
    while l < s:
        x = arr[l]
        update(x, -1)
        res -= query(0, x - 1)
        l += 1
    while l > s:
        l -= 1
        x = arr[l]
        res += query(0, x - 1)
        update(x, 1)
    answer[idx] = res

print('\n'.join(map(str, answer)))
