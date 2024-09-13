a,b = 3,1
mod = 1_000_000_007

def matrix_mal(arr1,arr2):
    arr3 = [[0,0],[0,0]]
    for a in range(2):
        for b in range(2):
            for i in range(2):
                arr3[a][b]+=arr1[a][i]*arr2[i][b]
                tmp = arr3[a][b]
            arr3[a][b] %=mod
    return arr3
def cal(arr, c):
    if c==0:
        return [[1,0],[0,1]]
    if c ==1:
        return arr
    if c == 2:
        return matrix_mal(arr,arr)
    tmp = cal(arr,c//2)
    if c%2:
        return matrix_mal(matrix_mal(tmp,tmp),cal(arr,1))
    else:
        return matrix_mal(tmp,tmp)

n = int(input())
if n%2:
    print(0)
else:
    tmp = cal([[4,-1],[1,0]], n//2)
    # print(tmp)
    print((3*tmp[1][0] + tmp[1][1])%mod)