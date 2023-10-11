# 최대한 많은 core에 연결했을 경우 전선 길이의 합을 구하기
# 여러 방법이 있을 경우 전선 길이의 합이 최소가 되는 값 구하기

import sys
input = sys.stdin.readline

t = int(input())

def connect(idx, cnt, length):
    global maxCnt, minLength
    if idx == len(cell_list):
        if cnt > maxCnt:
            maxCnt = cnt
            minLength = length
        elif maxCnt == cnt:
            minLength = min(minLength, length)
        return

    x, y = cell_list[idx]

    for d in range(4):
        count = 0
        nx = x
        ny = y

        while True:
            nx += dx[d]
            ny += dy[d]

            if nx < 0 or nx >= n or ny < 0 or ny >= n: # 벽 만난 경우 아무 이상 없이 연결된 것
                break
            if cell[nx][ny] == 1: # 전선 만난 경우 전선 길이 카운트했던 것 초기화
                count = 0
                break

            count += 1

        originX = x
        originY = y

        for i in range(count):
            originX += dx[d]
            originY += dy[d]

            cell[originX][originY] = 1

        if count == 0:
            connect(idx+1, cnt, length)

        else:
            connect(idx+1, cnt+1, length+count)

            originX = x
            originY = y

            for _ in range(count):
                originX += dx[d]
                originY += dy[d]

                cell[originX][originY] = 0


for tc in range(1, t+1):
    n = int(input())

    cell = [list(map(int, input().split())) for _ in range(n)]

    cell_list = []

    for i in range(n):
        for j in range(n):
            if i == 0 or i == n-1 or j == 0 or j == n-1:
                continue
            if cell[i][j] == 1:
                cell_list.append((i,j)) # 셀의 위치를 리스트에 담음 (벽에 있는 셀 제외)

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    maxCnt = 0
    minLength = 999

    connect(0, 0, 0)

    print("#{} {}".format(tc, minLength))