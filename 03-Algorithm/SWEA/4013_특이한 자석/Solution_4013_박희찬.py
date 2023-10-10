''' SWEA_4013_특이한 자석 '''
from collections import deque


def iamSpinRoundAndRound(cur, d):
    visited[cur] = True

    # 1. 이전 톱니가 있을 경우
    if cur - 1 >= 1 and not visited[cur - 1]:
        # 1-1. 이전 톱니와의 접점이 양극인 경우
        # 재귀 호출 -> 또 다른 톱니와의 접점 확인, 반대 방향 회전
        if qu[cur][6] != qu[cur - 1][2]:
            iamSpinRoundAndRound(cur - 1, -1 if d == 1 else 1)

    # 1. 다음 톱니가 있을 경우
    if cur + 1 <= 4 and not visited[cur + 1]:
        # 1-1. 다음 톱니와의 접점이 양극인 경우
        # 재귀 호출 -> 또 다른 톱니와의 접점 확인, 반대 방향 회전
        if qu[cur][2] != qu[cur + 1][6]:
            iamSpinRoundAndRound(cur + 1, -1 if d == 1 else 1)

    # 최초 함수에 들어온 현재 톱니를 방향에 맞게 회전
    qu[cur].rotate(d)
    return


for tc in range(1, int(input()) + 1):
    K = int(input())  # 회전 횟수

    # 자석의 정보를 담을 큐
    qu = deque()
    qu.append(deque([]))  # 1번 인덱스부터 쓰기 위함.

    # 4개의 자석들 정보를 입력받아 저장
    for _ in range(4):
        subQu = deque(map(int, input().split()))
        qu.append(subQu)

    for _ in range(K):
        visited = [False] * 5
        # 회전시키려는 자석의 번호, 회전 방향
        start, direc = map(int, input().split())
        iamSpinRoundAndRound(start, direc)

    res = 0  # 자석 점수 계산
    for idx in range(1, len(qu)):
        if qu[idx][0]:
            res += 2 ** (idx - 1)

    print(f"#{tc} {res}")
