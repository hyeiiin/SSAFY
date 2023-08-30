import sys
from collections import deque
N = int(sys.stdin.readline())

arr = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

# 파이프 연결이 가로로 됬을 경우 / 세로 / 대각선인 경우 각각을 기억해야함. 즉, arr가 3개가 필요함.

# arr_row = [[0 for _ in range(N)] for _ in range(N)]
# arr_col = [[0 for _ in range(N)] for _ in range(N)]
# arr_dia = [[0 for _ in range(N)] for _ in range(N)]

# dp로 풀거면 이런식으로.
# 즉, arr[i][j] = arr_row[i][j-1] + arr_col[i-1][j] + arr_dia[i-1][j-1]
# 추가로, 가로나 세로는 그냥 그 자리만 빈자리면 되는데, 대각은 그 지점 + 위쪽 + 왼쪽도 빈칸이어야함.




# bfs, dfs로 먼저 풀어보기

# bfs =============시간초과 >> dfs로.
# while queue:
#     x, y, z = queue.popleft()
#     if z == 0:
#         # 가로
#         if y+1 < N and arr[x][y+1] != 1:
#             queue.append((x, y+1, 0))
#             arr_pipe[x][y+1] += 1
#         if x+1 < N and y+1 < N and arr[x+1][y+1] != 1 and arr[x][y+1] != 1 and arr[x+1][y] != 1:
#             queue.append((x+1, y+1, 2))
#             arr_pipe[x+1][y+1] += 1
#     elif z == 1:
#         # 세로
#         if x+1 < N and arr[x+1][y] != 1:
#             queue.append((x+1, y, 1))
#             arr_pipe[x+1][y] += 1
#         if x+1 < N and y+1 < N and arr[x+1][y+1] != 1 and arr[x][y+1] != 1 and arr[x+1][y] != 1:
#             queue.append((x+1, y+1, 2))
#             arr_pipe[x+1][y+1] += 1
#     elif z == 2:
#
#         # 대각
#         if y + 1 < N and arr[x][y + 1] != 1:
#             queue.append((x, y + 1, 0))
#             arr_pipe[x][y + 1] += 1
#
#         if x+1 < N and arr[x+1][y] != 1:
#             queue.append((x+1, y, 1))
#             arr_pipe[x+1][y] += 1
#
#         if x+1 < N and y+1 < N and arr[x+1][y+1] != 1 and arr[x][y+1] != 1 and arr[x+1][y] != 1:
#             queue.append((x+1, y+1, 2))
#             arr_pipe[x+1][y+1] += 1


# 그냥 dfs도 터짐. bfs나 dfs에 추가적으로 적용해서 시간을 줄여야할듯.
#     >> python3 로 제출하면 초과나고 pypy3로 제출하면 통과됨.
#         >> python3는 단순코드, pypy3는 반복이 많은 코드에서 유리하다고함.
# 방문배열 추가?
answer = 0

def dfs(x, y, z):
    if x == N-1 and y == N-1:
        global answer
        answer += 1
        return

    if z == 0:
        # 가로
        if y + 1 < N and arr[x][y + 1] != 1:
            dfs(x, y+1, 0)
        if x + 1 < N and y + 1 < N and arr[x + 1][y + 1] != 1 and arr[x][y + 1] != 1 and arr[x + 1][y] != 1:
            dfs(x+1, y+1, 2)

    elif z == 1:
        # 세로
        if x+1 < N and arr[x+1][y] != 1:
            dfs(x+1, y, 1)
        if x+1 < N and y+1 < N and arr[x+1][y+1] != 1 and arr[x][y+1] != 1 and arr[x+1][y] != 1:
            dfs(x+1, y+1, 2)
    elif z == 2:

        # 대각
        if y + 1 < N and arr[x][y + 1] != 1:
            dfs(x, y+1, 0)

        if x+1 < N and arr[x+1][y] != 1:
            dfs(x+1, y, 1)

        if x+1 < N and y+1 < N and arr[x+1][y+1] != 1 and arr[x][y+1] != 1 and arr[x+1][y] != 1:
            dfs(x+1, y+1, 2)



dfs(0, 1, 0)
print(answer)