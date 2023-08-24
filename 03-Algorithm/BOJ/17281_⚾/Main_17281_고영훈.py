from itertools import cycle, permutations
from sys import stdin


def main():
    input = stdin.readline
    N = int(input())
    innings = [list(map(int, input().split())) for _ in range(N)]
    max_score = 0
    for player_order in permutations(range(1, 9)):
        player_cycle = cycle(player_order[:3] + (0,) + player_order[3:])
        score = 0
        for results in innings:
            base1 = 0
            base2 = 0
            base3 = 0
            outs = 0
            for player in player_cycle:
                result = results[player]
                if result == 1:
                    score += base3
                    base3 = base2
                    base2 = base1
                    base1 = 1
                elif result == 2:
                    score += base3 + base2
                    base3 = base1
                    base2 = 1
                    base1 = 0
                elif result == 3:
                    score += base3 + base2 + base1
                    base3 = 1
                    base2 = 0
                    base1 = 0
                elif result == 4:
                    score += base3 + base2 + base1 + 1
                    base3 = 0
                    base2 = 0
                    base1 = 0
                else:
                    outs += 1
                    if outs == 3:
                        break
        if score > max_score:
            max_score = score
    print(max_score)


main()
