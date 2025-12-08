import sys
input = sys.stdin.readline

n, m = map(int, input().split())
parents = [None, 1]
for _ in range(n - 1):
    p = int(input())
    parents.append(p)

# 각 노드의 초기 색 하나만 가지고 시작
colors = [set() for _ in range(n + 1)]
for i in range(1, n + 1):
    c = int(input())
    colors[i].add(c)

queries = [list(map(int, input().split())) for _ in range(m + n - 1)][::-1]

금지 = set()
for a, b in queries:
    if a == 1:
        금지.add(b)

parent = list(range(n + 1))

def find(x):
    # 경로 압축만 수행, colors는 건드리지 않음
    while parent[x] != x:
        parent[x] = parent[parent[x]]
        x = parent[x]
    return x

def union(a, b):
    ra, rb = find(a), find(b)
    if ra == rb:
        return

    # small-to-large: 더 큰 set 쪽으로 합친다
    if len(colors[ra]) < len(colors[rb]):
        ra, rb = rb, ra

    parent[rb] = ra
    # 큰 쪽에 작은 쪽을 merge (set 복사는 안 하고 update만)
    colors[ra].update(colors[rb])
    # rb 쪽은 비워서 메모리 회수
    colors[rb].clear()

# 처음에는 끊어진(금지된) 간선을 제외하고 전부 union
for c in range(2, n + 1):
    if c in 금지:
        continue
    p = parents[c]
    union(p, c)

result = []
for a, b in queries:
    if a == 1:
        p = parents[b]
        union(p, b)
    else:
        root = find(b)
        result.append(len(colors[root]))

print(*result[::-1], sep='\n')
