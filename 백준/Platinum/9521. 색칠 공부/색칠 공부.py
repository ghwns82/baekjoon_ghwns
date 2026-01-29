n,k = map(int,input().split())
arrow = [0] + list(map(int,input().split()))

cycle=set()
visited=set()
result = 1

MOD = 10**9 + 7
def mul(x):
    """result에 x를 곱하고 MOD 적용"""
    global result
    result = (result * (x % MOD)) % MOD

# 자기자신 처리
for node in range(1,n+1):   
    if arrow[node] == node:
        mul(k)
        visited.add(node)
        # print('자기자신 처리',node)
        # print('result',result)


for node in range(1,n+1):
    if node in visited:
        continue
    # print('for문 node',node)
    
    if arrow[node] == node:
        mul(k)
        continue

    cycle.add(node)
    start = node
    while True:
        next = arrow[node]
        # print('next',next)
        if next in visited:
            for i in range(len(cycle)):
                mul(k-1)
            for node in cycle:
                visited.add(node)
            cycle=set()
            break
        elif next in cycle:
            # 사이클 확인
            # print('check cycle')
            check=node
            cnt=0
            while True:
                next = arrow[node]
                # print(f'{node}->{next},|{check}')
                cnt+=1
                if next == check:
                    # tmp  = (k-1)**cnt + (k-1) * (-1)**cnt
                    a = pow(k - 1, cnt, MOD)
                    b = (k - 1) % MOD
                    if cnt % 2 == 1:  # (-1)^cnt = -1
                        b = (-b) % MOD
                    tmp = (a + b) % MOD
                    # print('tmp,cnt',[tmp,cnt])
                    mul(tmp)
                    for i in range(len(cycle) - cnt):
                        mul(k-1)
                    for node in cycle:
                        visited.add(node)
                    cycle=set()
                    break
                node=next
            break
        else:
            node = next
            cycle.add(node)
    # print(result)
for i in range(len(cycle)):
    mul(k-1)
print(result)
# print(result)