def main():
    def f(x):
        if dp[x] != -1:
            return dp[x]
        y = min(f(x // 3) + (x % 3), f(x // 2) + (x % 2)) + 1
        dp[x] = y
        return y

    X = int(input())
    dp = [-1] * (X + 2)
    dp[1] = 0
    dp[2] = 1
    print(f(X))


main()
