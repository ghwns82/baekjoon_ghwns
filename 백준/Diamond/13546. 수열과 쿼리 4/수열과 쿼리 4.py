from collections import defaultdict, deque
from heapq import heappop, heappush
import sys

input = lambda: sys.stdin.readline().rstrip()
n,k = map(int,input().split())
ns = [0]
for i in map(int, input().split()):
    ns.append(i)
sq = n**0.5
m = int(input())
queries = sorted(
    [list(map(int, input().split())) + [i] for i in range(m)],
    key=lambda x: (x[0] // sq, x[1]),
)
answer = [0] * m
dd = defaultdict(deque)
left, right = 1, 1
dd[0].append(0)
dd[ns[1]].append(1)
maxtable = [0] * (n + 1)

unit = 300
cnt_table = [0] * (n // unit + 1)
maxtable[0] = 1
cnt_table[0] = 1


def modify(index, change, right=False):  # change는 인덱스 변화
    value = ns[index]
    if [-1, 1][right] * change == -1:
        if len(dd[value]) > 1:
            diff = dd[value][-1] - dd[value][0]
            maxtable[diff] -= 1
            cnt_table[diff // unit] -= 1
        method = [deque.popleft, deque.pop][right]
        method(dd[value])
        if len(dd[value]) > 1:
            diff = dd[value][-1] - dd[value][0]
            maxtable[diff] += 1
            cnt_table[diff // unit] += 1

    index += change
    value = ns[index]
    if [-1, 1][right] * change == 1:
        if len(dd[value]) > 1:
            diff = dd[value][-1] - dd[value][0]
            maxtable[diff] -= 1
            cnt_table[diff // unit] -= 1
        method = [deque.appendleft, deque.append][right]
        method(dd[value], index)
        if len(dd[value]) > 1:
            diff = dd[value][-1] - dd[value][0]
            maxtable[diff] += 1
            cnt_table[diff // unit] += 1

    return index


for q in queries:
    # print('query',q)
    while left > q[0]:
        left = modify(left, -1)

    while right < q[1]:
        right = modify(right, 1, True)

    while left < q[0]:
        left = modify(left, 1)

    while right > q[1]:
        right = modify(right, -1, True)
    # print('cnt_table',cnt_table)
    # print('maxtable',maxtable)
    for i in range(len(cnt_table)-1,-1,-1):
        if cnt_table[i]>0:
            break
    for j in range(min(len(maxtable), unit*(i+1))-1,unit*i-1,-1):
        if maxtable[j]>0:
            answer[q[2]] = j
            break
    

for i in answer:
    print(i)
    pass