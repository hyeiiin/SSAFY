from collections import deque
import copy
import sys

input = sys.stdin.readline

#바이러스는 상하좌우로 인접한 빈칸으로 퍼질 수 있음
#세울수있는 벽의 수: 3
#0: 빈칸 1: 벽 2: 바이러스
#안전 영역 크기의 최댓값?
N, M = map(int,input().split())
#delta = [[-1,0],[1,0],[0,-1],[0,1]] #상하좌우
dx = [0,0,1,-1]
dy = [1,-1,0,0]
# 바이러스를 탐색하고, 그 주위(상하좌우)로 벽이 있는지 체크
def virus():
    que = deque()
    tmp = copy.deepcopy(map)

    for i in range(N):
        for j in range(M):
            #바이러스를 찾았다면 큐에 바이러스 인덱스 저장
            if tmp[i][j]==2:
                que.append((i,j))

    while que:
        #앞 바이러스의 좌표 가져오기
        r, c = que.popleft()

        for i in range(4):
            nr = r + dx[i]
            nc = c + dy[i]
            #바이러스 주변 탐색시 범위를 벗어난 경우
            if nr<0 or nr>=N or nc<0 or nc>=M:
                continue
            if tmp[nr][nc]==0: #빈 공간이라면 바이러스 주입
                tmp[nr][nc]=2
                que.append((nr,nc))

    global res
    safe_cnt = 0
    for i in range(N):
        safe_cnt += tmp[i].count(0)

    res = max(res, safe_cnt)

def build(wall_cnt):

    #벽을 다 지었다면 바이러스 터트리기
    if wall_cnt==3:
        virus()
        return

    #벽 생성
    for i in range(N):
        for j in range(M):
            # 공간이 있다면 벽 짓고 다음 벽 짓기
            if map[i][j]==0:

                map[i][j]=1
                build(wall_cnt+1)
                map[i][j]=0

map = [list(map(int,input().split())) for _ in range(N)]

res = 0
build(0)

print(res)
