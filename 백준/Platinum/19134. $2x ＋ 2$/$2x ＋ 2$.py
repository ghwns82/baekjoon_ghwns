n = int(input())
answer = 0
selected = 1
while n>0:
    # print(n, selected==1, n//2+1 + (n%2==1))
    if selected:
        answer += n//2 + 1 + (n%2==1 and n>1)
    n=n//2-1
    selected^=1
print(answer)

