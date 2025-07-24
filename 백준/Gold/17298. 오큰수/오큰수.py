input()

num_list = list(map(int, input().split()))
stack = []
result = []

for i in range(len(num_list)-1,-1,-1):
    while stack and stack[-1]<= num_list[i]:
        stack.pop()
    
    if not stack:
        stack.append(num_list[i])
        result.append(-1)
    else:
        result.append(stack[-1])
        stack.append(num_list[i])
print(*result[::-1])