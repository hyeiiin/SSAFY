import sys
input = sys.stdin.readline

T = int(input())

memo = [1, 1]
def fact(n):
    if n == 1: return 1

    for i in range(2, n + 1):
        memo.append(i * memo[i - 1])

    return memo[n]


# nCr = n! / (n-r)!r!
for _ in range(T):
    N, M = list(map(int, input().split()))
    mFact = fact(M)
    nFact = fact(N)
    nmFact = fact(M - N)

    answer = mFact // (nFact * nmFact)
    print(answer)
