def solution():
    N, dp = input().split(maxsplit=1)
    N, dp = int(N), [x if x else 10_000 for x in map(int, dp[::2])]
    for k in range(N):
        k2 = k * N
        for i in range(N):
            if i == k:
                continue
            i2 = i * N
            for j in range(N):
                if j == k or j == i:
                    continue
                dp[i2 + j] = min(dp[i2 + j], dp[i2 + k] + dp[k2 + j])
    return min(sum(dp[i2:i2 + N]) - 10_000 for i2 in range(0, N * N, N))


def main():
    for t in range(1, int(input()) + 1):
        print(f'#{t} {solution()}')


main()
