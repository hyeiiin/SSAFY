from sys import stdin


def main():
    def solution(mat):
        for _ in range(T):
            # 미세먼지 확산
            new_mat = [[0] * C for _ in range(R)]
            for y in range(R):
                for x in range(C):
                    if x == 0 and y in cleaner:
                        new_mat[y][x] = mat[y][x]
                        continue
                    v = mat[y][x]
                    nv = v // 5
                    if nv:
                        for dy, dx in ((-1, 0), (1, 0), (0, -1), (0, 1)):
                            ny, nx = y + dy, x + dx
                            if 0 <= ny < R and 0 <= nx < C:
                                if nx == 0 and ny in cleaner:
                                    continue
                                new_mat[ny][nx] += nv
                                v -= nv
                    new_mat[y][x] += v
            mat = new_mat
            # 공기청정기 작동
            new_mat = [[0] * C for _ in range(R)]
            for y in range(R):
                for x in range(C):
                    v = mat[y][x]
                    if x == 0:
                        if y < cleaner[0] - 1:
                            new_mat[y + 1][x] = v
                        elif y > cleaner[1] + 1:
                            new_mat[y - 1][x] = v
                        else:
                            # 먼지 냠냠
                            pass
                    elif y == 0 or y == R - 1:
                        new_mat[y][x - 1] = v
                    elif x == C - 1:
                        if y <= cleaner[0]:
                            new_mat[y - 1][x] = v
                        else:
                            new_mat[y + 1][x] = v
                    elif y in cleaner:
                        new_mat[y][x + 1] = v
                    else:
                        new_mat[y][x] = v
            mat = new_mat
        return sum(v for row in mat for v in row)

    R, C, T = map(int, stdin.readline().split())
    mat = [list(map(int, line.split())) for line in stdin.read().splitlines()]

    cleaner = []
    for r in range(R):
        for c in range(C):
            if mat[r][c] == -1:
                cleaner.append(r)
    print(solution(mat))


main()
