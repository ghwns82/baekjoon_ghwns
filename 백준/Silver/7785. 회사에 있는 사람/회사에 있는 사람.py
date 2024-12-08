import sys
input = lambda : sys.stdin.readline().strip()

log = set()
n = int(input())
for i in range(n):
    who,how = input().split()
    if how =='enter':
        log.add(who)
    else:
        log.remove(who)

for i in sorted(log,reverse=True):
    print(i)