from collections import deque
import heapq


N, M = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]

# for i in arr:
#     print(i)

# 먼저 각 섬 >> 섬 으로의 최솟값을 전부 구해서 인접 행렬로 구한다.
# 인접 행렬을 이용해서 최소 신장트리를 prims 알고리즘 써서 가중치의 합(다리의 총 길이)을 구한다.

# 일단 섬이 뭐뭐 있는지 찾는다.
# 각 섬별로 1, 2, 3, 4, 5, 6 으로 구분할 수 있게 바꿔줌.

dx = [-1, 0, 1, 0]  # 상 우 하 좌
dy = [0, 1, 0, -1]
visited = [[False for _ in range(M)] for _ in range(N)]

island_cnt = 1
for i in range(N):
    for j in range(M):
        if arr[i][j] != 0 and not visited[i][j] :
            queue = deque()
            queue.append((i, j))
            arr[i][j] = island_cnt
            visited[i][j] = True
            while queue:
                x, y = queue.popleft()
                for idx in range(4):
                    nx = x + dx[idx]
                    ny = y + dy[idx]
                    if 0 <= nx < N and 0 <= ny < M:
                        if not visited[nx][ny] and arr[nx][ny] != 0:
                            visited[nx][ny] = True
                            queue.append((nx, ny))
                            arr[nx][ny] = island_cnt
            island_cnt += 1


# 각 섬이 어떤 좌표로 이루어졌는지 찾았고,
# 각 섬 to 섬으로의 최소 거리를 찾는다.

# 각 섬과 섬 사이의 거리를 전부 가져와서 등록함. 중복은 제거될 수 있도록 set으로.
# (거리, 시작, 끝)
edge_all = set()

for i in range(N):
    for j in range(M):
        if arr[i][j] == 0:
            continue

        temp = arr[i][j]
        # 각 i,j에서 상하좌우 한칸씩 싹 돌려서 i to j 가능한 edge를 싹 구한다. 최솟값 신경쓰지말고 전부.
        for idx in range(4):
            cnt = 0
            ni = i
            nj = j
            while True:
                ni = ni + dx[idx]
                nj = nj + dy[idx]

                if 0 <= ni < N and 0 <= nj < M:
                    if arr[ni][nj] == 0:
                        cnt += 1
                        continue # 0이니까 해당방향으로 계속 탐색
                    elif arr[ni][nj] == temp:
                        break # while break
                    elif arr[ni][nj] != temp:
                        if cnt <= 1:
                            break # 길이가 1보다 커야하니까.
                        else:
                            # (길이, 시작, 끝)
                            edge_all.add((cnt, temp, arr[ni][nj]))
                            break

                else:
                    # 배열 밖으로 나갔으면 while break
                    break


# 각 섬과 섬 사이의 간선을 토대로 인접 행렬을 만듬.
# 이때, 일단 최초로 등록하고, 똑같은 (시작,끝)에 대해서 다른 거리 값이 주어졌을 경우, 그 중 작은놈으로 넣는다.
# ex)  (3, 1, 3) (5, 1, 3)이 주어졌을 경우, graph[1][3] = 3이어야함.
# 크루스칼이면 몰라도 prim 쓸거면 이거 해줘야함.

graph = [[0 for _ in range(island_cnt)] for _ in range(island_cnt)]
for dist, x, y in edge_all:
    if graph[x][y] != 0:
        graph[x][y] = min(dist, graph[x][y])
    else:
        graph[x][y] = dist

# prim 알고리즘 사용을 위해 heap 사용.
heap = []

# 1번섬을 시작으로 잡고, 1번섬과 연결된 애들을 일단 heap에 다 저장
for i in range(island_cnt):
    if graph[1][i] != 0:
        heapq.heappush(heap, (graph[1][i], 1, i))

if not heap:
    # 1번 섬에 연결된게 아무것도 없다면 애초에 전체 섬을 연결하는게 불가능하단 뜻이므로 -1 출력
    print(-1)
else:
    # prim 실행
    # 방문배열 생성 및 최초 방문 처리
    visited_prim = [False for _ in range(island_cnt)]
    visited_prim[0] = True # 0번섬은 애초에 없고
    visited_prim[1] = True # 1번섬 시작으로 잡았으니까 방문처리
    sum_dist = 0

    edge_cnt = 0
    while heap:
        if edge_cnt == island_cnt -2:
            # 간선 개수가 전체 섬 개수 - 1이 되면 다 연결된거니까 종료
            break
        dist, start, end = heapq.heappop(heap)
        if not visited_prim[end]:
            edge_cnt += 1
            visited_prim[end] = True
            sum_dist += dist
            for i in range(island_cnt):
                if graph[end][i] > 0 and not visited_prim[i]:
                    heapq.heappush(heap, (graph[end][i], end, i))

    if False in visited_prim:
        # 혹시 모르니까 체크
        print(-1)
    else:
        print(sum_dist)

