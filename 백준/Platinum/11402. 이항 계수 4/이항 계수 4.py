from math import comb


def convert(num,d):
    result=[]
    while num>0:
        result.append(num%d)
        num//=d
    return result


n,k,m = map(int,input().split())
p1=convert(n,m)
p2=convert(k,m)
result=1
for i in range(max(len(p1),len(p2))):
    try:
        a=p1[i]
    except:
        a=0
    try:
        b=p2[i]
    except:
        b=0
    result*=comb(a,b)
print(result%m)