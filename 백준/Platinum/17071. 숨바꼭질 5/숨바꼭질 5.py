import sys
from collections import deque

input = lambda: sys.stdin.readline().strip()

subin, little = map(int, input().split())

if subin == little:
    print(0)
    sys.exit()

little_loc = [-1] * 500_001
iter = 0
while True:
    little += iter
    if little > 500_000:
        max_move = iter
        break
    little_loc[little] = iter
    iter += 1


dp = [[-1, -1] for i in range(500_001)]

work = deque([[subin, 0]])

result = float("inf")
while work:
    loc, time = work.popleft()
    # print(loc, time, dp[:20])
    if time >= max_move:
        continue
    time += 1
    for x in [loc * 2, loc + 1, loc - 1]:
        if 0 <= x <= 500_000:
            diff = little_loc[x] - time
            if diff == 0:
                print(min(time, result))
                sys.exit()
            elif diff > 0 and diff % 2 == 0:
                result = min(result, time + diff)

            else:
                if dp[x][time % 2] == -1:
                    dp[x][time % 2] = time
                    work.append([x, time])
    # print("work", work)


if result == float("inf"):
    print(-1)
else:
    print(result)