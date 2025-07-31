from collections import deque
def solution(storey):
    work = deque([[storey,0]])
    result = 100_000_000
    while work:
        cur,cnt = work.popleft()
        while cur>0 and cur%10==0:
            cur//=10
        if cur==0:
            result = min(result, cnt)
        elif cur==1:
            result = min(result,cnt+1)
        else:
            work.append([cur//10,cnt+cur%10])
            work.append([(cur//10)+1,cnt+10-cur%10])

        
    return result