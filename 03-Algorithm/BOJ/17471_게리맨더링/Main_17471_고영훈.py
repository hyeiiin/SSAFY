from itertools import combinations
from sys import stdin


def main():
    def dfs(s, u):
        for v in adj_list[u]:
            if v in s:
                s.remove(v)
                dfs(s, v)

    input = stdin.readline
    N = int(input())
    pops = [0] + list(map(int, input().split()))
    adj_list = [set()] + [set(map(int, input().split()[1:])) for _ in range(N)]
    answer = 1001

    city_set_all = set(range(1, N + 1))
    for r in range(1, N // 2 + 1):
        for cities in combinations(city_set_all, r):
            a = set(cities)
            b = city_set_all - a
            a_pop = sum(pops[i] for i in a)
            b_pop = sum(pops[i] for i in b)
            dfs(a, a.pop())
            if a:
                continue
            dfs(b, b.pop())
            if b:
                continue
            answer = min(answer, abs(a_pop - b_pop))

    if answer == 1001:
        print(-1)
    else:
        print(answer)


main()
