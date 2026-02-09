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

    need = [1 if s[i] == '0' else 0 for i in range(index, n)]

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

    result = ''.join('10'[need[i] ^ dq[i]] for i in range(len(need)))

    idx = start + len(need)
    while idx < n:
        dq.popleft()
        dq.append(int(s[idx]))
        now = ''.join('10'[need[i] ^ dq[i]] for i in range(len(need)))
        if now > result:
            result = now
        idx += 1

    write(s[start:n - len(result)])
    write(result)
    write('\n')
