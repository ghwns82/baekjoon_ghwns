gears=[input(),input(),input(),input()]
up=[0,0,0,0]
for _ in range(int(input())):
    start, direction = map(int, input().split())
    d=[0,0,0,0]
    d[start-1]=direction
    for i in range(start-2,-1,-1):
        if not d[i+1]:
            # print('break')
            break
        if gears[i+1][(up[i+1]-2)%8]!=gears[i][(up[i]+2)%8]:
            d[i] = -1 * d[i+1]
    for i in range(start,4):
        if not d[i-1]:
            # print('break2')
            break
        if gears[i][(up[i]-2)%8]!=gears[i-1][(up[i-1]+2)%8]:
            d[i] = -1 * d[i-1]
    for i in range(4):
        up[i] = (up[i] - d[i])%8
    # print(d,up)

print(int(''.join([gears[i][up[i]] for i in range(4)][::-1]),2))
        
