import sys

input = lambda: sys.stdin.readline().rstrip()

from collections import deque


n, t = map(int, input().split())
arr = {tuple(map(int, input().split())) for i in range(n)}

work = deque([(0, 0, 0)])
while work:
    a, b, c = work.popleft()
    tmp = set()
    for x in range(a - 2, a + 3):
        for y in range(b - 2, b + 3):
            if (x, y) not in arr:
                continue
            if (x, y) not in tmp and abs(x - a) <= 2 and abs(y - b) <= 2:
                if y == t:
                    print(c + 1)
                    sys.exit()
            work.append((x, y, c + 1))
            tmp.add((x, y))
    arr -= tmp
print(-1)