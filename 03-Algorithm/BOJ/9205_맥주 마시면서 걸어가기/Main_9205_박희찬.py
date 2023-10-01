from sys import stdin
from collections import deque
from pprint import pprint

### BOJ_9205_맥주 마시면서 걸어가기


# 맨해튼 거리 = |x1 - x2| + |y1 - y2|
def manhattan(x1, y1, x2, y2):
    return abs(x1 - x2) + abs(y1 - y2)


def bfs(x, y):
    global store, ex, ey, res

    qu = deque()
    qu.append([x, y, 20])  # 초기 위치, 맥주 개수
    visited = [False for _ in range(N)]  # 편의점 방문 처리

    while qu:
        x, y, beer = qu.popleft()

        # 편의점 확인
        for st in range(N):
            if visited[st]:  # 방문한 편의점은 가지 않음
                continue

            # 가는데 소비 할 맥주 개수 = 맨해튼거리 / 50m
            cal = manhattan(x, y, store[st][0], store[st][1]) / 50
            if beer - cal < 0:  # 갈 수 없음
                continue
                
            else:  # 갈 수 있음(0 이어도 딱 도착함)
                visited[st] = True  # 편의점 방문 처리
                beer = 20  # 맥주 리필
                qu.append([store[st][0], store[st][1], beer])

        # END : 현재 위치에서 도착점 까지 맥주 0병 이상으로 갈 수 있는 경우
        if 0 <= beer - (manhattan(x, y, ex, ey) / 50):
            res = "happy"  # 도착 했으므로 최종 결괏값 갱신 후 반환
            return

    res = "sad"  # BFS()가 끝나면 갈 수 없으므로 최종 결괏값 갱신


# Main #
TC = int(stdin.readline())

for tc in range(1, TC + 1):
    N = int(stdin.readline())

    # 출발 위치
    sx, sy = map(int, stdin.readline().split())

    # 편의점 위치
    store = [list(map(int, stdin.readline().split())) for _ in range(N)]

    # 도착 위치
    ex, ey = map(int, stdin.readline().split())

    res = "sad"
    bfs(sx, sy)
    print(res)
