import sys
input = sys.stdin.readline

n,m,h = map(int,input().split())
graph = [[-1]*(n) for i in range(h)]


for i in range(m):
    a,b = map(int,input().split())
    graph[a-1][b-1] = b
    graph[a-1][b] = b-1
    
def check():
    global result
    for start in range(n):
        # print('start',start)
        cur = start
        for i in range(h):
            if graph[i][cur]>-1:
                cur = graph[i][cur]
            # print('cur',cur)
        if cur!=start:
            return False
    print(result)
    sys.exit()

def dfs(depth, index):
    if depth == 0:
        check()
        return

    global n, result,h
    total=(n-1)*h
    for i in range(index, total):
        x,y = i//(n-1), i % (n-1)
        if depth and graph[x][y] == graph[x][y+1]==-1:
            graph[x][y] = y+1
            graph[x][y+1] = y
            dfs(depth-1, index+1)
            graph[x][y] = graph[x][y+1]= -1

for result in range(4):
    dfs(result,0)
print(-1)