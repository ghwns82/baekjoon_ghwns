# import sys
# input = lambda : sys.stdin.readline().strip()

def recursion(n):
    global tmp
    global result
    if n==11:
        result = max(result, tmp)
        return
    for i in range(11):
        if info[n][i] != 0 and not visited[i]:
            visited[i]=True
            tmp+=info[n][i]
            recursion(n+1)
            tmp-=info[n][i]
            visited[i]=False

for _ in range(int(input())):
    info = [list(map(int,input().split())) for i in range(11)]
    visited = [False]*11
    tmp=0
    result = 0
    recursion(0)
    print(result)