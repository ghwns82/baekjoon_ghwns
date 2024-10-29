import sys
import math
from collections import Counter

input = lambda: sys.stdin.readline().rstrip()

n, k = map(int, input().split())
arr = list(map(int, input().split()))

c = Counter()
s, e = 0, 0
result = 0
while s < n:
    c[arr[s]] += 1
    # print("start up", s, c)
    while c[arr[s]] > k:
        # print("end up", e, c)
        c[arr[e]] -= 1
        e += 1

    s += 1
    result = max(result, s - e)
    # print("result", result)
print(result)