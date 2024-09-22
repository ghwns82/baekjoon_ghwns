import sys
from math import sqrt
input = lambda : sys.stdin.readline().strip()

def mul_mat(a,b):
    c = [[0]*len(a) for i in range(len(a))]
    for i in range(len(a)):
        for j in range(len(a)):
            for k in range(len(a)):
                c[i][j]+=a[i][k]*b[k][j]
            c[i][j]%=1000
            if c[i][j]==0:
                c[i][j]+=1000
    return c

def dq(arr,n):
    # print(n,arr)
    if n==0:
        return [[1,0],[0,1]]
    if n==1:
        return arr
    tmp = mul_mat(arr,arr)
    if n%2==0:
        return dq(tmp,n//2)
    else:
        return mul_mat(dq(tmp,n//2),arr)
def get_result(n):
    tmp = dq(arr,n)
    # print('final',tmp)
    result=0
    result += tmp[0][0] * 3
    result += tmp[0][1]
    result*=2
    result-=1
    result%=1000
    result = int(result)
    result = str(result)[-3:].zfill(3)
    return result

arr = [[3,5],[1,3]]
for i in range(1, int(input())+1):
    result = 0
    tmp = get_result(int(input())-1)
    print(f'Case #{i}:',tmp)