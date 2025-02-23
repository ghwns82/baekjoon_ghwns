from decimal import *
import sys
input = lambda: sys.stdin.readline().strip()
getcontext().prec = 100



for _ in range(int(input())):
    A, B, C, D = map(Decimal, input().split())

    def f(x, a=A, b=B, c=C, d=D):
        return a * (x**3) + b * (x**2) + c * x + d

    def bisect(start, end, show=False):
        epsilon = Decimal("1e-20")
        while abs(end - start) > epsilon:
            mid = (start + end) / 2
            if show:
                print(round(start,3),round(mid,3),round(end,3))
                print(round(f(mid),3),round(f(start),3))
            if f(mid)*f(start)>0:
                start = mid
            else:
                end = mid
        return start

    MIN_Value = Decimal(-1000000)
    MAX_Value = Decimal(1000000)

    ta, tb, tc = 3 * A, B, C
    sq = (tb * tb - ta * tc)
    ans = []
    # print('sq',sq)
    if sq<=0:
        ans.append(bisect(MIN_Value, MAX_Value))
    else:
        sq= sq**Decimal('0.5')
        x1, x2 = (-tb + sq) / ta, (-tb - sq) / ta
        x1, x2 = min(x1, x2), max(x1, x2)
        # print('x1,x2',x1,x2)
        # print('fx1,fx2',f(x1),f(x2))
        if round(f(x1)*f(x2),21)>0:
            # print('this1')
            ans.append(bisect(MIN_Value, MAX_Value))
        elif round(f(x1),21)==0:
            # print('this2')
            ans.append(x1)
            ans.append(bisect(x2, MAX_Value))
        elif round(f(x2),21)==0:
            # print('this3')
            ans.append(bisect(MIN_Value,x1))
            ans.append(x2)
        else:
            # print('this4')
            ans.append(bisect(MIN_Value, x1))
            ans.append(bisect(x1,x2))
            ans.append(bisect(x2,MAX_Value))
            # ans.append(bisect(x2,MAX_Value, True))
    ans=['{:.10f}'.format(i) for i in ans]
    print(*ans)