from sys import stdin
from collections import deque
from pprint import pprint


# 문을 열 수 있는지 조사
def openIt(keys, door):
    # 문을 비트마스킹으로 매칭되는 열쇠 위치에 가지고 있는지 AND 연산
    check = keys & (1 << (ord(door) - 65))
    return True if check else False


# BFS 순회
def bfs(x, y):
    # 열쇠(소문자) : 97 ~ 102 / 문(대문자) : 65 ~ 70
    qu = deque()

    # 방문 배열을 3차원으로 만듦.
    # (1 << 6) = 열쇠 개수
    visited = [[[0] * (1 << 6) for _ in range(M)] for _ in range(N)]
    qu.append([x, y, 0, 0])
    visited[x][y][0] = 1

    while qu:
        # 현재 좌표, 열쇠 현황, 거리 체크
        x, y, key, cnt = qu.popleft()

        # END : 출구를 찾았다면 cnt 반환
        if lst[x][y] == '1':
            return cnt

        for k in range(4):
            nx = x + dx[k]
            ny = y + dy[k]

            # 갈 수 있는 범위라면,
            if 0 <= nx < N and 0 <= ny < M:
                # 방문 하지 않은 곳이라면,
                if not visited[nx][ny][key]:
                    nxt = ord(lst[nx][ny])  # 아스키 코드 대조를 위해 변환

                    # 1. 길 또는 출구라면 거리 증감
                    if lst[nx][ny] == '.' or lst[nx][ny] == '1':
                        visited[nx][ny][key] = 1
                        qu.append([nx, ny, key, cnt + 1])

                    # 2. 열쇠를 찾았다면
                    elif 97 <= nxt <= 102:
                        # 기존 열쇠와 찾은 열쇠를 OR 연산
                        # 열쇠는 2개 이상 의미 없으므로 찾았어도 여전히 1로 됨.
                        newKeys = key | (1 << (nxt - 97))
                        # print(nx, ny, newKeys)

                        # 방문 체크 후, 갱신된 열쇠 묶음을 큐로 넘김
                        visited[nx][ny][newKeys] = 1
                        qu.append([nx, ny, newKeys, cnt + 1])

                    # 3. 문을 찾았다면
                    elif 65 <= nxt <= 70:
                        # 열쇠-문 대조 후 결과에 따라 큐에 넘길지 말지 정함.
                        if openIt(key, lst[nx][ny]):
                            visited[nx][ny][key] = 1
                            qu.append([nx, ny, key, cnt + 1])

    return -1


N, M = map(int, stdin.readline().split())

lst = [list(stdin.readline().strip()) for _ in range(N)]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

for n in range(N):
    for m in range(M):
        if lst[n][m] == '0':
            lst[n][m] = '.'
            print(bfs(n, m))
            break

# check = [[[0] * (1 << 6) for _ in range(1)] for _ in range(7)]
# pprint(check, width=40)

