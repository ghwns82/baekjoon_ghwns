from collections import deque
import sys
input = lambda : sys.stdin.readline().rstrip()

def check(n):
    work = deque([[1,1]])
    end = 1<<100
    visited = [False]*20000
    while True:
        num, div = work.popleft()
        tmp = (div * 10)%n
        if num<<1 >=end:
            return 'BRAK'
        if tmp==0:
            return num<<1
        elif (tmp+1)%n == 0:
            return (num<<1)+1
        if not visited[tmp]:
            visited[tmp]=True
            work.append([num<<1,tmp])
        if not visited[(tmp+1)%n]:
            visited[(tmp+1)%n]=True
            work.append([(num<<1)+1 ,(tmp+1)%n ])
        # print(work)
        # input()/


t = int(input())
for _ in range(t):
    n = int(input())
    if n ==1:
        print(n)
        continue
    result = check(n)
    print(bin(result)[2:])