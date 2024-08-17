import math
import sys
input = sys.stdin.readline
n = int(input())
stack = []
result = 0
for i in range(n):
    a = int(input())
    while True:
        if not stack or stack[-1][0]>a:
            stack.append([a,1])
            break
        elif stack[-1][0]==a:
            stack[-1][1]+=1
            break
        else:
            _,c=stack.pop()
            result+=(math.comb(c,2)+c)
            if stack:
                result+=c
    # print(stack,result)

while stack:
    _,c = stack.pop()
    result+=math.comb(c,2)
    if stack:
        result+=c
print(result)

# # 2    4    1    2    2    5    1
# # 제일 작은 수 +1
# # 제일 큰 수 +len
# # 중간 수 제거+1

# # 2    4    [4]
# # 4    1    [4,1]
# # 4    2    [4,2]
# # 1    2
# # 4    2    [4,2,2]
# # 2    2
# # 4    5    [5]
# # 2    5
# # 2    5
# # 5    1    [5,1]
