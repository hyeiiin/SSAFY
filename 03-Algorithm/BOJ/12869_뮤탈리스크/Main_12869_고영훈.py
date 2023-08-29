from functools import reduce
from itertools import permutations


def main():
    def f(hps):
        i = reduce(lambda r, x: r * 60 + x, hps, 0)
        if dp[i] == -1:
            dp[i] = 0
            if hps:
                dp[i] = 1 + min(f([hp - d for hp, d in zip(new_hps, damages) if hp > d])
                                for new_hps in permutations(hps))
        return dp[i]

    damages = (9, 3, 1)
    N = int(input())
    dp = [-1] * sum(60 ** n for n in range(N + 1))
    print(f(list(map(int, input().split()))))


main()
