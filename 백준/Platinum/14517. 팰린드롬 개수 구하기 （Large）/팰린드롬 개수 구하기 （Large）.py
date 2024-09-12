import sys
sys.setrecursionlimit(10**8)
from functools import cache

s = input()
n = len(s)
mod = 10007

@cache
def solve(l,r):
    if l>r:
        return 0
    if l==r:
        return 1
    return (solve(l+1,r) + solve(l,r-1) - solve(l+1,r-1)*(1 - +(s[l]==s[r]) ) +(s[l]==s[r]))%mod

print(solve(0,n-1))