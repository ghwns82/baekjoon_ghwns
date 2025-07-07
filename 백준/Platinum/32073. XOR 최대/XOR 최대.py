import sys
input = sys.stdin.readline
from collections import deque
write = sys.stdout.write

t = int(input())
for _ in range(t):
    n = int(input())
    s = input().strip()

    index = 0
    while index < n and s[index] == '0':
        index += 1

    start = index
    if start == n:
        write("0\n")
        continue

    while index < n and s[index] == '1':
        index += 1

    need = []
    for i in range(index, n):
        if s[i] == '0':
            need.append(1)
        else:
            need.append(0)

    if not need:
        write(s[start:n - 1])
        if s[0] == '1':
            write('0\n')
        else:
            write('1\n')
        continue

    dq = deque()
    for i in range(start, start + len(need)):
        dq.append(int(s[i]))

    result = [need[i] ^ dq[i] for i in range(len(need))]

    index = start + len(need)
    while index < n:
        dq.popleft()
        dq.append(int(s[index]))
        tmp = []
        flag = True
        for i in range(len(need)):
            tmp_val = need[i] ^ dq[i]
            tmp.append(tmp_val)
            if tmp_val < result[i]:
                flag = False
            if flag and tmp_val > result[i]:
                break
        else:
            result = tmp
        index += 1

    write(s[start:n - len(result)])
    write(''.join('10'[i] for i in result))
    write('\n')
