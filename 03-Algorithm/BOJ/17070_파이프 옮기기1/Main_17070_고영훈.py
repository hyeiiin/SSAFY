from sys import stdin


def main():
    def solution():
        def is_available(direction, y, x):
            if blocked[y][x]:
                return False
            if direction == 0:
                return x > 0 and not blocked[y][x - 1]
            if direction == 1:
                return y > 0 and not blocked[y - 1][x]
            else:
                return x > 0 and y > 0 and not blocked[y][x - 1] and not blocked[y - 1][x]

        def update_dp(y, x):
            dp0 = dp[0][y][x + 1]
            dp1 = dp[1][y + 1][x]
            dp2 = dp[2][y + 1][x + 1]
            if is_available(0, y, x):
                dp[0][y][x] = dp0 + dp2
            if is_available(1, y, x):
                dp[1][y][x] = dp1 + dp2
            if is_available(2, y, x):
                dp[2][y][x] = dp0 + dp1 + dp2

        dp = [[[0] * (N + 1) for _ in range(N + 1)] for _ in range(3)]
        for direction in range(3):
            if is_available(direction, N, N):
                dp[direction][N][N] = 1
        for i in range(N - 1, 0, -1):
            update_dp(i, i)
            for j in range(i - 1, -1, -1):
                update_dp(i, j)
                update_dp(j, i)
        return dp[0][0][1]

    N = int(stdin.readline())
    blocked = [list(map(int, line[::2])) + [0]
               for line in stdin.read().splitlines()] + [[0] * (N + 1)]
    print(solution())


main()
