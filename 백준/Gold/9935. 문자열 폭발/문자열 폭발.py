n = input()
k = input()
i = len(n)-len(k)
stack=[]
for i in n:
    stack.append(i)
    if i == k[-1] and ''.join(stack[-len(k):])==k:
        del stack[-len(k):]
#         print(stack)
if stack:
    print(''.join(stack))
else:
    print('FRULA')