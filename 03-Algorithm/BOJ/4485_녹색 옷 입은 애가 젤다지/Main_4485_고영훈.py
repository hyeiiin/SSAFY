from collections import deque
from sys import stdin


def main():
    input = stdin.readline
    INF = 156250

    def bfs():
        q = deque([(0, 0, mat[0][0])])
        loss_mat = [[INF] * N for _ in range(N)]
        while q:
            y, x, loss = q.popleft()
            for dy, dx in ((-1, 0), (1, 0), (0, -1), (0, 1)):
                ny, nx = y + dy, x + dx
                if 0 <= ny < N and 0 <= nx < N and loss < loss_mat[ny][nx]:
                    loss_mat[ny][nx] = loss
                    q.append((ny, nx, loss + mat[ny][nx]))
        return loss_mat[N - 1][N - 1] + mat[N - 1][N - 1]

    problem = 0
    while N := int(input()):
        problem += 1
        mat = [list(map(int, input()[::2])) for _ in range(N)]
        print(f'Problem {problem}: {bfs()}')


main()
