from sys import stdin


def f(n, m):
    """
    n/m 1 2 3 4 5
      1 1 2 3 4 5
      2   1 3 6 10
      3     1 4 10
      4       1 5
    """
    dp = [[1] * (m + 1) for _ in range(n + 1)]
    for i in range(1, n + 1):
        for j in range(i + 1, m + 1):
            dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1]
    return dp[n][m]


def main():
    for line in stdin.read().splitlines()[1:]:
        n, m = map(int, line.split())
        print(f(n, m))


main()
