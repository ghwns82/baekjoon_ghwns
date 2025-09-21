import sys
sys.setrecursionlimit(2*10**5)
from collections import defaultdict
import sys
input = sys.stdin.readline


n = int(input())
graph = defaultdict(set)
for i in range(n-1):
    a,b = map(int,input().split())
    graph[a-1].add(b-1)
    graph[b-1].add(a-1)
cars = list(map(int,input().split()))

def dfs(point):
    order = [cars[point]]
    while len(graph[point])==1:
        next = graph[point].pop()
        order.append(cars[next])
        graph[next].remove(point)
        point = next
    if not graph[point]:
        return order
    index = len(order)
    for next in list(graph[point]):
        graph[next].remove(point)
        subresult =dfs(next)
        for i in range(len(subresult)):
            if len(order)>index+i:
                order[index+i]+=subresult[i]
            else:
                order.append(subresult[i])

    return order
result=dfs(0)
answer = 0
last = 0
for i in range(len(result)):
    if result[i] > 0:
        answer = max(answer, i)
        answer += result[i]
print(answer)
