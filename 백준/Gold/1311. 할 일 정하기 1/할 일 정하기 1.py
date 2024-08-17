n = int(input())

dp = [0] * (1<<n)
last = [0]
for s in range(n):
    visited=set()
    for i,v in enumerate(map(int,input().split())):
        for key in last:
            # print(key,  key|(1<<i))
            if not key&(1<<i):
                tmp = key|(1<<i)
                if tmp not in visited:
                    dp[tmp] = dp[key]+v
                else:
                    dp[tmp] = min(dp[key]+v, dp[tmp])
                visited.add(tmp)
    last=visited
    # print(visited)
    # print(dp)
print(dp[-1])

