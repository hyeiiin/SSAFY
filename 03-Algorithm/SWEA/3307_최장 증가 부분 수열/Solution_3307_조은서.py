# 최장 증가 부분 수열
import sys
input = sys.stdin.readline

t = int(input())

for tc in range(1, t+1):
    n = int(input())
    array = list(map(int, input().split()))
    res = [int(1) for _ in range(n)]

    for i in range(1, n):
        for j in range(0, i):
            if (array[j] < array[i]):
                res[i] = max(res[i], res[j] + 1)

    result = max(res)
    print("#{} {}".format(tc, result))

# 1 3 2 5 4
# 1 1 1 1 1
#   3 2 2 2
#       5 4
# result: [1 2 4] -> 3