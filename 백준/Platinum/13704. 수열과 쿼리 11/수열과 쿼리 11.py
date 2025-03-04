from collections import Counter
import sys
from math import comb

input = lambda: sys.stdin.readline().rstrip()
n,k = map(int,input().split())
ns = [0]
for i in map(int, input().split()):
    ns.append(ns[-1] ^ i)
sq = n**0.5
m = int(input())
queries = sorted(
    [list(map(int, input().split())) + [i] for i in range(m)],
    key=lambda x: (x[0] // sq, x[1]),
)
answer = [0] * m
ct = Counter()
left, right = 1, 1

res=0
for i in range(0,2):
    me, pair =ns[i] ,ns[i]^k
    if k:
        res -= ct[me]*ct[pair]
    else:
        res -= comb(ct[me], 2)
    ct[me]+=1
    if k:
        res+= ct[me]*ct[pair]
    else:
        res += comb(ct[me], 2)

# print('first res',res)
# print('first ct',ct)


def modify(index, change, increase):  # change는 인덱스 변화
    global res
    # print('index',index+increase)
    if increase==1:
        index+=change
    me, pair =ns[index] ,ns[index]^k
    if k:
        res -= ct[me]*ct[pair]
    else:
        res -= comb(ct[me],2)
    ct[me]+=increase
    if k:
        res += ct[me]*ct[pair]
    else:
        res += comb(ct[me],2)
    if increase==-1:
        index+=change
    # print('me,pair',[me,pair])
    # print('ct',ct)
    # print('res',res)
    return index
# print('ns',ns)

for q in queries:

    while left > q[0]:
        left = modify(left - 1, -1,1,) + 1

    while right < q[1]:
        right = modify(right, 1, 1,)

    while left < q[0]:
        left = modify(left - 1, 1,-1,) + 1

    while right > q[1]:
        right = modify(right, -1, -1,)

    

    answer[q[2]]=res
    

for i in answer:
    print(i)
    pass