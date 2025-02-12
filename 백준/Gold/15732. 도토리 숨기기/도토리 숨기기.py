n,k,d = map(int,input().split())

rules = [list(map(int,input().split())) for i in range(k)]

def check(p):
    result=0
    for start,end,step in rules:
        if p>=start:
            result+= (min(end,p)-start)//step+1
    return result

result = 0
s,e=1,n
while s<=e:
    mid = (s+e)//2
    r = check(mid)
    # print(r,s,mid,e)
    if r >= d:
        e=mid-1
        result = mid
    else:
        s=mid+1
        
print(result)