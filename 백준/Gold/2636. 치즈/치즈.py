import sys
input = lambda: sys.stdin.readline().strip()
from collections import deque

h,w = map(int,input().split())

space = [list(map(int,input().split())) for i in range(h)]

cheeze_cnt = 0
for i in range(h):
    for j in range(w):
        cheeze_cnt+=space[i][j]
        
direction = [[0,1],[1,0],[0,-1],[-1,0]]        
def bfs(visit_flag):
    cheeze=set()
    work = deque([[0,0]])
    while work:
        x,y = work.popleft()
        for dx,dy in direction:
            nx,ny = x+dx, y+dy
            if 0<=nx<h and 0<=ny<w and space[nx][ny]!=visit_flag and (nx,ny) not in cheeze:
                if space[nx][ny]==1:
                    cheeze.add((nx,ny))
                else:
                    work.append([nx,ny])
                    space[nx][ny] = visit_flag
    
    for x,y in cheeze:
        space[x][y] = visit_flag
    return len(cheeze)
        
        
visit_flag = 0        
while True:
    visit_flag-=1
    cnt = bfs(visit_flag)
    if cheeze_cnt == cnt:
        break
    cheeze_cnt -= cnt
print(-visit_flag)
print(cheeze_cnt)