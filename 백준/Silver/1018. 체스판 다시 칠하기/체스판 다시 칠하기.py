N,M = map(int, input().split())
chess = [input() for _ in range(N)]
result = 64


for x in range(N-7):
    for y in range(M-7):
        cnt = 0
        for b in range(x, x + 8):
            for j in range(y, y + 8):              
                if (j + b)%2 == 0:
                    if chess[b][j] != 'W': cnt += 1  
                else :
                    if chess[b][j] != 'B': cnt += 1
        cnt = min(cnt, 64-cnt)
        result = min (result, cnt)
print(result)