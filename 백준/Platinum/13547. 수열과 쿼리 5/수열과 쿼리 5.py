import sys
from collections import Counter

input = lambda: sys.stdin.readline().strip()
n = int(input())
arr = list(map(int, input().split()))
m = int(input())
sq = n**0.5
queries = sorted(
    [list(map(int, input().split())) + [i, 0] for i in range(m)],
    key=lambda x: (x[0] // sq, x[1]),
)
ct = Counter()
left, right = 0, 0
ct[arr[0]] += 1
for query in queries:
    # print(query, left, right)
    while right >= query[1]:
        ct[arr[right]] -= 1
        if ct[arr[right]] == 0:
            ct.pop(arr[right])
        right -= 1
    while right < query[1] - 1:
        right += 1
        ct[arr[right]] += 1

    while left >= query[0]:
        left -= 1
        ct[arr[left]] += 1

    while left < query[0] - 1:
        ct[arr[left]] -= 1
        if ct[arr[left]] == 0:
            ct.pop(arr[left])
        left += 1
    query[3] = len(ct.keys())
    # print(query)

for q in sorted(queries, key=lambda x: x[2]):
    print(q[3])