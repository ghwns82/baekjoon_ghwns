import sys
input = sys.stdin.readline

n = int(input())
a = []
for i in range(n):
    a+=list(map(int,input().split()))
    a.sort(reverse=True)
    a = a[:n]
print(a[-1])