''' BOJ_17144_미세먼지 안녕!'''
from sys import stdin


def diffusion(lst, time):
    global res
    # END : T초가 지난 후 남아있는 미세먼지의 총량
    if time == T:
        for row in range(R):
            for col in range(C):
                if lst[row][col] > 0:
                    res += lst[row][col]

        return

    # 1. 확산
    # 확산 = 본래 확산하고 남은 미세먼지 + 인접점에서 확산 후 넘어온 미세먼지
    temp = [[0] * C for _ in range(R)]  # 후자의 정보를 저장하기 위한 리스트

    for x in range(R):
        for y in range(C):
            if lst[x][y] > 0:
                direction = 0  # 확산된 방향의 개수
                for k in range(4):  # 인접한 4방의 1칸씩만 봄
                    nx = x + [-1, 1, 0, 0][k]
                    ny = y + [0, 0, -1, 1][k]

                    # 전체 맵을 벗어나지 않고, 청정기 위치가 아니면 확산 가능
                    if 0 <= nx < R and 0 <= ny < C and lst[nx][ny] != -1:
                        direction += 1  # 확산된 방향의 수 증가
                        temp[nx][ny] += lst[x][y] // 5  # 확산되어 추가되는 양을 누적

                # 본래 칸에 있던 미세먼지의 남는 양
                lst[x][y] -= (lst[x][y] // 5) * direction

    # 위에서 구한 본래 칸의 감소된 양 + 주변에서 확산되어 겹치는 구간을 합해줌
    for r in range(R):
        for c in range(C):
            if temp[r][c] > 0:
                lst[r][c] += temp[r][c]

    # 2. 회전
    lst = bingle_bingle(lst)
    # 정해진 시간까지 재귀 호출로 반복
    diffusion(lst, time + 1)


# 빙글빙글
def bingle_bingle(lst):
    global whisen

    # 청정기 위쪽 반시계
    x, y = whisen[0]
    x -= 1  # 청정기 위치 제외한 다음 위치를 시작점으로 잡음.
    d = 0  # 방향 인덱스

    # 상우하좌
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]

    # 구하려는 방향의 역방향으로 가면서 다음 위치를 현재 위치로 1칸씩 당김.
    while 1:
        nx = x + dx[d]
        ny = y + dy[d]

        if 0 <= nx <= whisen[0][0] and 0 <= ny < C:
            lst[x][y] = lst[nx][ny]
            if [nx, ny] == whisen[0]:
                lst[x][y] = 0
                break
            x, y = nx, ny

        else:
            d = (d + 1) % 4
            continue

    # 청정기 아래쪽 시계
    x, y = whisen[1]
    x += 1
    d = 0

    # 하우상좌
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]

    while 1:
        nx = x + dx[d]
        ny = y + dy[d]

        if whisen[1][0] <= nx < R and 0 <= ny < C:
            lst[x][y] = lst[nx][ny]
            if [nx, ny] == whisen[1]:
                lst[x][y] = 0
                break
            x, y = nx, ny

        else:
            d = (d + 1) % 4
            continue

    # 회전이 끝난 리스트 반환
    return lst


'''  Main '''
R, C, T = map(int, stdin.readline().split())
arr = [list(map(int, stdin.readline().split())) for _ in range(R)]

# 청정기 위치 저장
whisen = []
for i in range(R):
    if arr[i][0] == -1:
        whisen.append([i, 0])

res = 0  # 남아있는 미세먼지의 총량
diffusion(arr, 0)
print(res)
