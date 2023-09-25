import sys
import copy
from collections import deque


N, M = map(int, sys.stdin.readline().split())

arr = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
arr_origin = copy.deepcopy(arr)

d = [[-1, 0], [0, 1], [1, 0], [0, -1]]


result = -1

# 벽을 세우는 경우의 수.

def create_wall(cnt):
    # 벽이 3개될때까지.
    if cnt == 3:
        bfs()
        return

    for i in range(N):
        for j in range(M):
            if arr[i][j] == 0:
                arr[i][j] = 1
                create_wall(cnt+1)
                arr[i][j] = 0

# bfs.
def bfs():

    queue = deque()
    temp_arr = copy.deepcopy(arr)
    #일단 시작할때 바이러스 위치를 전부 큐에넣고,
    for i in range(N):
        for j in range(M):
            if temp_arr[i][j] == 2:
                queue.append((i, j))

    #큐가 빌때까지 bfs 진행.
    while queue:
        x, y = queue.popleft()

        for i in range(4):
            nx = x + d[i][0]
            ny = y + d[i][1]

            # 큐에서 꺼낸놈 4방향으로 보고 그 자리가 0인 경우만 큐에 넣고, 방문의 의미로 2 할당.
            if 0 <= nx < N and 0 <= ny < M:
                if temp_arr[nx][ny] == 0:
                    temp_arr[nx][ny] = 2
                    queue.append((nx, ny))

    # 다 끝내고 0 개수 세기
    global result
    count_0 = 0
    for i in range(N):
        for j in range(M):
            if temp_arr[i][j] == 0:
                count_0 += 1

    result = max(result, count_0)


create_wall(0)
print(result)
