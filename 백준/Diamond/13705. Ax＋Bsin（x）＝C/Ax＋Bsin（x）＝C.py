import sys
from decimal import Decimal, getcontext
getcontext().prec = 50

input = lambda: sys.stdin.readline().strip()

a, b, c = map(Decimal, input().split())
pi = Decimal('3.14159265358979323846264338327950288419716939937510')
def sin(x):
    x = x % (2 * pi)
    getcontext().prec += 2
    i, lasts, s, fact, num, sign = 1, 0, x, 1, x, 1
    while s != lasts:
        lasts = s
        i += 2
        fact *= i * (i - 1)
        num *= x * x
        sign *= -1
        s += num / fact * sign
    getcontext().prec -= 2
    return +s

def f(x):
    return a*x+b*Decimal(sin(x))-c

s, e = (c - b) / a, (c + b) / a

while e-s> Decimal(1e-21):
    m = (s + e) / 2

    v = f(m)
    # print([s,m,e],v)

    if 0==v:
        break
    elif 0 > v:
        s = m
    else:
        e = m



print(round(m, 6))