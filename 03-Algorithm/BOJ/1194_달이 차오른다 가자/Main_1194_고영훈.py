from sys import stdin


def main():
    def bfs(mat, visited, y, x):
        count = 0
        q = [(y, x, 0)]
        while q:
            nq = []
            for y, x, keys in q:
                for dy, dx in ((-1, 0), (1, 0), (0, -1), (0, 1)):
                    ny, nx = y + dy, x + dx
                    if 0 <= ny < N and 0 <= nx < M and not visited[ny][nx][keys]:
                        c = mat[ny][nx]
                        if c == end:
                            return count + 1
                        if A <= c <= F and not keys & (1 << c - A):
                            continue
                        if a <= c <= f and not keys & (1 << c - a):
                            visited[ny][nx][keys | (1 << c - a)] = True
                            nq.append((ny, nx, keys | (1 << c - a)))
                        else:
                            visited[ny][nx][keys] = True
                            nq.append((ny, nx, keys))
            q = nq
            count += 1
        return -1

    A, F = ord('A'), ord('F')
    a, f = ord('a'), ord('f')
    dot, wall = ord('.'), ord('#')
    start, end = ord('0'), ord('1')

    input = stdin.readline
    N, M = map(int, input().split())
    mat = tuple(tuple(map(ord, input().rstrip())) for _ in range(N))
    visited = [[[False] * 64 for _ in range(M)] for _ in range(N)]
    y, x = 0, 0
    for i in range(N):
        for j in range(M):
            if mat[i][j] == start:
                y, x = i, j
            elif mat[i][j] == wall:
                visited[i][j] = [True] * 64
    print(bfs(mat, visited, y, x))
    return


main()
