def get_prime(n):
    prime_list=[]
    # print([n],n**0.5)
    for i in range(1,int(n**0.5)+1):
        if n%i==0:
            prime_list.append(i)
            prime_list.append(-i)
            prime_list.append(n//i)
            prime_list.append(-n//i)
    return prime_list

def get_ans_2(a,b,c):
    x1 = (-b + (b**2-4*a*c)**0.5) / 2 / a
    x2 = (-b - (b**2-4*a*c)**0.5) / 2 / a
    return x1,x2


def zzcal(a,b,c,d):
    if d==0:
        if b**2 -4 * a * c >=0:
            return {0.0, *get_ans_2(a,b,c)}
        else:
            return {0.0}
    for p in get_prime(abs(d)):
        if ((a * p + b)*p + c)*p+d == 0:
            a,b,c = a, b + a*p, -d/p
            if b**2 -4 * a * c >=0:
                return {p*1.0, *get_ans_2(a,b,c)}
            else:
                return {p}


for _ in range(int(input())):
    a,b,c,d = map(int,input().split())
    for _ans in sorted(zzcal(a,b,c,d)) :
        print("{:.04f}".format(_ans), end = ' ')
    print()