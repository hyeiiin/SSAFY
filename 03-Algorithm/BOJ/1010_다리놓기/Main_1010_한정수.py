import sys

T = int(sys.stdin.readline())
# 그냥 왼쪽이 N개, 오른쪽이 M개면 m C n 구하는 문제.
# n C r = n-1 C r-1 + n-1 C r
for i in range(T):
    N, M = map(int, sys.stdin.readline().split())
    # m C n
    C = [[0 for _ in range(N+1)] for _ in range(M+1)]

    for i in range(M+1):
        for j in range(min(i, N)+1):
            if j == 0 or i==j:
                C[i][j] = 1
            else:
                C[i][j] = C[i-1][j-1] + C[i-1][j]

    print(C[M][N])