import sys
from collections import deque
input = lambda: sys.stdin.readline().strip()


r,c =map(int, input().split())
miro = [list(input()) for i in range(r)]
direction = [[0,1],[0,-1],[1,0],[-1,0]]


queue=deque()
for i in range(r):
    for j in range(c):
        if miro[i][j]=='J':
            jihun = (i,j,0)
            miro[i][j]=='.'
        elif miro[i][j]=='F':
            queue.append([i,j,-1])
queue.append(jihun)
x,y,t = jihun
if x==0 or x==r-1 or y==0 or y==c-1:
    print(t+1)
    sys.exit()

dp = [[-1]*c for i in range(r)]
dp[x][y]=t
while queue:
    x,y, whatisthis = queue.popleft()
    for dx, dy in direction:
        nx, ny = dx+x,dy+y
        if not (0<=nx<r and 0<=ny<c):
            continue
        
        if miro[nx][ny]=='.':
            if whatisthis==-1: # fire
                queue.append([nx,ny,-1])
                miro[nx][ny]='F'
            else:
                time = whatisthis+1

                if nx==0 or nx==r-1 or ny==0 or ny==c-1:
                    print(time+1)
                    sys.exit()
                elif dp[nx][ny]==-1:
                    dp[nx][ny]=time
                    queue.append([nx,ny,time])
else:
    print('IMPOSSIBLE')