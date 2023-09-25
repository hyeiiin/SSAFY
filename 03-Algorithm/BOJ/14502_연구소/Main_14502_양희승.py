# 벽을 세운다 (3개) (벽은 1로 표시)
    # 완탐 -> 벽세울 수 있는 좌표
    # 좌표 조합 (3)
# 바이러스를 퍼뜨린다 (바이러스는 2로 표시)
    # BFS
# 안전영역을 카운트한다 (0의 개수)
    # 걍 세기

# 세로크기(N), 가로크기(M)

from itertools import combinations
from collections import deque

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(graph_copy, x, y):
    queue = deque([(x, y)])
    while queue:
        x, y = queue.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < n and 0 <= ny < m and graph_copy[nx][ny] == 0:
                graph_copy[nx][ny] = 2
                queue.append((nx, ny))

    return graph_copy

n, m = map(int, input().split())

graph = [list(map(int, input().split())) for _ in range(n)]

wall = [(i, j) for i in range(n) for j in range(m) if graph[i][j] == 0]

maxVal = 0
for comb in combinations(wall, 3):
    graph_copy = [[0 for _ in range(m)] for _ in range(n)]
    for i in range(n):
        for j in range(m):
            graph_copy[i][j] = graph[i][j]

    # 벽을 세운다
    for x, y in comb:
        graph_copy[x][y] = 1

    # 바이러스를 퍼뜨린다
    for i in range(n):
        for j in range(m):
            if graph_copy[i][j] == 2:
                graph_copy = bfs(graph_copy, i, j)

    count = 0
    for i in graph_copy:
        count += i.count(0)
    maxVal = max(maxVal, count)

print(maxVal)
