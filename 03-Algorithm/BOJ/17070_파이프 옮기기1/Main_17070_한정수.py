import sys
from collections import deque
N = int(sys.stdin.readline())

arr = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

#    Python3    pypy3
# dp    68ms     144ms
# dfs  시간초과   872ms


# 추가로, 가로나 세로는 그냥 그 자리만 빈자리면 되는데, 대각은 그 지점 + 위쪽 + 왼쪽도 빈칸이어야함.

# 3차원 배열 하나 만들어서 i,j 위치에 파이프의 방향별로 따로 저장하게. >> 파이프 개수를 한번에 관리.
arr_pipe = [[[0 for _ in range(3)] for _ in range(N)] for _ in range(N)]
# z:0 >> 가로  z:1 >> 세로  z:2 >> 대각
arr_pipe[0][1][0] = 1
arr_pipe[0][1][1] = 0
arr_pipe[0][1][2] = 0

# arr[i][j][가로] = arr[i][j -1][가로] + arr[i][j -1][대각]
# arr[i][j][세로] = arr[i -1][j][세로] + arr[i -1][j][대각]
# arr[i][j][대각] = arr[i -1][j -1][가로] + arr[i -1][j -1][세로] + arr[i -1][j -1][대각]

for i in range(N):
    for j in range(N):
        # 현재 위치에 장애물이 있으면 넘기고.
        if arr[i][j] == 1:
            continue


        if j-1 >= 0:
            arr_pipe[i][j][0] = arr_pipe[i][j-1][0] + arr_pipe[i][j-1][2]

        if i-1 >= 0:
            arr_pipe[i][j][1] = arr_pipe[i-1][j][1] + arr_pipe[i-1][j][2]

        if i-1 >= 0 and j-1 >= 0:
            # 대각선은 추가로 현위치 왼쪽과 위를 검사해야함.
            if arr[i-1][j] != 1 and arr[i][j-1] != 1:
                arr_pipe[i][j][2] = arr_pipe[i-1][j-1][0] + arr_pipe[i-1][j-1][1] + arr_pipe[i-1][j-1][2]

print(arr_pipe[N-1][N-1][0] + arr_pipe[N-1][N-1][1] + arr_pipe[N-1][N-1][2] )
#========================dp 끝==================================================


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
# answer = 0
#
# def dfs(x, y, z):
#     if x == N-1 and y == N-1:
#         global answer
#         answer += 1
#         return
#
#     if z == 0:
#         # 가로
#         if y + 1 < N and arr[x][y + 1] != 1:
#             dfs(x, y+1, 0)
#         if x + 1 < N and y + 1 < N and arr[x + 1][y + 1] != 1 and arr[x][y + 1] != 1 and arr[x + 1][y] != 1:
#             dfs(x+1, y+1, 2)
#
#     elif z == 1:
#         # 세로
#         if x+1 < N and arr[x+1][y] != 1:
#             dfs(x+1, y, 1)
#         if x+1 < N and y+1 < N and arr[x+1][y+1] != 1 and arr[x][y+1] != 1 and arr[x+1][y] != 1:
#             dfs(x+1, y+1, 2)
#     elif z == 2:
#
#         # 대각
#         if y + 1 < N and arr[x][y + 1] != 1:
#             dfs(x, y+1, 0)
#
#         if x+1 < N and arr[x+1][y] != 1:
#             dfs(x+1, y, 1)
#
#         if x+1 < N and y+1 < N and arr[x+1][y+1] != 1 and arr[x][y+1] != 1 and arr[x+1][y] != 1:
#             dfs(x+1, y+1, 2)
#
#
#
# dfs(0, 1, 0)
# print(answer)
