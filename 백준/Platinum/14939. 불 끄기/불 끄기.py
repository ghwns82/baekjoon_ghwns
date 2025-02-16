import sys
from copy import deepcopy

def convert(x):
    """'O'를 1, 'X'를 0으로 변환"""
    return +(x == "O")

def push(board):
    """첫 번째 줄의 상태를 결정한 후, 나머지 줄의 조작을 수행하는 함수"""
    board = deepcopy(board)  # 원본을 건드리지 않도록 복사
    cnt = 0  # 스위치 누른 횟수
    
    for i in range(1, 10):  # 1~9번 줄까지 처리
        for j in range(10):
            if board[i - 1][j] == 1:  # 윗줄이 1이면 눌러야 함
                cnt += 1
                for x, y in [(i + 1, j), (i - 1, j), (i, j), (i, j - 1), (i, j + 1)]:
                    if 0 <= x < 10 and 0 <= y < 10:
                        board[x][y] ^= 1  # 토글

    if all(board[i][j] == 0 for i in range(10) for j in range(10)):  # 모든 불이 꺼졌는지 확인
        return cnt
    return float('inf')  # 해결 불가능한 경우

def solve():
    """첫 번째 줄(0번째 줄)의 2^10가지 경우의 수를 모두 탐색"""
    global result
    result = float('inf')
    
    for case in range(1 << 10):  # 2^10(1024)가지 경우의 수 시도
        board = deepcopy(origin_board)  # 원본 보드 복사
        cnt = 0

        # 첫 번째 줄(0번째 줄)의 스위치 조작 경우 설정
        for j in range(10):
            if case & (1 << j):  # case에서 j번째 비트가 1이면 해당 스위치를 누름
                cnt += 1
                for x, y in [(0, j), (1, j), (0, j - 1), (0, j + 1)]:
                    if 0 <= x < 10 and 0 <= y < 10:
                        board[x][y] ^= 1

        # push 함수 실행하여 나머지 줄 조작 자동 진행
        result = min(result, push(board) + cnt)
    
    print(result if result != float('inf') else -1)

# 입력 처리
input = sys.stdin.readline
origin_board = [list(map(convert, list(input().strip()))) for _ in range(10)]

# 문제 해결
solve()