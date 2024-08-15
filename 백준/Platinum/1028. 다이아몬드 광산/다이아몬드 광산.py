n,m = map(int,input().split())
table = [input() for i in range(n)]

qr1 = [[0]*m for i in range(n)]
qr2 = [[0]*m for i in range(n)]
qr3 = [[0]*m for i in range(n)]
qr4 = [[0]*m for i in range(n)]


qr = [qr1,qr2,qr3,qr4]
d = [(-1,1),(-1,-1),(1,-1),(1,1)]
# 1,2사분면
for i in range(n):
    for j in range(m):
        if table[i][j]=='1':
            for k in range(2):
                qr[k][i][j]=1
                di,dj = i+d[k][0],j+d[k][1]
                if 0<=di<n and 0<=dj<m:
                    qr[k][i][j]+=qr[k][di][dj]
# 3,4사분면
for i in range(n-1,-1,-1):
    for j in range(m-1,-1,-1):
        if table[i][j]=='1':
            for k in range(2,4):
                qr[k][i][j]=1
                di,dj = i+d[k][0],j+d[k][1]
                if 0<=di<n and 0<=dj<m:
                    qr[k][i][j]+=qr[k][di][dj]




# print(*qr1,sep='\n')
# print()
# print(*qr2,sep='\n')
# print()
# print(*qr3,sep='\n')
# print()
# print(*qr4,sep='\n')
# print()


result = 0
for i in range(n):
    for j in range(m):
        tmp = min(qr3[i][j],qr4[i][j])
        for k in range(1,tmp+1):
            # if i!=2 or j!=3:
            #     continue
            di = (k-1)*2+i
            # print(i,j,tmp,di)
            if 0<=di<n and qr1[di][j]>=k and qr2[di][j]>=k:
                result = max(result,k)

print(result)
# 10 10
# 0000100000
# 0001010000
# 0010101000
# 0101010100
# 1010101010
# 0101010001
# 0010101010
# 0001010100
# 0000101000
# 0000010000

# 5

# 10 7
# 0100010
# 0010100
# 0001000
# 1010101
# 0100010
# 1010101
# 0001000
# 0010100
# 0100010
# 0000010

# 답: 3