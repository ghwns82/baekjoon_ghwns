import sys
input = lambda : sys.stdin.readline().strip()

n = int(input())
tasks =[list(map(int,input().split())) for i in range(n)]
tasks.sort(key=lambda x: -x[1])

now = 1_000_000
for t,s in tasks:
    now = min(now,s) - t

if now<0:
    print(-1)
else:
    print(now)