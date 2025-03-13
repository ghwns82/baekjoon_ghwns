from collections import deque
def solution(players, m, k):
    cnt = 0
    live_server = deque()
    for i in range(len(players)):
        while live_server and live_server[0] == i:
            live_server.popleft()
        diff = players[i]//m - len(live_server)
        if diff>0:
            cnt+=diff
            for _ in range(diff):
                live_server.append(i+k)
        
    return cnt