import sys
input = sys.stdin.readline

memo = []

n = int(input())

def dp(n):

    memo.append(2)
    memo.append(5)
    for i in range(2, n):
        memo.append(2*memo[i-1] + memo[i-2])

dp(n)
print(memo[n-1])