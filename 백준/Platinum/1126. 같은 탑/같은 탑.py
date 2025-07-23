from collections import defaultdict, deque
import sys
input = lambda : sys.stdin.readline().rstrip()

n = int(input())
arr = list(map(int,input().split()))

last=defaultdict(int)
last[0]=0
for i in arr:
    now = defaultdict(int) # diff
    for diff,h in list(last.items()):
        a,b = h, h-diff
        now[diff] = max(now[diff],h)
        now[diff+i] = max(h+i, now[diff+i])
        now[abs(diff-i)] = max(max(h-diff+i,h), now[abs(diff-i)])
    last = now

print([-1,now[0]][now[0]!=0])