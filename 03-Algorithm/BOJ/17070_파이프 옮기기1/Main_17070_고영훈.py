from sys import stdin


def main():
    def solution():
        def update_dp(y, x):
            if blocked[y][x]:
                return
            dp0 = dp[0][y][x + 1]
            dp1 = dp[1][y + 1][x]
            dp2 = dp[2][y + 1][x + 1]
            dp[0][y][x] = dp0 + dp2
            dp[1][y][x] = dp1 + dp2
            if x > 0 and y > 0 and not blocked[y][x - 1] and not blocked[y - 1][x]:
                dp[2][y][x] = dp0 + dp1 + dp2

        dp = [[[0] * (N + 1) for _ in range(N + 1)] for _ in range(3)]
        for direction in range(3):
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
