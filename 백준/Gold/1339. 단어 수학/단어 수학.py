dp = [0]*26
for _ in range(int(input())):
    digit=1
    for c in input()[::-1]:
        dp[ord(c)-ord('A')]+=digit
        digit*=10
result=0
digit = 9
for i in sorted(dp,reverse=True):
    result+=digit*i
    digit-=1
print(result)