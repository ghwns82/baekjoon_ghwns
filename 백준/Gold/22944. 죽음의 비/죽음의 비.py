import heapq
import sys

input = lambda: sys.stdin.readline().strip()

# 입력 받기
n, h, d = map(int, input().split())
grid = [list(input()) for _ in range(n)]

# 시작점, 안전지대, 우산 리스트 찾기
nodes = []
start_idx = end_idx = None

for i in range(n):
    for j in range(n):
        if grid[i][j] == 'S':
            start_idx = len(nodes)
            nodes.append((i, j))
        elif grid[i][j] == 'E':
            end_idx = len(nodes)
            nodes.append((i, j))
        elif grid[i][j] == 'U':
            nodes.append((i, j))

# 맨해튼 거리 계산 함수
def manhattan_distance(p1, p2):
    return abs(p1[0] - p2[0]) + abs(p1[1] - p2[1])

# 노드 수
num_nodes = len(nodes)

# 노드 간 거리 계산
distances = [[float('inf')] * num_nodes for _ in range(num_nodes)]
for i in range(num_nodes):
    for j in range(num_nodes):
        if i != j:
            distances[i][j] = manhattan_distance(nodes[i], nodes[j])

# 다익스트라 알고리즘
def dijkstra():
    pq = [(0, start_idx, h, 0)]  # (누적 이동 횟수, 현재 노드 인덱스, 남은 체력, 우산 내구도)
    visited = {}

    while pq:
        moves, current, health, durability = heapq.heappop(pq)

        # 안전지대에 도착하면 최소 이동 횟수 반환
        if current == end_idx:
            return moves

        # 이미 방문한 상태인지 확인
        if (current, health, durability) in visited and visited[(current, health, durability)] <= moves:
            continue
        visited[(current, health, durability)] = moves

        # 다른 모든 노드로 이동 시도
        for next_index in range(num_nodes):
            if next_index == current:
                continue

            distance = distances[current][next_index]

            # 시작점에서는 우산 없이 이동
            if current == start_idx:
                if health < distance:
                    continue
                new_health = health - distance
                new_durability = 0
            else:
                # 우산 내구도 + 체력으로 이동 가능 여부 확인
                if health + durability < distance:
                    continue

                # 내구도 소진 여부 확인
                if durability >= distance:
                    new_health = health
                    new_durability = durability - distance
                else:
                    new_health = health - (distance - durability)
                    new_durability = 0

            # 체력이 0 이하라면 이동 불가
            if new_health < 0:
                continue

            # 우산을 획득하면 내구도 초기화
            if grid[nodes[next_index][0]][nodes[next_index][1]] == 'U':
                new_durability = d

            # 우선순위 큐에 새로운 상태 추가
            heapq.heappush(pq, (moves + distance, next_index, new_health, new_durability))

    # 안전지대에 도달하지 못한 경우
    return -1

# 결과 출력
print(dijkstra())