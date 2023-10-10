import sys
input = sys.stdin.readline
import copy

def go(t):

    cleaner_r = []
    res = 0

    for i in range(R): #공기청정기 행 위치 찾기
        if A[i][0] == -1:
            cleaner_r.append(i)

    arr = copy.deepcopy(A)
    for _ in range(t):
        #1. 미세먼지 확산
        arr = spread(arr,cleaner_r)

        #2. 공기청정기 가동
        #공기청정기의 행 위치 두개를 기준으로 두 배열로 나누어 순환
        # 위: 반시계 방향 순환
        up_cleaner(arr,cleaner_r[0])
        #아래: 시계방향 순환
        down_cleaner(arr,cleaner_r[1])

    for i in range(R):
        for j in range(C):
            if arr[i][j] >= 0:
                res += arr[i][j]

    return res


def spread(arr,cleaner_r): #미세먼지 확산

    delta = [[1,0],[0,1],[0,-1],[-1,0]]

    spread_A= [[0]*C for _ in range(R)]
    #공기청정기 위치 표시
    spread_A[cleaner_r[0]][0] = -1
    spread_A[cleaner_r[1]][0] = -1

    for r in range(R):
        for c in range(C):

            if arr[r][c] > 0:

                d_cnt = 0  # 확산된 방향의 개수
                for dx, dy in delta:
                    nr = r + dx
                    nc = c + dy

                    #공기청정기가 없고 칸이 있어야 확산이 일어남
                    if 0<=nr<R and 0<=nc<C and arr[nr][nc]>=0:
                        spread_A[nr][nc] += arr[r][c]//5
                        d_cnt += 1

                #확산시킨 후 d_cnt만큼 남은 미세먼지 양 줄이기
                spread_A[r][c] += arr[r][c] - (arr[r][c]//5)*d_cnt

    return spread_A

def up_cleaner(arr,row):

    updel = [[0,1],[1,0],[0,-1],[-1,0]]

    r = 0
    c = 0
    d = 0
    tmp = arr[r][c]

    while d<4:
        nr = r + updel[d][0]
        nc = c + updel[d][1]

        if 0 <= nr <= row and 0 <= nc < C:
            if arr[nr][nc] == -1:  # 옮기려는 위치에 공기청정기가 있다면, 위치 변환 없이 패스
                arr[r][c] = 0
                r = nr
                c = nc
                d += 1
                continue

            if nr == row - 1 and nc==0:  # 공기청정기로 들어갈 미세먼지
                arr[nr][nc] = 0
                r = nr
                c = nc
                continue

            arr[r][c] = arr[nr][nc]
            r = nr
            c = nc
        else:
            d += 1

    arr[r+1][c] = tmp


def down_cleaner(arr,row):
    downdel = [[1, 0], [0, 1], [-1, 0], [0, -1]]

    r = row
    c = 0
    d = 0
    tmp = arr[r][c]

    while d < 4:
        nr = r + downdel[d][0]
        nc = c + downdel[d][1]

        if row <= nr < R and 0 <= nc < C:
            if arr[nr][nc] == -1:  # 옮기려는 위치에 공기청정기가 있다면, 위치 변환 없이 패스
                r = nr
                c = nc
                d += 1
                continue

            if nr == row + 1 and nc == 0:  # 공기청정기로 들어갈 미세먼지
                arr[nr][nc] = 0
                r = nr
                c = nc
                continue

            arr[r][c] = arr[nr][nc]
            r = nr
            c = nc
        else:

            d += 1

    arr[r][c+1] = 0


#R*C, T=초
R, C, T = map(int,input().split())

A = [list(map(int,input().split())) for _ in range(R)]
#공기청정기 설치된 곳 = A[r][c] = -1, 나머지 값은 미세먼지의 양
#공기청정기는 윗행, 아랫행과 두칸 이상 떨어져있음

print(go(T))