def main():
    def f(x):
        if dp[x] == -1:
            dp[x] = min(f(x // 3) + (x % 3), f(x // 2) + (x % 2)) + 1
        return dp[x]

    N = int(input())
    dp = [-1] * (N + 2)
    dp[1] = 0
    dp[2] = 1
    print(f(N))


main()
