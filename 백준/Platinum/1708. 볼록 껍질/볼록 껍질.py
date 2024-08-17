# 1. 가장 아래 점을 찾기
# 2. 모든 점이 위 혹은 아래
# 만약 x가 같다면, 위 아래
# 만약 y가 같다면, 왼 오
# 그외엔 공식
# 만약 점이 겹치면 최대 점으로 업데이트

# (a1-a2)(y-b1)-(b1-b2)(x-a1) = 0
# b1(a2-a1) + (a1-a2)y +a1(b1-b2)+(b2-b1)x=0
# c1 = b1*(b2-a1) + a1(b1-b2)
# c2 = a1-a2
# c3 = b2-b1
# c1 + c2*y+c3*x=0
import sys
input = sys.stdin.readline
n = int(input())
dots = set()
next_dots = set()
for i in range(n):
    a, b = map(int, input().split())
    dots.add((a, b))
    next_dots.add((a, b))


def update(x1, y1, x2, y2, a, b):
    if abs(a - x1) + abs(b - y1) > abs(x2 - x1) + abs(y2 - y1):
        return a, b
    else:
        return x2, y2


def check(x1, y1, x2, y2):
    tmp = set()
    tmp2 = set()
    if x1 == x2:
        for a, b in dots:
            if (a, b) == (x1, y1):
                continue
            if a < x1:
                tmp.add(-1)
            elif a > x1:
                tmp.add(1)
            else:
                x2, y2 = update(x1, y1, x2, y2, a, b)
                tmp2.add((a,b))
            if len(tmp)==2:
                return x1,y1
    elif y1 == y2:
        for a, b in dots:
            if (a, b) == (x1, y1):
                continue
            if b < y1:
                tmp.add(-1)
            elif b>y1:
                tmp.add(1)
            else:
                x2, y2 = update(x1, y1, x2, y2, a, b)
                tmp2.add((a,b))
            if len(tmp)==2:
                return x1,y1
    else:
        for a, b in dots:
            if (a, b) == (x1, y1):
                continue
            if (x1-x2)*(b-y1)-(y1-y2)*(a-x1) > 0:
                tmp.add(-1)
            elif (x1-x2)*(b-y1)-(y1-y2)*(a-x1) < 0:
                tmp.add(1)
            else:
                x2, y2 = update(x1, y1, x2, y2, a, b)
                tmp2.add((a,b))
            if len(tmp)==2:
                return x1,y1
                
    if len(tmp) == 1:
        for t1, t2 in tmp2:
            visited.add((t1, t2))
            next_dots.discard((t1, t2))
        x1, y1 = x2, y2
        result.add((x1, y1))
        next_dots.discard((x1, y2))
    return x1, y1


result = set()
visited = set()
x1, y1 = min(dots)
result.add((x1, y1))

while True:
    tmp = []
    # print((x1,y1),result,visited)
    for x2, y2 in next_dots:
        if (x2, y2) in result or (x2, y2) in visited:
            continue
        nx, ny = check(x1, y1, x2, y2)
        if x1 != nx or ny != y1:
            x1, y1 = nx, ny
            break

    else:
        break

# print(result)
print(len(result))
