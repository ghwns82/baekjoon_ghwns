# import sys
# input = sys.stdin.readline

n = int(input())
arr = list(map(int,input().split()))
result = arr[0]+arr[-1]
left,right = 0,n-1
while left<right:
    hap = arr[left]+arr[right]
    if abs(result)> abs(hap):
        result=hap
    if hap>0:
        right-=1
    elif hap<0:
        left+=1
    else:
        break
print(result)
