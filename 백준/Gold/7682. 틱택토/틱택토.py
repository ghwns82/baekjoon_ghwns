import sys
input = lambda: sys.stdin.readline().strip()

while (ttt:=input())!='end':
    counter = [0,0,0] # ox.
    ## 개수비교
    for t in ttt:
        if t=='.':
            counter[-1]+=1
        else:
            counter[t=='X']+=1
    if not counter[0]<=counter[1]<=counter[0]+1:
        print('invalid')
        continue

    # 승자 확인
    winner=[0,0] # ox
    for i in range(0,9,3):
        if ttt[i]==ttt[i+1]==ttt[i+2]:
            if ttt[i]!='.':
                winner[ttt[i]=='X']+=1
    for i in range(0,3):
        if ttt[i]==ttt[i+3]==ttt[i+6]:
            if ttt[i]!='.':
                winner[ttt[i]=='X']+=1
    if ttt[2]==ttt[4]==ttt[6]:
        if ttt[2]!='.':
            winner[ttt[2]=='X']+=1
    if ttt[0]==ttt[4]==ttt[8]:
        if ttt[0]!='.':
            winner[ttt[0]=='X']+=1
    # print('winner',winner)
    if winner[0]==0 and winner[1]!=0:
        print(['invalid','valid'][counter[0]+1==counter[1]])
    elif winner[0]!=0 and winner[1]==0:
        print(['invalid','valid'][counter[0]==counter[1]])
    elif winner[0]==winner[1]==0 and counter[-1]:
        print('invalid')
    else:
        print(['valid','invalid'][winner[0] > 0 and winner[1] > 0])