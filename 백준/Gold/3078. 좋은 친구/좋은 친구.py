from collections import deque
import sys

def count_good_friends(N, K, names):
    # 이름 길이별 큐를 저장하는 딕셔너리
    length_queues = [deque() for _ in range(21)]  # 2~20자라서 최대 21칸 (0~20)
    good_friends_count = 0

    for rank, name in enumerate(names):
        name_length = len(name)

        # 큐에서 등수 차이가 K를 초과하는 요소 제거
        while length_queues[name_length] and (rank - length_queues[name_length][0] > K):
            length_queues[name_length].popleft()

        # 현재 큐에 남아 있는 요소 개수만큼 좋은 친구 쌍 생성
        good_friends_count += len(length_queues[name_length])

        # 현재 학생의 등수를 큐에 추가
        length_queues[name_length].append(rank)

    return good_friends_count

# 입력 처리
N, K = map(int, sys.stdin.readline().split())
names = [sys.stdin.readline().strip() for _ in range(N)]

# 결과 출력
print(count_good_friends(N, K, names))