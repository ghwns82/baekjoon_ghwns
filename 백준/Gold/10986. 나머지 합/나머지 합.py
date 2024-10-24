n,m=map(int,input().split())

def x(d):
    return int(d)%m
a=list(map(x,input().split()))

result=[0]*m
s=0
# tmp=[]
for i in a:
    s+=i
    if s>=m:
        s%=m
    result[s]+=1
    # tmp.append(s)
# print(*result)
# print(tmp)
r = 0
for i in result:
    r+=i*(i-1)//2
    

print(r+result[0])


# 1 2
# 1 2 3
# 1 2 3 1 2
# 2 3 1
# 3
# 3 1 2
# 1 2