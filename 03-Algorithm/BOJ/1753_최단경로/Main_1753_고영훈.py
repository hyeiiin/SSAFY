from heapq import heappop, heappush
from sys import stdin


def main():
    input = stdin.readline
    INF = 156250

    def dijkstra():
        q = [(mat[0][0], 0, 0)]
        loss_mat = [[INF] * N for _ in range(N)]
        while q:
            loss, y, x = heappop(q)
            if loss > loss_mat[y][x]:
                continue
            for dy, dx in ((-1, 0), (1, 0), (0, -1), (0, 1)):
                ny, nx = y + dy, x + dx
                if 0 <= ny < N and 0 <= nx < N:
                    new_loss = loss + mat[ny][nx]
                    if new_loss < loss_mat[ny][nx]:
                        loss_mat[ny][nx] = new_loss
                        heappush(q, (new_loss, ny, nx))
        return loss_mat[N - 1][N - 1]

    problem = 0
    while N := int(input()):
        problem += 1
        mat = [list(map(int, input()[::2])) for _ in range(N)]
        print(f'Problem {problem}: {dijkstra()}')


main()
