# 1. 가능한 선분 구하기
# 2. 모든 점과 선분간의 거리 구하기
# b1(a2-a1) + (a1-a2)y +a1(b1-b2)+(b2-b1)x=0
# c1 = b1*(a2-a1) + a1(b1-b2)
# c2 = a1-a2
# c3 = b2-b1
from math import ceil
def cal1(a,b,x=0,y=0,c1=0,c2=0,c3=0):
    return a-x
def cal2(a,b,x=0,y=0,c1=0,c2=0,c3=0):
    return b-y
def cal3(a,b,x=0,y=0,c1=0,c2=0,c3=0):
    return (c1+c2*b+c3*a)/((c2**2+c3**2)**0.5)

test_case = 1
while True:
    n = int(input())
    if not n:
        break
    dots = [list(map(int, input().split())) for i in range(n)]
    min_v = (10**4) * 4
    for x1, y1 in dots:
        for x2, y2 in dots:
            mind,maxd=0,0
            if x1==x2:
                cal = cal1
            elif y1==y2:
                cal = cal2
            else:
                cal = cal3
            c1 = y1 * (x2 - x1) + x1 * (y1 - y2)
            c2 = x1 - x2
            c3 = y2 - y1
            for a,b in dots:
                d = cal(a,b,x1,y1,c1,c2,c3)
                mind = min(d,mind)
                maxd = max(d,maxd)
            min_v = min(abs(maxd-mind),min_v)
    result =ceil(min_v*100)*0.01
    print(f'Case {test_case}: {result:.2f}')
    test_case+=1
