n,k = map(int,input().split())
arr = list(map(int,input().split()))
cnt = 0
result=-1

def merge_sort(A, p, r):
    # A[p..r]을 오름차순 정렬
    if p < r:
        q = (p + r) // 2  # 중간 지점
        merge_sort(A, p, q)      # 왼쪽 절반 정렬
        merge_sort(A, q + 1, r)  # 오른쪽 절반 정렬
        merge(A, p, q, r)        # 병합


def merge(A, p, q, r):
    global cnt,k,result
    # A[p..q]와 A[q+1..r]은 이미 정렬된 상태
    tmp = []
    i = p
    j = q + 1

    # 두 구간을 병합
    while i <= q and j <= r:
        if A[i] <= A[j]:
            tmp.append(A[i])
            i += 1
        else:
            tmp.append(A[j])
            j += 1

    # 남은 왼쪽 부분 복사
    while i <= q:
        tmp.append(A[i])
        i += 1

    # 남은 오른쪽 부분 복사
    while j <= r:
        tmp.append(A[j])
        j += 1

    # 병합 결과를 A[p..r]에 반영
    for t in range(len(tmp)):
        A[p + t] = tmp[t]
        cnt+=1
        if cnt==k:
            result = tmp[t]

merge_sort(arr,0,n-1)
print(result)