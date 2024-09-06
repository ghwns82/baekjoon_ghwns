import sys
from itertools import combinations_with_replacement
from collections import Counter
n,m = map(int,input().split())


o1 = (1<<n)-1
o2 = 2
for i in range(n):
    tmp = (o2<<2)+2
    if tmp>o1:
        break
    o2 = tmp
if n ==1:
    o2 = 0
o3 = 1
for i in range(n):
    tmp = (o3<<2)+1
    if tmp>o1:
        break
    o3 = tmp
o4 = 1
for i in range(n):
    tmp = (o4<<3)+1
    if tmp>o1:
        break
    o4 = tmp

result = set()
visited = {(0,0,0,0):0}
num_list = [o1,o2,o3,o4]
time_list = [n,n//2,(n+1)//2,(n+2)//3]


for i in range(4):
    num,time = num_list[i], time_list[i]
    for k,v in list(visited.items()):
        if v+time<=m:
            tmp = list(k)
            tmp[i]+=1
            tmp = tuple(tmp)
            if tmp not in visited:
                visited[tmp] = v+time
for k in visited.keys():
    num = 0
    for i in range(4):
        if k[i]:
            num^=num_list[i]
    result.add(num)
# print(*visited.items(),sep='\n')
# print(len(visited))
# print(result)
print(len(result))