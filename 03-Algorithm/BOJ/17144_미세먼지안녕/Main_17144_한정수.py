#python3 제출시 시간초과, pypy3 제출시 정답
from collections import deque

N, M, T = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]

# 일단 공기청정기 위치를 찾고
upper_cleaner = 0
down_cleaner = 0
for i in range(N):
    if arr[i][0] == -1:
        upper_cleaner = i
        down_cleaner = i+1
        break

# 먼지 확산
queue = deque()
# 각 큐에서 꺼낸 먼지를 확산시켜준다.
dx = [-1, 0, 1, 0] # 상 우 하 좌
dy = [0, 1, 0, -1]

for t in range(T):
    # 먼지가 4이하면 확산이 안일어남.
    # 따라서 5 이상인 먼지만 골라서 큐에 넣고,
    for i in range(N):
        for j in range(M):
            if arr[i][j] >= 5:
                queue.append((i, j, arr[i][j]))

    while queue:
        x, y, amount = queue.popleft()
        diffusion = amount // 5
        for idx in range(4):
            nx = x + dx[idx]
            ny = y + dy[idx]

            if 0 <= nx < N and 0 <= ny < M :
                if arr[nx][ny] != -1:
                    arr[x][y] -= diffusion
                    arr[nx][ny] += diffusion

    # 이제 공기청정기 위쪽은 반시계, 아래쪽은 시계로 회전시킨다
    # 위쪽
    # upper_cleaner,0 에서 0,0 까지
    for i in range(upper_cleaner, 0, -1):
        if i == upper_cleaner:
            continue
        arr[i][0] = arr[i-1][0]

    # 0,0에서 0,M-1까지
    for i in range(1, M):
        arr[0][i-1] = arr[0][i]

    # 0,M-1에서 upper_cleaner,M-1까지
    for i in range(1, upper_cleaner+1):
        arr[i-1][M-1] = arr[i][M-1]

    # (upper_cleaner, M-1) 에서 (upper_cleaner, 0)까지
    for i in range(M-1, 0, -1):
        arr[upper_cleaner][i] = arr[upper_cleaner][i-1]
        if i == 1:
            arr[upper_cleaner][i] = 0

    # 아래쪽
    # down_cleaner,0 부터 down_cleaner,N-1 까지
    for i in range(down_cleaner+1, N-1):
        arr[i][0] = arr[i+1][0]

    # N-1,0 부터 N-1,M-1까지
    for i in range(1, M):
        arr[N-1][i-1] = arr[N-1][i]

    # N-1,M-1부터 down_cleaner,M-1까지
    for i in range(N-1, down_cleaner, -1):
        arr[i][M-1] = arr[i-1][M-1]

    # down_cleaner,M-1 부터 down_cleaner,0 까지
    for i in range(M-1, 0, -1):
        arr[down_cleaner][i] = arr[down_cleaner][i-1]
        if i == 1:
            arr[down_cleaner][i] = 0

result = 0
for i in range(N):
    for j in range(M):
        if arr[i][j] > 0:
            result += arr[i][j]

print(result)