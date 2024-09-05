n,m = map(int,input().split())

a = [list(map(int,input().split())) for i in range(n)]

max_value=0




# ㅢ
for i in range(n):
    for j in range(0,m-3):
        max_value = max(a[i][j]+a[i][j+1]+a[i][j+2]+a[i][j+3], max_value)
for i in range(n-3):
    for j in range(m):
        max_value = max(a[i][j]+a[i+1][j]+a[i+2][j]+a[i+3][j], max_value)
# ㅁ
for i in range(n-1):
    for j in range(m-1):
        max_value = max(a[i][j]+a[i+1][j]+a[i+1][j+1]+a[i][j+1], max_value)

# ㅗㅜㄱㄴ
for i in range(n-1):
    for j in range(m-2):
        max_value = max(a[i][j]+a[i][j+1]+a[i][j+2]+
                        max(a[i+1][j],a[i+1][j+1],a[i+1][j+2]), max_value)
        max_value = max(a[i+1][j]+a[i+1][j+1]+a[i+1][j+2]+
                        max(a[i][j],a[i][j+1],a[i][j+2]), max_value)
# ㅓㅏㄱㄴ
for i in range(n-2):
    for j in range(m-1):
        max_value = max(a[i][j]+a[i+1][j]+a[i+2][j]+
                        max(a[i][j+1],a[i+1][j+1],a[i+2][j+1]), max_value)
        max_value = max(a[i][j+1]+a[i+1][j+1]+a[i+2][j+1]+
                        max(a[i][j],a[i+1][j],a[i+2][j]), max_value)

# ㄱㄴ
for i in range(n-2):
    for j in range(m-1):
        max_value = max(a[i+1][j]+a[i+1][j+1]+
            max(a[i][j],a[i][j+1])+
            max(a[i+2][j],a[i+2][j+1]), max_value)
for i in range(n-1):
    for j in range(m-2):
        max_value = max(a[i][j+1]+a[i+1][j+1]+
                       max(a[i][j],a[i+1][j])+
                       max(a[i][j+2],a[i+1][j+2]),max_value)
print(max_value)