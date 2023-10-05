from sys import stdin


def main():
    N, M = map(int, stdin.readline().split())
    mat = [list(map(int, line[::2])) for line in stdin.read().splitlines()]

    # 1. 정점 번호 지정
    def dfs(i, j, v):
        visited[i][j] = True
        mat[i][j] = v
        for di, dj in ((-1, 0), (1, 0), (0, -1), (0, 1)):
            ni, nj = i + di, j + dj
            if 0 <= ni < N and 0 <= nj < M:
                if mat[ni][nj]:
                    if not visited[ni][nj]:
                        dfs(ni, nj, v)

    V = 0
    visited = [[False] * M for _ in range(N)]
    for i in range(N):
        for j in range(M):
            if mat[i][j] and not visited[i][j]:
                V += 1
                dfs(i, j, V)

    # 2. 가중치 2 이상의 간선 리스트
    edges = []
    visited_col = [row[:] for row in visited]
    visited_row = [row[:] for row in visited]
    for i in range(N):
        for j in range(M):
            if not visited_col[i][j]:
                visited_col[i][j] = True
                for up in range(i - 1, -1, -1):
                    visited_col[up][j] = True
                    u = mat[up][j]
                    if u:
                        for down in range(i + 1, N):
                            visited_col[down][j] = True
                            v = mat[down][j]
                            if v:
                                w = down - up - 1
                                if w >= 2:
                                    edges.append((u, v, w))
                                break
                        break
            if not visited_row[i][j]:
                visited_row[i][j] = True
                for left in range(j - 1, -1, -1):
                    visited_row[i][left] = True
                    u = mat[i][left]
                    if u:
                        for right in range(j + 1, M):
                            visited_row[i][right] = True
                            v = mat[i][right]
                            if v:
                                w = right - left - 1
                                if right - left > 2:
                                    edges.append((u, v, w))
                                break
                        break

    # 3. 최소 스패닝 트리
    def find(x):
        if x == parents[x]:
            return x
        parents[x] = find(parents[x])
        return parents[x]

    def union(a, b):
        a = find(a)
        b = find(b)
        if a == b:
            return False
        parents[b] = parents[a]
        return True

    weights = 0
    parents = list(range(V + 1))
    edges.sort(key=lambda x: x[2])
    for u, v, w in edges:
        if union(u, v):
            weights += w
    for u, v, w in edges:
        union(u, v)

    # 4. 모두 연결됐으면 최소값 출력
    root = parents[1]
    for x in parents[1:]:
        if x != root:
            print(-1)
            return
    print(weights)


main()
