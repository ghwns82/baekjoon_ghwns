import sys
input = lambda : sys.stdin.readline().strip()
from heapq import heappop, heappush

h = []

for i in range(int(input())):
    num = int(input())
    if num !=0:
        heappush(h, (abs(num),num))
    else:
        if h:
            print(heappop(h)[1])
        else:
            print(0
                 )