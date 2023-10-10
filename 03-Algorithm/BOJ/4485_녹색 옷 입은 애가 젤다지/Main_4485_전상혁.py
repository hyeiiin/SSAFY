import sys
from collections import deque
input = sys.stdin.readline

def escape():
    #도둑루피 값 누적할 배열 생성
    rupee_sums = [[-1] * N for _ in range(N)]
    #링크의 시작 위치의 도둑루피 값 넣기
    rupee_sums[0][0] = rupee[0][0]

    q = deque([(0,0)])

    while q:
        r, c = q.popleft()

        for x, y in delta:
            nr = r + x
            nc = c + y

            if not (0<= nr < N and 0<= nc < N):
                continue
            #이동하려는 칸이 방문하지 않은 경우
            if rupee_sums[nr][nc]== -1:
                #그 칸의 루피 값 누적시켜 주고 위치 넣기
                rupee_sums[nr][nc] = rupee_sums[r][c] + rupee[nr][nc]
                q.append((nr, nc))

            #이동하려는 칸을 이미 방문해서 루피가 누적되었을 경우
            elif rupee_sums[nr][nc] > -1:
                #누적된 루피값이 현재 위치 루피 + 이동하려는 위치 루피 값 보다 크다면
                if rupee_sums[nr][nc] > rupee_sums[r][c] + rupee[nr][nc]:
                    #최솟값을 구해야 하므로 작은 값을 갱신시킴
                    rupee_sums[nr][nc] = rupee_sums[r][c] + rupee[nr][nc]

                    q.append((nr, nc))


    return rupee_sums[N-1][N-1]



tc = 0

while True:
    #동굴의 크기
    N = int(input())

    #N=0이면 종료
    if N==0:
        break

    rupee = [list(map(int,input().split())) for _ in range(N)]
    # 도둑 루피 크기가 k : 이 칸을 지날 경우 k루피를 잃는다. 0~9
    delta = [[0,1],[1,0],[-1,0],[0,-1]]


    tc += 1
    print("Problem {}: {}".format(tc, escape()))



