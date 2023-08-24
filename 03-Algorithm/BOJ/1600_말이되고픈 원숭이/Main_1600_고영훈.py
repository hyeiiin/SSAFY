from sys import stdin


def main():
    def bfs():
        def append_if_more_chance(next_chance):
            ny, nx = y + dy, x + dx
            if 0 <= ny < H and 0 <= nx < W and not blocked[ny][nx] and chance_mat[ny][nx] < next_chance:
                chance_mat[ny][nx] = next_chance
                nq.append((ny, nx, next_chance))

        count = 0
        chance_mat[0][0] = K
        q = [(0, 0, K)]
        while q:
            nq = []
            for y, x, chance in q:
                if y == H - 1 and x == W - 1:
                    return count
                for dy, dx in monkey_deltas:
                    append_if_more_chance(chance)
                if chance:
                    for dy, dx in horse_deltas:
                        append_if_more_chance(chance - 1)
            q = nq
            count += 1
        return -1

    monkey_deltas = ((-1, 0), (1, 0), (0, -1), (0, 1))
    horse_deltas = ((-2, -1), (-2, 1), (-1, -2), (-1, 2), (1, -2), (1, 2), (2, -1), (2, 1))
    K = int(stdin.readline())
    W, H = map(int, stdin.readline().split())
    blocked = tuple(tuple(map(int, line[::2])) for line in stdin.read().splitlines())
    chance_mat = [[-1] * W for _ in range(H)]
    print(bfs())


main()
