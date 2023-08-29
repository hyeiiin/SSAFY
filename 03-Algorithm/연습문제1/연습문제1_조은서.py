import sys
input = sys.stdin.readline

memo = [2, 3]
n = int(input())

for i in range(2, n):
    memo.append(memo[i-1] + memo[i-2])

print(memo[n-1])