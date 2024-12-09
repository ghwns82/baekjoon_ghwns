# import sys
# input = lambda : sys.stdin.readline().strip()
from collections import Counter


def recursion(n):
    for key in sorted(wdict.keys()):
        if wdict[key]:
            wdict[key]-=1
            aword[-n]=key
            if n>1:
                recursion(n-1)
            else:
                print(*aword,sep='')
            wdict[key]+=1

for _ in range(int(input())):
    word = input()
    n = len(word)
    aword=['']*n
    wdict=Counter(list(word))
    recursion(n)