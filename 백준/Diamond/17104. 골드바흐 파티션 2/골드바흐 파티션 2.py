from collections import deque
import sys
input = lambda : sys.stdin.readline().rstrip()
import cmath, math
from cmath import pi

def fft(p, w):
    n = len(p)
    if n == 1: #Base Case
        return p

    even, odd = p[0::2], p[1::2] #짝수차수와 홀수차수로 분할
    yeven, yodd = fft(even, w*w), fft(odd, w*w) #재귀적으로 불러옴 w*w인 이유는 w^2가 들어가기 떄문에

    z = complex(1, 0)
    y = [0] * n
    for j in range(n//2): #위에서 구한 공식
        y[j] = yeven[j] + z * yodd[j]
        y[j + n//2] = yeven[j] - z * yodd[j]
        z *= w
    return y
def mult(a, b):
    n = 1
    while n <= len(a) and n <= len(b):
        n *= 2
    n *= 2 #다항식의 크기를 2의 거듭제곱꼴로 맞춰준다
    a = a + [0] * (n - len(a))
    b = b + [0] * (n - len(b))
    w = complex(math.cos(2*pi/n), math.sin(2*pi/n)) #w계산
    a = fft(a, w)
    b = fft(b, w) #a, b다항식을 각각 fft 취해준다.
    c = [0] * n

    for i in range(n):
        c[i] = a[i] * b[i] #fft의 결과값을 곱해준다.
    c = fft(c, 1/w) #IFFT를 취해준다.

    for i in range(n):
        c[i] /= n
        c[i] = round(c[i].real)
    return c

num = 1000000
# num = 2000
che=set(range(3, num, 2))

for i in range(3, int(num**0.5)+1):
    if i in che:
        for t in range(i*2, num,i):
            che.discard(t)
che.add(2)


fx = [0]*(num//2)
for i in che:
    fx[i//2]=1

    
gx =mult(fx, fx)

for i in range(int(input())):
    number = int(input())
    if number==4:
        print(1)
    elif number//2 in che:
        print((gx[(number-1)//2]+1)//2)
    else:
        print(gx[(number-1)//2]//2)