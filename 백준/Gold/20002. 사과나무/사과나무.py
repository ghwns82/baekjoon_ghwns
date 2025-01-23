import sys
import math

input = lambda: sys.stdin.readline().strip()


n = int(input())
land = []
for i in range(n):
    land.append(list(map(int,input().split())))

ns = [[0]*(n+1) for i in range(n+1)]

for i in range(1,n+1):
    for j in range(1,n+1):
        ns[i][j] = ns[i][j-1]+ns[i-1][j]-ns[i-1][j-1]+land[i-1][j-1]

max_value = land[0][0]

for k in range(1,n+1):
    for i in range(n+1-k):
        for j in range(n+1-k):
            v = ns[i+k][j+k] + ns[i][j]-ns[i+k][j]-ns[i][j+k]
            max_value = max(max_value,v)

print(max_value)