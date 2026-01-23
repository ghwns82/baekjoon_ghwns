import sys
input = sys.stdin.readline

n,b,c = map(int,input().split())
arr = list(map(int,input().split()))+[0,0]
cost= 0
if b<c:
    c = b
for i in range(n):
    if arr[i+1] > arr[i+2]:
        ak = min(arr[i],arr[i+1]-arr[i+2])
        arr[i]-=ak
        arr[i+1]-=ak
        cost+=(b+c)*ak
        ak = min(arr[i],arr[i+1],arr[i+2])
        arr[i]-=ak
        arr[i+1]-=ak
        arr[i+2]-=ak
        cost+=(b+c+c)*ak

    else:
        ak = min(arr[i],arr[i+1],arr[i+2])
        arr[i]-=ak
        arr[i+1]-=ak
        arr[i+2]-=ak
        cost+=(b+c+c)*ak
        ak = min(arr[i],arr[i+1])
        arr[i]-=ak
        arr[i+1]-=ak
        cost+=(b+c)*ak
    # print(i,cost)
    cost+=b*arr[i]
    # print(i,cost)
print(cost)

