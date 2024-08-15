import sys
from collections import deque
from time import sleep
n, m = map(int, input().split())
board = [list(input()) for i in range(n)]

for i in range(n):
    for j in range(m):
        if board[i][j] == "B":
            bx, by = i, j
            board[i][j] = '.'
        elif board[i][j] == "R":
            rx, ry = i, j
            board[i][j] = '.'

blue=[bx,by,'blue']
red=[rx,ry,'red']

def move(blue,red,drc):
    if drc == 'up':
        dx,dy = -1,0
        balls = [red, blue] if blue[0] > red[0] else [blue, red]
    elif drc =='down':
        dx,dy = 1,0
        balls = [red, blue] if blue[0] < red[0] else [blue, red]
    elif drc =='left':
        dx,dy = 0,-1
        balls = [red, blue] if blue[1] > red[1] else [blue, red]
    else:
        dx,dy = 0,1
        balls = [red, blue] if blue[1] < red[1] else [blue, red]
    
    done = (-1,-1)
    result = []
    for x,y,c in balls:
        while True:
            x+=dx
            y+=dy
            if board[x][y]=='O':
                # print(drc,c,'hall')
                result.append([-1,-1,c])
                break
            elif board[x][y] =='#' or (x,y)==done:
                done = (x-dx,y-dy)
                result.append([x-dx,y-dy,c])
                # print(drc,c,'wall')
                break
    # print(result)
    return result



work=deque([[blue,red,1]])
drc_list = ['up','down','left','right']
result_table = 0
last_time=1
while work:
    blue,red,time = work.popleft()
        
    for drc in drc_list:
        # print(drc)
    # drc = input()
        balls= move(blue,red,drc)
        result = 0
        for ball in sorted(balls, key=lambda x:x[-1]):
            # print(ball)
            if ball[-1]=='blue' and ball[0]==ball[1]==-1:
                result = -1
            if ball[-1]=='red' and ball[0]==ball[1]==-1 and result!= -1:
                print(time)
                sys.exit()
        result_table = min(result,result_table)
    # print('ti',time)
        if time < 10:
            # print([drc,balls[0],balls[1],time+1], len(work))
            work.append([balls[0],balls[1],time+1])
    # sleep(1)
print(-1)