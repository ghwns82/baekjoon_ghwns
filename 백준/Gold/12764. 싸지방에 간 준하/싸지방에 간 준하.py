import sys
input = sys.stdin.readline
from heapq import heappop,heappush,nsmallest

n = int(input())
arr = [list(map(int,input().split())) for i in range(n)]
arr.sort()
hq1,hq2=[],list(range(n))
last_time, out_time = arr[0]
heappush(hq1, (out_time, heappop(hq2)))
result = [0]*n
result[0]+=1
max_v = 0
for i in range(1,n):
    a,b =arr[i]
    while hq1 and a>hq1[0][0]:
        _,index =heappop(hq1)
        heappush(hq2,index)
    index = heappop(hq2)
    max_v = max(max_v, index)
    result[index]+=1
    heappush(hq1, (b, index))
    # print(hq1,hq2)
    # print(result)
print(max_v+1)
print(*result[:max_v+1])

# 3
# 1 6
# 2 5
# 3 4

# 3
# 1 1 1

# 3
# 1 11
# 2 10
# 12 13

# ans: 2 1
# result: 1 2

# 5
# 1 5
# 2 3
# 4 5
# 7 8
# 8 9

# # Output
# 2
# 2 3

# 6
# 1 10
# 2 12
# 3 30
# 5 50
# 15 100
# 20 200


# # Answer
# 4
# 2 2 1 1

# 7
# 0 20
# 3 10
# 5 17
# 7 13
# 8 15
# 14 25
# 16 30

# 5
# 1 2 1 2 1