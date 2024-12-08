while 1:
    a,b = map(int,input().split())
    if a+b==0:
        break
    print([["neither","factor"][not b%a],"multiple"][not a%b])