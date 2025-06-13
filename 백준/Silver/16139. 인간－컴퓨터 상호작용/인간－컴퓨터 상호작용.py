import sys
input = sys.stdin.readline

s = input()
nsum={}
for i in set(s):
    nsum[i]=[0]

for i in range(len(s)):
    for key in nsum:
        nsum[key].append(nsum[key][-1])
    nsum[s[i]][-1]+=1

for i in range(int(input())):
    a, *d = input().split()
    b,c = map(int,d)
    try:
        print(nsum[a][c+1] - nsum[a][b]    )
    except:
        print(0)