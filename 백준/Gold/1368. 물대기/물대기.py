import heapq

n = int(input())

well = sorted([(int(input()),i, i) for i in range(n)], reverse=True)
graph = [list(map(int,input().split())) for i in range(n)]

visited = [0]*n


work = []

cnt=0
result = 0
while cnt<n:
    if not work or well and well[-1][0] <= work[0][0]:
        value, start, node = well.pop()
    else:
        value, start ,node = heapq.heappop(work)
    if visited[node]:
        continue
    visited[node]=1
    result+=value
    cnt+=1
    # print(node,result)
    for i in range(n):
        if i!=node and not visited[i]:
            # print(i,node)
            heapq.heappush(work,[graph[node][i], node, i])

print(result)

# 2
# 133
# 465
# 0 80
# 80 0

# ->  213

# 4
# 379
# 374
# 327
# 74
# 0 335 281 61
# 335 0 302 330
# 281 302 0 131
# 61 330 131 0

# -> 568