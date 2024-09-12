import sys
input = sys.stdin.readline
def can_place_routers(houses, n, c, distance):
    # 첫 번째 집에 공유기를 설치
    count = 1
    last_position = houses[0]

    # 공유기를 distance 이상의 간격으로 설치 가능한지 확인
    for i in range(1, n):
        if houses[i] - last_position >= distance:
            count += 1
            last_position = houses[i]
            if count == c:
                return True
    return False

def find_max_min_distance(houses, n, c):
    # 집의 좌표를 정렬
    houses.sort()

    # 이분 탐색을 위한 초기값 설정
    low = 1
    high = houses[-1] - houses[0]
    result = 0

    while low <= high:
        mid = (low + high) // 2
        # mid 거리를 기준으로 c개의 공유기를 설치할 수 있는지 확인
        if can_place_routers(houses, n, c, mid):
            result = mid
            low = mid + 1
        else:
            high = mid - 1

    return result

# 입력 받기
n, c = map(int, input().split())
houses = [int(input()) for _ in range(n)]

# 가장 인접한 두 공유기 사이의 최대 거리 출력
print(find_max_min_distance(houses, n, c))