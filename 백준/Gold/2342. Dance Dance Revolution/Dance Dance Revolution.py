import sys

# 이동 비용 정의
def move_cost(from_pos, to_pos):
    if from_pos == 0:  # 중앙에서 이동
        return 2
    if from_pos == to_pos:  # 같은 위치를 반복
        return 1
    if abs(from_pos - to_pos) == 2:  # 반대편 이동
        return 4
    return 3  # 인접 이동

def min_energy(steps):
    INF = float('inf')
    dp = {}  # DP 테이블 (딕셔너리 사용하여 메모리 절약)

    # 초기 상태 (두 발이 중앙에 있음)
    dp[(0, 0)] = 0

    for step in steps:
        next_dp = {}

        for (left, right), energy in dp.items():
            # 왼발을 움직이는 경우
            if right != step:  # 두 발이 같은 위치로 갈 수 없음
                new_cost = energy + move_cost(left, step)
                if (step, right) not in next_dp or next_dp[(step, right)] > new_cost:
                    next_dp[(step, right)] = new_cost

            # 오른발을 움직이는 경우
            if left != step:
                new_cost = energy + move_cost(right, step)
                if (left, step) not in next_dp or next_dp[(left, step)] > new_cost:
                    next_dp[(left, step)] = new_cost

        dp = next_dp  # 다음 단계로 업데이트

    return min(dp.values())  # 최소 힘 반환

# 입력 처리
steps = list(map(int, sys.stdin.readline().split()))
steps.pop()  # 마지막 0 제거

# 결과 출력
print(min_energy(steps))