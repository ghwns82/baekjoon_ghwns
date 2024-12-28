import sys
input = lambda : sys.stdin.readline().strip()

for i in range(int(input())):
    a,b = map(int,input().split())
    c = 1
    for i in range(b):
        c *=a
        c %=10
    if not c:
        c+=10
    print(c)