def robot_vacuum_cleaner(N, M, r, c, d, room):
    # 방향 벡터 (북, 동, 남, 서)
    directions = [(-1, 0), (0, 1), (1, 0), (0, -1)]

    # 청소한 칸 개수
    cleaned_count = 0

    # 현재 위치 및 방향
    x, y, direction = r, c, d

    while True:
        # 1. 현재 칸이 청소되지 않았다면 청소
        if room[x][y] == 0:
            room[x][y] = 2  # 청소 완료 표시
            cleaned_count += 1

        # 2. 현재 칸의 주변 4칸 탐색
        found = False
        for _ in range(4):
            # 반시계 방향으로 90도 회전
            direction = (direction + 3) % 4
            nx, ny = x + directions[direction][0], y + directions[direction][1]

            # 청소되지 않은 빈 칸이 있는 경우 전진
            if room[nx][ny] == 0:
                x, y = nx, ny
                found = True
                break

        # 3. 청소되지 않은 빈 칸이 없는 경우
        if not found:
            # 뒤로 이동할 수 있으면 이동
            back_direction = (direction + 2) % 4
            bx, by = x + directions[back_direction][0], y + directions[back_direction][1]
            if room[bx][by] != 1:
                x, y = bx, by
            else:
                # 뒤가 벽인 경우 작동 중지
                break

    return cleaned_count

# 입력 처리
N, M = map(int, input().split())
r, c, d = map(int, input().split())
room = [list(map(int, input().split())) for _ in range(N)]

# 결과 출력
print(robot_vacuum_cleaner(N, M, r, c, d, room))