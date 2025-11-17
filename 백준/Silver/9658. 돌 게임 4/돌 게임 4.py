n = int(input())
dp=[0]*n
for i in range(1,n):
    for s in [-1,-3,-4]:
        j = i+s
        if j<0:
            break
        if not dp[j]:
            dp[i] = 1
            break
    # print(dp)
print(['CY','SK'][dp[-1]])

