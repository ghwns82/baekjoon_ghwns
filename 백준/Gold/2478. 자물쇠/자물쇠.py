import sys
from collections import deque

input = lambda: sys.stdin.readline().strip()

n = int(input())
arr = list(map(int, input().split()))

tmp = set()
for i in range(-n, 0):
    if arr[i] % n == (arr[i + 1] + 1) % n:
        
        tmp.add(n + i)
        tmp.add((n + i + 1) % n)
tmp = sorted(list(tmp))
# print("tmp", tmp)
# input()
if len(tmp) >= n:
    if arr[0] == n:
        answer = f"2\n1 {n-1}\n1"
        # print("answer1")
    else:
        answer = f"1\n1 {n-1}\n{n-arr[0]}"
        # print("answer2")
else:
    for k2 in range(1,n):
    
    
        for i in range(-len(tmp), 0):
            if tmp[i] % n != (tmp[i + 1] - 1) % n:
                end = tmp[i]
                start = tmp[i + 1]
    move = (arr[end] - 1 - start) % n
    # print("start", start, "end", end)
    # print("move", move)
    # input()

    for k2 in range(n):
        k1 = (move - k2) % n
        # print(k2)
        if start < end and k1 > 0 and k2 > 0:
            answer = f"{k1}\n{start+1} {end+1}\n{k2}"
            break
        start += 1
        start %= n
        end += 1
        end %= n


print(answer)