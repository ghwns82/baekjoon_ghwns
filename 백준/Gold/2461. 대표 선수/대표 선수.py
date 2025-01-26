from heapq import heappop, heappush
import sys
input = lambda: sys.stdin.readline().strip()

n,m = map(int,input().split())

school = []
for i in range(n):
    school.append(sorted(map(int,input().split())))
    
indexes = [0]*n

q = []
max_value = school[0][0]
for i in range(n):
    max_value = max(max_value, school[i][0])
    heappush(q, [school[i][0],i])
    
result = float('inf')
while q:
    v,th = heappop(q)
    result=min(result,max_value-v)
    indexes[th]+=1
    index = indexes[th]
    if index>=m:
        break
    # print(th,index)
    max_value = max(max_value, school[th][index])
    heappush(q, [school[th][index],th])
    
print(result)