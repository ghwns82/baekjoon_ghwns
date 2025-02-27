from bisect import bisect
import sys
prime = []; visited = [0]*50001
for i in range(2,50001):
  if not visited[i]:
    prime.append(i)
    if i<317:
      for j in range(1,50000//i+1):
        visited[i*j] = 1



def count(x):
    cnt_divisible=0
    q = [(1,-1,1)]
    while q:
        n,i,s = q.pop()
        for j in range(i+1, P):
            n1 = n*prime[j] ** 2
            if n1 > x:
                break
            cnt_divisible += x// n1 * s
            q.append([n1,j,-s])
    return x - cnt_divisible
P = len(prime)
k = int(input())

start, end = 1,2000000000
while start<end:
    mid = (start+end)//2
    v = count(mid)
    # print([start,mid,end],v)
    if v>=k:
        end=mid
    else:
        start=mid+1
print(end)