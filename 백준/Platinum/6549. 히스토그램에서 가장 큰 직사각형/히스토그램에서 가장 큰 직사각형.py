import sys
input = sys.stdin.readline

while True:

    h_list=list(map(int,input().split()))
    if len(h_list)==1:
        break
    h_list = h_list[1:]
    result = 0
    stack=[]
    for i,h in enumerate(h_list+[0]):
        # print(i,h)
        tmp=i
        while True:
            if not stack or stack[-1][0]<=h:
                stack.append([h,tmp])
                break
            else:
                h2,j = stack.pop()
                # print('pop', h2*(i-j))
                tmp=j
                result = max(result, h2*(i-j))
        # print('ss',stack,result)

    print(result)