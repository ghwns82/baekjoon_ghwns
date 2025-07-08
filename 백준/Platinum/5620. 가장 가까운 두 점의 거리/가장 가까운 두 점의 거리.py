import sys
input = sys.stdin.readline
n = int(input())
dots = sorted([list(map(int,input().split())) for i in range(n)])
result = (20000**2)*2

def rec(s,e):
    global result
    if e-s>3:
        m = (s+e)//2
        rec(s,m)
        rec(m+1,e)
        tmp = []
        mid_x = dots[m][0]
        for i in range(m-1,s-1,-1):
            if abs(dots[i][0]-mid_x)**2 <=result:
                tmp.append(dots[i])
            else:
                break
        for i in range(m,e+1):
            if abs(dots[i][0]-mid_x)**2 <=result:
                tmp.append(dots[i])
            else:
                break
        tmp.sort(key=lambda x:x[1])
        for i in range(0,len(tmp)):
            a,b=tmp[i]
            for j in range(i+1,len(tmp)):
                c,d=tmp[j]
                if abs(b-d)**2>result:
                    break
                result = min(result, abs(a-c)**2+abs(b-d)**2)
                if result==0:
                    print(0)
                    sys.exit()



    else:
        for i in range(s,e):
            a,b=dots[i]
            for j in range(i+1,e+1):
                c,d=dots[j]
                # print([a,b],[c,d],abs(a-c)**2+abs(b-d)**2)
                result = min(result, abs(a-c)**2+abs(b-d)**2)
                if result==0:
                    print(0)
                    sys.exit()
                    




rec(0,n-1)
print(result)