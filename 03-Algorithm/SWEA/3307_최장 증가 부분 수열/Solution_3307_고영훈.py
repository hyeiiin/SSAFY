def solution():
    N = int(input())
    arr = list(map(int, input().split()))
    dp = [1] * N
    for right in range(1, N):
        for left in range(right):
            if arr[right] > arr[left]:
                dp[right] = max(dp[right], dp[left] + 1)
    return max(dp)


def main():
    for t in range(1, int(input()) + 1):
        print(f'#{t} {solution()}')


main()
