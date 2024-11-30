c= [1,0]
for i in range(int(input())):
    a,b = c
    c = [b,a+b]
print(*c)