""" BOJ_4485_ 녹색 옷 입은 애가 젤다지?"""
from sys import stdin
from collections import deque


def bfs(xx, yy):
    global res

    qu = deque()
    qu.append([xx, yy])
    dist[xx][yy] = lst[xx][yy]

    while qu:
        x, y = qu.popleft()

        for k in range(4):
            nx = x + dx[k]
            ny = y + dy[k]

            if 0 <= nx < N and 0 <= ny < N:
                # 현재까지 경로 + 다음 위치의 값 = 다음까지의 경로 값
                nxt = dist[x][y] + lst[nx][ny]
                # print(f'{x, y} -> {nx, ny} // {nxt, dist[nx][ny]} ')
                
                # 현재위치에서의 경로값 < 다음 위치의 경로값
                if nxt < dist[nx][ny]:
                    # 위 IF의 경우에만 거리 갱신 및 다음 경로 추가
                    dist[nx][ny] = nxt
                    qu.append([nx, ny])

    res = min(res, dist[N-1][N-1])
    
    # TC-2에서 (0, 2)좌표를 보면,
    # 1. 출발점에서 오른쪽으로 쭉 간경우 => 12(= 거리)
    # 2. (2, 0)까지 내려갔다가 -> (2, 2)까지 오른쪽 갔다가 올라가는 U자형 => 11
    # 위 2가지 경우처럼 돌아가는게 더 최솟값이 나올 수 있으므로
    # 방문 배열로 검사하지말고, 이동 시 이미 dist값이 있든 말든
    # 현재 경로가 더 최소일 경우만 다음 경로로 큐에 넣으면서 진행해야함
    # 일반적인 BFS()로는 위 케이스 때문에 올바른 결괏값이 나오지 않음!


""" Main """
tc = 1  # TestCase
while 1:
    N = int(stdin.readline().strip())
    if not N:  # 입력으로 0이 들어오면 종료
        break
    lst = [list(map(int, stdin.readline().split())) for _ in range(N)]  # 원본 맵
    dist = [[float('inf')] * N for _ in range(N)]  # 거리 맵

    res = float('inf')  # 최종 출력값
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    bfs(0, 0)

    print(f'Problem {tc}: {res}')
    tc += 1
