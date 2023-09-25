import copy
import sys
input = sys.stdin.readline
from collections import deque

n, m = map(int, input().split())
graph = []
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

# 바이러스 탐색하고 감염시키기, 안전 구역 구하기
def bfs() :
    queue = deque()
    tmp_graph = copy.deepcopy(graph)

    # 큐에 바이러스 담기
    for i in range(n):
        for j in range(m):
            if tmp_graph[i][j] == 2:
                queue.append((i, j))

    while queue:
        x, y = queue.popleft()

        # 사방탐색 -> 감염 시키기
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            if tmp_graph[nx][ny] == 0: # 감염 가능 여부 확인
                tmp_graph[nx][ny] = 2
                queue.append((nx, ny))

    global ans
    safe = 0

    for i in range(n):
        safe += tmp_graph[i].count(0)

    ans = max(ans, safe)

# 벽 세우기
def backTracking(cnt):
    if cnt == 3: # 벽이 3개 다 만들어지면 탐색
        bfs()
        return

    for i in range(n):
        for j in range(m):
            if graph[i][j] == 0: #빈 공간인지 확인
                graph[i][j] = 1 # 벽 세우기
                backTracking(cnt+1)
                graph[i][j] = 0 # 재귀 끝나면 해당 위치 벽 허물기

for i in range(n):
    graph.append(list(map(int, input().split())))

ans = 0
backTracking(0)
print(ans)