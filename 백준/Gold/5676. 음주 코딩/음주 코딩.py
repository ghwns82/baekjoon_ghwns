import sys

def build(node, start, end):
    """ 세그먼트 트리 초기화 """
    if start == end:
        tree[node] = arr[start]
    else:
        mid = (start + end) // 2
        build(2 * node, start, mid)
        build(2 * node + 1, mid + 1, end)
        tree[node] = tree[2 * node] * tree[2 * node + 1]

def update(node, start, end, idx, value):
    """ 값 변경 (C i V) """
    if start == end:
        arr[idx] = value
        tree[node] = value
    else:
        mid = (start + end) // 2
        if idx <= mid:
            update(2 * node, start, mid, idx, value)
        else:
            update(2 * node + 1, mid + 1, end, idx, value)
        tree[node] = tree[2 * node] * tree[2 * node + 1]

def query(node, start, end, l, r):
    """ 곱셈 범위 쿼리 (P i j) """
    if r < start or l > end:
        return 1  # 곱셈에 영향 없는 항등원
    if l <= start and end <= r:
        return tree[node]
    mid = (start + end) // 2
    left_result = query(2 * node, start, mid, l, r)
    right_result = query(2 * node + 1, mid + 1, end, l, r)
    return left_result * right_result

while True:
    try:
        n, k = map(int, sys.stdin.readline().split())
        arr = list(map(int, sys.stdin.readline().split()))
        
        # 1, -1, 0 형태로 변환하여 저장
        arr = [1 if x > 0 else -1 if x < 0 else 0 for x in arr]
        
        tree = [0] * (4 * n)
        build(1, 0, n - 1)

        result = []
        for _ in range(k):
            command = sys.stdin.readline().split()
            if command[0] == "C":
                i, v = int(command[1]) - 1, int(command[2])
                v = 1 if v > 0 else -1 if v < 0 else 0
                update(1, 0, n - 1, i, v)
            else:  # "P i j"
                i, j = int(command[1]) - 1, int(command[2]) - 1
                res = query(1, 0, n - 1, i, j)
                result.append("+" if res > 0 else "-" if res < 0 else "0")

        sys.stdout.write("".join(result) + "\n")

    except:
        break