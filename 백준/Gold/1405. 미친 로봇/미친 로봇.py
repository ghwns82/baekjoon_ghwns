import sys
input = sys.stdin.readline

N, e, w, n, s = map(int,input().split())


if N==1 or e*w==n*s==0:
    print(1.0)
    sys.exit()

size = 2*N+1

visited =[[False]*(size) for i in range(size)]

result = 0
probability = 1

cur_pos = (0,0)
visited[0][0] = True

direction =[]
for p, (dx,dy) in zip([e,w,n,s],[(0,1),(0,-1),(1,0),(-1,0)]):
    if p:
        direction.append([p,dx,dy])
def dfs(depth):
    global cur_pos,N,result, probability,size
    # print('cur',cur_pos)
    if depth == N:
        result += probability
        # print(probability,result)
        # print('visited',*visited,sep='\n')
        return
    x,y = cur_pos
    for p, dx, dy in direction:
        nx,ny = (dx+x)%size, (dy+y)%size
        # print('dx,dy',[dx,dy],'nx,ny',[nx,ny],visited[nx][ny])
        if visited[nx][ny]:
            continue
        probability*=p
        visited[nx][ny]=True
        cur_pos = (nx,ny)
        dfs(depth+1)
        probability//=p
        cur_pos = (x,y)
        visited[nx][ny]=False
        
dfs(0)

print(result/100**N)


    



