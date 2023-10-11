''' SWEA_1486_장훈이의 높은 선반 '''


def subSet():
    global res

    # 공집합을 제외한 부 집합
    for i in range(1, 1 << N):
        top = 0
        for j in range(N):
            if i & (1 << j):
                top += lst[j]

        # 만들어진 탑이 선반 이상일 경우, 차의 최솟값 갱신
        if top >= B:
            res = min(res, top - B)


''' Main '''
for tc in range(1, int(input()) + 1):
    N, B = map(int, input().split())
    lst = list(map(int, input().split()))

    res = float('inf')

    subSet()

    print(f'#{tc} {res}')
