from collections import deque

N, M = map(int, input().split())
arr = [[] for _ in range(N)]
wall = ['A', 'B', 'C', 'D', 'E', 'F']
wall_key = ['a', 'b', 'c', 'd', 'e' ,'f']
# 민식이의 시작 위치
cur_x = 0
cur_y = 0
for i in range(N):
    input_temp = list(input().rstrip())
    if '0' in input_temp:
        cur_x = i
        cur_y = input_temp.index('0')
    arr[i] = input_temp

arr[cur_x][cur_y] = '.'

dx = [-1, 0, 1, 0]  # 상 우 하 좌
dy = [0, 1, 0, -1]


queue = deque()
# 민식이가 움직일때마다 움직였던 자리를 .으로 바꿈.
# 열쇠 a,b,c,d,e,f 를 먹으면 그 key에 넣고, 그 자리를 . 으로 대체.
# 열쇠를 먹었을때, 다음의 방법중 하나를 사용
#   >> 1. 열쇠를 먹은 즉시 방문배열을 초기화하고, 그 자리에서 다시 bfs를 시작함.
#         >>1-1. 문을 만날 경우 항상 key를 가지고있는지 검사하고 그 문을 없앰.
#                >> bfs 돌면서 queue에 넣을때 key보유 정보까지 넣어줘야함.
#                 >> 그럼 열쇠먹고 돌아가는건?
#                      >>visited에 지나갔던 순간의 key보유현황을 저장.
#         >>1-2. 애초에 키를 먹자마자 전체 맵에서 그 키에 해당하는 문을 .으로 바꿔버림.
#                >> 문제는 그 열쇠를 먹는게 최적해라고 장담할 수가 없음.


# 같은 열쇠가 여러개일 수 있다. 문도 마찬가지로 여러개일 수 있다.
# 문은 있는데 열쇠가 없을 수도 있다.
# 없음, a, b, c, d, e, f
visited = [[[False for _ in range(64)] for _ in range(M)] for _ in range(N)]

# 000000 비트마스킹 활용.
# 000000 = 키없음
# 100000 = f
# 010000 = e
# 001000 = d
# 000100 = c
# 000010 = b
# 000001 = a
def bfs(start_x, start_y):
    queue.append((start_x, start_y, 0))
    visited[start_x][start_y][0] = 0
    depth = 0
    while queue:
        depth += 1
        for i in range(len(queue)):
            x, y, origin_key = queue.popleft()
            # print("현재 위치 : ", x, y, origin_key)

            for n in range(4):
                nx = x + dx[n]
                ny = y + dy[n]
                key = origin_key
                if (0 <= nx < N and 0 <= ny < M) and not visited[nx][ny][key]:
                    if arr[nx][ny] == '1':
                        return depth
                    elif arr[nx][ny] != '#':
                        if arr[nx][ny] == '.':
                            visited[nx][ny][key] = True
                            queue.append((nx, ny, key))
                        else:
                            if 'A' <= arr[nx][ny] <= 'F':
                                # 벽이다.
                                # 열쇠를 가지고 있는가?
                                if (key & (1 << (ord(arr[nx][ny])-ord('A')))):
                                    visited[nx][ny][key] = True
                                    queue.append((nx, ny, key))

                                # if arr[nx][ny].lower() in key:
                                #     visited[nx][ny] = key
                                #     queue.append((nx, ny, key))

                            elif arr[nx][ny] in wall_key:
                                # 열쇠다.
                                key |= (1 << (ord(arr[nx][ny])- ord('a')))
                                visited[nx][ny][key] = True
                                queue.append((nx, ny, key))


result = bfs(cur_x, cur_y)
if result:
    print(result)
else:
    print(-1)
