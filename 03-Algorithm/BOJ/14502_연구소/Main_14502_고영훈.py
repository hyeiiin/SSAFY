from collections import deque
from itertools import combinations
from sys import stdin


def main():
    def bfs(q, visited, walls) -> int:
        for y, x in walls:
            visited |= (1 << (y * 8 + x))
        q = deque(q)
        count = 3
        while q:
            y, x = q.popleft()
            for dy, dx in ((-1, 0), (1, 0), (0, -1), (0, 1)):
                ny, nx = y + dy, x + dx
                if 0 <= ny < N and 0 <= nx < M and not visited & (1 << (ny * 8 + nx)):
                    visited |= (1 << (ny * 8 + nx))
                    q.append((ny, nx))
                    count += 1
        return count

    N, M = map(int, stdin.readline().split())
    mat = [list(map(int, x[::2])) for x in stdin.read().splitlines()]
    blanks = []
    viruses = []
    visited = 0
    for y in range(N):
        for x in range(M):
            v = mat[y][x]
            if mat[y][x] == 0:
                blanks.append((y, x))
            else:
                if mat[y][x] == 2:
                    viruses.append((y, x))
                visited |= (1 << (y * 8 + x))
    max_count = min(bfs(viruses, visited, walls) for walls in combinations(blanks, 3))
    print(len(blanks) - max_count)


main()
