import sys
input = sys.stdin.readline
n = int(input())
m = int(input())
a = list(set(map(int,input().split())))
from collections import deque
size = 0
num = 1
while n>num:
    num<<=1
    size+=1
visited=[-1]*(n+1)
q = deque()
for i in a:
    if visited[i]!=0:
        q.append([i,0])
    visited[i]=0

while q:
    num, safe=q.popleft()
    for i in range(size):
        d = 1<<i
        next = num^d
        if next<=n and visited[next]==-1:
            visited[next]=safe+1
            q.append([next,safe+1])
print(safe)