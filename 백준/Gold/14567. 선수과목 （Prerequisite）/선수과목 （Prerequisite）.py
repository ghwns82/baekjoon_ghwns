import sys
input = lambda : sys.stdin.readline().rstrip()

n,m = map(int,input().split())
pre = [[] for i in range(n+1)]
wait = [0]*(n+1)
wait[0] = -1
result = [0]*(n+1)
for _ in range(m):
    a,b = map(int,input().split())
    pre[a].append(b)
    wait[b]+=1

work=[i for i in range(1,n+1) if wait[i]==0]
time = 0
while True:
    time+=1
    next_work=[]
    # print('current time',time)
    # print('current work',work)
    while work:
        study = work.pop()
        # print('pop',study)
        result[study] = time
        for next in pre[study]:
            wait[next]-=1
            if wait[next]==0:
                next_work.append(next)
    work= next_work
    if not work:
        break
print(*result[1:])
        
    
