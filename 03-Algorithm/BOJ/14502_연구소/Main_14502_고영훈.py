from itertools import combnations
from sys import stdin


def main():
    input = stdin.readline
    N = input()
    T = N // 2
    mat = tuple(tuple(map(int, input().split())) for _ in range(N))
    pool = set(range(N))
    answer = 100_000
    for a_team in combnations(pool, T):
        b_team = tuple(pool - set(a_team))
        a = sum(mat[i][j] + mat[j][i] for i, j in combnations(a_team, 2))
        b = sum(mat[i][j] + mat[j][i] for i, j in combnations(b_team, 2))
        answer = max(answer, abs(a - b))
    print(answer)


main()