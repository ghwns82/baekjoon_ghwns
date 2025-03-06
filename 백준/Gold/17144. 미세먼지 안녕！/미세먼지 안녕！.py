import sys
input = sys.stdin.readline

# 이동 방향 (상, 하, 좌, 우)
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def spread_dust(R, C, room):
    new_room = [[0] * C for _ in range(R)]  # 새로운 방 상태 저장
    cleaner = []  # 공기청정기 위치 저장

    # 기존 방 상태 복사 및 공기청정기 위치 찾기
    for i in range(R):
        for j in range(C):
            if room[i][j] == -1:
                cleaner.append(i)  # 공기청정기 위치 저장
                new_room[i][j] = -1
            elif room[i][j] > 0:
                spread_amount = room[i][j] // 5  # 확산량
                spread_count = 0  # 확산된 방향 개수
                
                # 네 방향으로 확산
                for d in range(4):
                    ni, nj = i + dx[d], j + dy[d]
                    if 0 <= ni < R and 0 <= nj < C and room[ni][nj] != -1:
                        new_room[ni][nj] += spread_amount
                        spread_count += 1

                # 남은 미세먼지 양 계산
                new_room[i][j] += room[i][j] - (spread_amount * spread_count)

    return new_room, cleaner

def clean_air(R, C, room, cleaner):
    upper, lower = cleaner  # 공기청정기 위쪽, 아래쪽 위치
    
    # **반시계 방향 순환 (위쪽 공기청정기)**
    for i in range(upper-1, 0, -1):
        room[i][0] = room[i-1][0]
    for j in range(C-1):
        room[0][j] = room[0][j+1]
    for i in range(upper):
        room[i][C-1] = room[i+1][C-1]
    for j in range(C-1, 1, -1):
        room[upper][j] = room[upper][j-1]
    room[upper][1] = 0  # 정화된 공기

    # **시계 방향 순환 (아래쪽 공기청정기)**
    for i in range(lower+1, R-1):
        room[i][0] = room[i+1][0]
    for j in range(C-1):
        room[R-1][j] = room[R-1][j+1]
    for i in range(R-1, lower, -1):
        room[i][C-1] = room[i-1][C-1]
    for j in range(C-1, 1, -1):
        room[lower][j] = room[lower][j-1]
    room[lower][1] = 0  # 정화된 공기

def get_dust_amount(R, C, room):
    return sum(sum(row) for row in room) + 2  # 공기청정기 (-1) 두 개 제거

# **메인 로직**
R, C, T = map(int, input().split())
room = [list(map(int, input().split())) for _ in range(R)]

for _ in range(T):
    room, cleaner = spread_dust(R, C, room)  # 미세먼지 확산
    clean_air(R, C, room, cleaner)  # 공기청정기 작동

print(get_dust_amount(R, C, room))  # 남은 미세먼지 총합 출력
