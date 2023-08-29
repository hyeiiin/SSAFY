from itertools import permutations


def main():
    def f(hps):
        hps.sort()
        i = sum((60 ** n) * hp for n, hp in enumerate(hps))
        if dp[i] == -1:
            dp[i] = 1 + min(f([hp - d for hp, d in zip(hps, ds) if hp > d])
                            for ds in permutations(damages[:len(hps)]))
        return dp[i]

    damages = (9, 3, 1)
    N = int(input())
    dp = [-1] * sum(60 ** n for n in range(N + 1))
    dp[0] = 0
    print(f(list(map(int, input().split()))))


main()
