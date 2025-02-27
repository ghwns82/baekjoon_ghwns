def check(arr, max_sum):
    """주어진 max_sum으로 나눌 때 필요한 그룹 개수를 계산"""
    cnt = 1
    group_sum = 0
    for num in arr:
        if group_sum + num > max_sum:
            cnt += 1
            group_sum = num
        else:
            group_sum += num
    return cnt

def divide_groups(arr, max_sum, M):
    """M개의 그룹을 정확히 만들어서 그룹 크기를 반환"""
    groups = []
    current_sum = 0
    current_count = 0
    extra_groups = check(arr, max_sum) - M  # 초과된 그룹 개수 (줄여야 할 개수)

    for num in arr:
        if current_sum + num > max_sum or (extra_groups > 0 and len(groups) + (len(arr) - sum(groups)) > M):
            groups.append(current_count)
            current_sum = num
            current_count = 1
            extra_groups -= 1  # 초과 그룹을 줄여야 하므로 감소
        else:
            current_sum += num
            current_count += 1

    groups.append(current_count)

    # 정확히 M개가 될 수 있도록 조정
    while len(groups) < M:
        for i in range(len(groups)):
            if groups[i] > 1:
                groups[i] -= 1
                groups.insert(i + 1, 1)
                if len(groups) == M:
                    break

    return groups

def solve():
    # 입력 받기
    global N, M
    N, M = map(int, input().split())
    arr = list(map(int, input().split()))

    # 이분 탐색 범위 설정
    low, high = max(arr), sum(arr)
    result = high  # 최적의 최대 그룹 합

    while low <= high:
        mid = (low + high) // 2
        if check(arr, mid) <= M:
            result = mid
            high = mid - 1
        else:
            low = mid + 1

    # 최적의 result 값을 기반으로 정확히 M개의 그룹을 만듦
    best_groups = divide_groups(arr, result, M)

    # 결과 출력
    print(result)
    print(" ".join(map(str, best_groups)))

solve()