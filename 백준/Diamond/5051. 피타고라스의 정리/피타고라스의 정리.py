from cmath import sin, cos, pi
import sys
I = sys.stdin.readline

def FFT(a,inv):
    n = len(a)
    j=0
    for i in range(1,n):
        bit = n>>1
        while j>=bit:
            j-=bit
            bit>>=1
        j+=bit
        if i<j: 
            tmp = a[i]
            a[i] = a[j]
            a[j] = tmp
    l = 2
    while l<=n:
        ang = 2*pi/l * (-1 if inv else 1)
        wlen = complex(cos(ang),sin(ang))
        for i in range(0,n,l):
            w = 1
            for j in range(l//2):
                u,v = a[i+j],a[i+j+l//2]*w
                a[i+j] = u+v
                a[i+j+l//2] = u-v
                w *= wlen
        l<<=1
    if inv:
        for i in range(n): a[i]/=n

def multiply(u_,v_):
    n = 1
    length = len(u_)+len(v_)-1
    while n<length: n<<=1
    u = u_.copy()
    v = v_.copy()
    u += [0]*(n-len(u))
    v += [0]*(n-len(v))
    FFT(u,False); FFT(v,False)
    for i in range(n): u[i]*=v[i]
    FFT(u,True)
    return [round(u[i].real) for i in range(length)]

n = int(I())
fx = [0]*n
gx = [0]*n
for i in range(1,n):
    fx[(i*i)%n]+=1
    gx[(2*i*i)%n]+=1

result = 0
ffx = multiply(fx,fx)

for i in range(len(ffx)):
    result += fx[i%n] * ffx[i]

# fx와 gx의 곱 추가
for i in range(n):
    result += fx[i] * gx[i]

print(result // 2)
