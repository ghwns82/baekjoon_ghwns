n = int(input())
def cal(n):
    # print('this n is',n)
    if n<=1:
        return 1
    q,r=divmod(n,5)
    tmp = [2,4,8,6]
    result = tmp[q%4-1]
    q2=q%2
    for i in range(5*q2+1, 5*q2+r+1):
        result*=i
        result%=10
    # print('this result is',result,[n,q,r])
    return (cal(q)*result)%10
print(cal(n))