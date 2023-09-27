# 벌꿀 채취
import sys
input = sys.stdin.readline

T = int(input())

def getHoney(r, c, cnt, sum, profit):

    if sum > C: # 채취한 벌꿀의 양이 C보다 크면 RETURN
        return
    if cnt == M: # 벌통 M개 선택했으면
        if memo[r][c] < profit: # 이익 계산하고
            memo[r][c] = profit # 이익 갱신
        return
    temp = honey[r][c+cnt]
    getHoney(r, c, cnt+1, sum+temp, profit+temp*temp)
    getHoney(r, c, cnt+1, sum, profit)


for tc in range(1,T+1):
    honey = []  # 벌통
    # N: 벌통의 크기
    # M: 선택할 수 있는 벌통의 개수
    # C: 각 일꾼이 채취할 수 있는 최대 벌꿀 양
    N, M, C = list(map(int, input().split()))
    honey = [list(map(int, input().split())) for _ in range(N)]

    memo = [[0 for _ in range(N-M+1)] for _ in range(N)]

    for r in range(N):
        for c in range(N-M+1):
            getHoney(r, c, 0, 0, 0)


    # 일꾼 1의 이익
    max1 = 0
    r1, c1 = -1, -1
    for r in range(N):
        for c in range(N-M+1):
            if max1 < memo[r][c]:
                max1 = memo[r][c]
                r1 = r
                c1 = c

    # 일꾼 2의 이익
    max2 = 0
    for r in range(N):
        for c in range(N-M+1):
            if r == r1 and ((c <= c1 <= c+M-1) or (c <= c1+M-1 <= C+M-1)):
                continue
            if max2 < memo[r][c]:
                max2 = memo[r][c]

    print("#{} {}".format(tc, max1+max2))