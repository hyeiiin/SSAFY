import sys

input = sys.stdin.readline

T = int(input())
for tc in range(1, T+1):

    res = 0

    N = int(input())

    lst = list(map(int,input().split()))
    #[1, 3, 2, 5, 4]

    dp = [0]*N


    for i in range(N):
        dp[i] = 1
        for j in range(i):
            if lst[i] > lst[j]:
                dp[i] = max(dp[i], dp[j] + 1)


    res = max(dp)


    print("#{} {}".format(tc, res))
