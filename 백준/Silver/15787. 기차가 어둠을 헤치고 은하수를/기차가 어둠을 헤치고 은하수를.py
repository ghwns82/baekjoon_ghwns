n,m = map(int, input().split())
train_list = [0]*n
for _ in range(m):
    a,*b = map(int,input().split())
    if a==1:
        train_list[b[0]-1] |= 1<<(b[1]-1)
    elif a==2:
        train_list[b[0]-1] &= ~(1<<(b[1]-1))
    elif a==4:
        train_list[b[0]-1]>>=1
    elif a==3:
        train_list[b[0]-1]<<=1
        train_list[b[0]-1] &= ~(1<<20)
    # print(train_list)
print(len(set(train_list)))