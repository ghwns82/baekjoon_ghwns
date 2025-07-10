import math
def solution(w,h):
    
    g = math.gcd(w,h)
    a,b = w//g, h//g

    return w*h - (a+b-1)* g