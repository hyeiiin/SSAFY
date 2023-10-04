""" SWEA_3307_최장 증가 부분 수열(LIS) """


def binarySearch(left, right, num):  # 이진 탐색을 위한 start, end, 비교할 숫자값
    mid = 0  # 이진 탐색을 위한 검색 범위

    # left가 right 이상이 되버리면 모든 범위를 탐색했다는 의미.
    while left < right:
        # 중간 범위 계산
        mid = (left + right) // 2

        # LIS의 중간 범위 숫자보다 비교할 숫자가 더 크다면,
        if res[mid] < num:
            left = mid + 1  # left의 위치를 mid 다음으로 설정하여 탐색 범위 갱신
        elif res[mid] > num:
            right = mid  # right의 위치를 mid로 설정하여 탐색 범위 갱신

    return right


TC = int(input())

for tc in range(1, TC + 1):
    N = int(input())
    lst = list(map(int, input().split()))  # 입력 받은 수열
    res = [0] * N  # 최장 증가 부분 수열(Longest Increasing Subsequence)
    res[0] = lst[0]  # 처음에 입력 받은 수열의 첫 번째 원소를 넣고 시작.

    # LIS의 현재 인덱스, 검사할 수열의 인덱스
    prev, nxt = 0, 1

    # 입력 수열의 끝까지 탐색
    while nxt < N:
        # print(f'Check => {prev} / {nxt}')

        # 1. 현재 LIS의 마지막 숫자 보다 입력 수열의 현재 숫자가 더 클 경우
        if res[prev] < lst[nxt]:
            # 증가 수열이 되므로, LIS의 다음 위치에 입력 수열의 숫자를 넣음
            res[prev + 1] = lst[nxt]
            prev += 1  # LIS의 다음 비교 인덱스 증가

        # 2. 현재 LIS의 마지막 숫자가 크거나 같을 경우 -> 숫자를 넣을 위치를 찾아야 함.
        else:
            # 이진 탐색을 통해 새로이 넣을 위치를 찾음
            newIdx = binarySearch(0, prev, lst[nxt])
            # 위에서 찾은 새 위치에 입력 수열의 숫자 대입 -> 증가 수열 유지
            res[newIdx] = lst[nxt]

        # 입력 수열의 다음 비교 인덱스 증가
        nxt += 1

    # 반복이 끝나면, prev는 LIS의 마지막 인덱스를 가르키므로,
    # 실제 구해야할 길이는 +1 (List의 Index가 0부터 시작하므로)
    print(f'#{tc} {prev + 1}')

