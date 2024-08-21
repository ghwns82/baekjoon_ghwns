from math import gcd
a, c = map(int,input().split())
b, d = map(int,input().split())

g = gcd(c,d)
tmp1 = a * d//g + b*c//g
tmp2 = c*d//g
g2 = gcd(tmp1,tmp2)
print(tmp1//g2, tmp2//g2)

# c//g
# d//g