from collections import deque
import sys
input = lambda: sys.stdin.readline().strip()
n,s = map(int,input().split())
loc = list(map(int,input().split()))
fuel = list(map(int,input().split()))
result = {s}
s-=1
left, right = s-1, s+1
q = deque([[s,fuel[s]]])

while q:
    now, power = q.popleft()
    if left>=0 and loc[now] - loc[left] <=power:
        result.add(left+1)
        q.append([left,max(power - loc[now] + loc[left], fuel[left])])
        left-=1
    
    if right<n and loc[right] - loc[now] <=power:
        result.add(right+1)
        q.append([right,max(power - loc[right] + loc[now], fuel[right])])
        right+=1
print(*sorted(result))