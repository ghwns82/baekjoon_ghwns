import sys

input = sys.stdin.readline


n = int(input())
a = [int(input()) for i in range(n)]


result = 0

stack = []
for i in a:
    diff = 0
    while stack and stack[-1] <= i:
        diff = max(diff, i-stack.pop())
    result+=diff
    stack.append(i)

if len(stack)>1:
    result += stack[0]-stack[-1]

print(result)