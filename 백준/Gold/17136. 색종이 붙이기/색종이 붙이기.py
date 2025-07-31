board = [list(map(int,input().split())) for i in range(10)]


result = 100
papers = [5]*6
def rec(index,cnt):
    while index<100:
        i,j = index//10, index%10
        index+=1
        if board[i][j]==0:
            continue
        
        for d in range(1,6):
            if j+d>10 or i+d>10:
                continue
            flag = True
            for di in range(i,i+d):
                for dj in range(j,j+d):
                    if board[di][dj]==0:
                        flag = False
                        break

            if papers[d] and flag:
                papers[d]-=1
                for di in range(i,i+d):
                    for dj in range(j,j+d):
                        board[di][dj] = 0
                rec(index,cnt+1)
                for di in range(i,i+d):
                    for dj in range(j,j+d):
                        board[di][dj] = 1
                papers[d]+=1
        break
    else:
        global result
        result = min(cnt,result)

rec(0,0)
if result == 100:
    print(-1)
else:
    print(result)            