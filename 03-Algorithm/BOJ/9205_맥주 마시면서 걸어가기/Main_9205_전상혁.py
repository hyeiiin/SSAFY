import sys
from collections import deque
input = sys.stdin.readline


t = int(input())
for tc in range(t):
    # 편의점 개수
    n = int(input())
    #상근이네 집
    start_x, start_y = map(int,input().split())
    songdo = []

    for _ in range(n):
        # 편의점
        x, y = map(int,input().split())
        songdo.append([x,y])

    #페스티벌 좌표
    finish_x, finish_y = map(int,input().split())
    songdo.append([finish_x, finish_y])

    # print(songdo)
    #맥주병 수용 개수: 20, 50미터 안 한병씩 드링크, 즉 각 거리는 1000을 넘을 수 없음
    visited = [False]*(n+1)

    def bfs():
        q = deque()
        q.append((start_x, start_y))

        while q:
            x, y = q.popleft()
            #현 위치에서 페스티벌까지 거리가 1000이하면 행복해질 수 있음
            if abs(x-finish_x) + abs(y-finish_y) <= 1000:
                print('happy')
                return
            #바로 갈 수 없는 거리인 경우 편의점 탐색

            for i in range(n):
                if visited[i] == False:
                    #방문하지 않은 편의점 좌표를 가져와서 거리를 잼
                    nx, ny = songdo[i]
                    #그 거리가 1000이하면 큐에 삽입 후 방문처리
                    if abs(x-nx) + abs(y-ny) <= 1000:
                        q.append((nx,ny))
                        visited[i] = True

        # 편의점 위치도 1000보다 큰 경우 갈 수 없으므로 종료
        print('sad')
        return

    bfs()
