import sys
input = lambda: sys.stdin.readline().strip()
n,k = map(int,input().split())
arr = list(map(int,input().split()))
i=0
while i<n and arr[i]:
    i+=1
    

s,e=0,0
result = 0
sum=0
ability = k

while e<n:
    # print(s,e,sum, ability)
    if arr[e]%2==0:
        sum+=1
        result = max(result,sum)
        e+=1
    else:
        if ability:
            ability-=1
            e+=1
        else:
            if arr[s]%2:
                ability+=1
            else:
                sum-=1
            s+=1    
        
print(result)