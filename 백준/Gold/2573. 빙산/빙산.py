import sys
input = lambda : sys.stdin.readline().strip()

n,m=map(int,input().split())
space=[list(map(int,input().split())) for i in range(n)]
ices = [(i,j) for i in range(n) for j in range(m) if space[i][j]]

direction = [[0,1],[1,0],[0,-1],[-1,0]]
visited=[[1]*m for i in range(n)]
time=0
flag=True
while ices:
    if flag:
        x,y = ices[0]
        visited[x][y]=time
        queue = [[x,y]]
        cnt=1
        while queue:
            i,j=queue.pop()
            for dx, dy in direction:
                x,y = i+dx,j+dy
                if 0<=x<n and 0<=y<m and space[x][y] and visited[x][y]!=time:
                    visited[x][y]=time
                    queue.append([x,y])
                    cnt+=1
        if cnt < len(ices):
            print(time*-1)
            break
    time-=1
    flag=False            
    
    
    
    diff = []
    for ice in ices:
        cnt=0
        for dx, dy in direction:
            x,y = ice[0]+dx,ice[1]+dy
            if 0<=x<n and 0<=y<m and space[x][y]==0:
                cnt+=1
        diff.append(cnt)
    new=[]
    for index in range(len(ices)):
        i,j = ices[index]
        space[i][j] = max(0, space[i][j]-diff[index])
        index+=1
        if space[i][j]:
            new.append((i,j))
        else:
            flag=True
    ices=new
else:
    print(0)