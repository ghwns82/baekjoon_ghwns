from collections import defaultdict
import sys
input=sys.stdin.readline
cur = 0
start = defaultdict(int)
end = defaultdict(int)
max = 0
max_start, max_end=0,[0,0]
for i in range(int(input())):
    s,e=map(int,input().split())
    start[s]+=1
    end[e]+=1
# dp=[0]*2_100_000_001
for i in sorted(set(start.keys())|set(end.keys())):
    cur= cur + start[i]-end[i]
    # print(i, max, max_start,max_end,cur)
    if cur>max:
        max = cur
        max_start=i
        max_end[0]=cur
    elif max>cur and max_end[0]==max:
        max_end=[cur,i]
print(max)
print(max_start,max_end[1])