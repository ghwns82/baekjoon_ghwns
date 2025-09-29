import sys
input = sys.stdin.readline
n,m = map(int,input().split())
want=[list(map(int,input().split()))[1:] for i in range(n)]

def bimatch(node):
    if visited[node]:
        return False
    visited[node] = True

    for next in want[node]:

        if selected[next] == -1 or bimatch(selected[next]):
            selected[next] = node
            return True
    return False

selected = [-1]*(m+1)
for i in range(n):
    visited = [0]*(n+1)
    bimatch(i)
print(sum(i>=0 for i in selected))
