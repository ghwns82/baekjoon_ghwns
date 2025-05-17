n=int(input())
line1=list(map(int,input().split()))[::-1]
line2=[]
i=1
while i<=n:
    if line2 and line2[-1]==i:
        line2.pop()
        i+=1
    elif line1 and line1[-1]==i:
        line1.pop()
        i+=1
    else:
        if line1:
            line2.append(line1.pop())
        else:
            print('Sad')
            break
else:
    print('Nice')