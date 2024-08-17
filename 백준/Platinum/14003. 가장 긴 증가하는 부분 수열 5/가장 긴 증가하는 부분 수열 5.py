import sys
from bisect import bisect_left

input = sys.stdin.readline
n = int(input())
a = list(map(int, input().split()))
stack = [[] for i in range(1_000_000)]
# stack = [[] for i in range(10)]
stack2 = []
for i in range(n):
    if not stack2:
        stack[0].append([i, a[i]])
        stack2.append(a[i])
    else:
        j = bisect_left(stack2, a[i])
        if j == len(stack2):
            stack2.append(a[i])
            stack[len(stack2) - 1].append([i, a[i]])
        else:
            # # # print(j)
            stack2[j] = a[i]
            stack[j].append([i, a[i]])

# print(*stack, sep='\n')
# print(stack2)

def search(arr, goal, last_value):
    s, e = 0, len(arr) - 1
    # print('se',s,e,goal,last_value)
    # m = (s + e) // 2
    # while s <= e:
    #     m = (s + e) // 2
    #     # # # print(s,m,e)
    #     if arr[m][0] >= goal:
    #         e = m-1
    #     else:
    #         s = m+1
        # time.sleep(1)
    # print('m',m)
    # print(arr)
    for x in range(e, -1, - 1):
        if last_value > arr[x][1] and arr[x][0]<=goal:
            break
    # print('x',x)
    return x


result = []
result.append(stack[len(stack2) - 1][-1][1])
last = stack[len(stack2) - 1][-1][0]
last_index = -1
for i in range(len(stack2) - 2, -1, -1):
    # # print('result',result)
    index = search(stack[i], last - 1, stack[i + 1][last_index][1])
    # print('i index',i,index)
    # # # print(stack[i + 1][last_index][1])
    stack[i]
    stack[i][index]
    # print(stack[i][index][1])
    result.append(stack[i][index][1])
    # print('result',result)
    last = stack[i][index][0]
    last_index = index
print(len(result))
print(*result[::-1])
