def f(n):
    dp = [0] * (n + 1)
    dp[1] = 2
    dp[2] = 5
    for i in range(3, n + 1):
        dp[i] = dp[i - 1] * 2 + dp[i - 2]
    return dp[n]


print(f(6))
