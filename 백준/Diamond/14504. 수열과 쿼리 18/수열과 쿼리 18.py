import sys
input = lambda: sys.stdin.readline().strip()
from bisect import bisect_right


n = int(input())
arr = list(map(int,input().split()))

info_table = []
unit_n = (int(n**0.5)+1)

for i in range(n//unit_n+1):
    partial_arr = sorted(arr[i*unit_n:(i+1)*unit_n])
    info_table.append(partial_arr)


def overK(value, partial_arr):
    result = len(partial_arr) - bisect_right(partial_arr, value)
    return result

def betweenIndex(left,right, k):
    result = 0
    for i in range(left//unit_n , right//unit_n):
        result += overK(k, partial_arr=info_table[i])
        
    for i in range(max(left//unit_n*unit_n,0), left):
        result -= arr[i] > k
        
    for i in range(right//unit_n*unit_n, right+1):
        result += arr[i] > k
    return result

# print(info_table)
        

for _ in range(int(input())):
    ctype, *content=map(int,input().split())
    if ctype==1:
        i,j,k=content
        print(betweenIndex(i-1,j-1,k))
    else:
        i,v = content
        index = i-1
        arr[index] = v
        partial_arr = sorted(arr[index//unit_n*unit_n:(index//unit_n+1)*unit_n])
        info_table[index//unit_n] = partial_arr
    # show()
