import sys
n=int(input())
exs = [input().split() for i in range(n)]
def bimatch(n):
    if visited[n]:
        return False
    visited[n] = True

    for c in '+-*':
        num = eval(c.join(exs[n]))
        if num not in selected or bimatch(selected[num][0]):
            selected[num] = (n,c)
            return True
    return False

selected={}
for i in range(n):
    visited=[False]*n
    if not bimatch(i):
        print('impossible')
        sys.exit()

answer=[0]*n
for i,(v1,v2) in selected.items():
    answer[v1]=(v2,i)

for i in range(n):
    print(exs[i][0],answer[i][0],exs[i][1],'=',answer[i][1])