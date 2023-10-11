def solution():
    input()
    trees = sorted(map(int, input().split()))
    MAX_HEIGHT = trees[-1]
    ONE_LESS = MAX_HEIGHT - 1
    TWO_LESS = MAX_HEIGHT - 2
    one_count = trees.count(ONE_LESS)
    two_count = trees.count(TWO_LESS)
    trees = trees[:-trees.count(MAX_HEIGHT) - one_count - two_count]
    # 우선순위
    # 홀수: 1 작은 나무 -> 3 이상 작은 나무 -> 2 작은 나무
    # 짝수: 2 작은 나무 -> 3 이상 작은 나무
    min_count = min(one_count, two_count)
    one_count -= min_count
    two_count -= min_count
    day = min_count * 2
    while trees or one_count or two_count:
        day += 1
        if day & 1:
            if one_count:
                one_count -= 1
            elif trees:
                x = trees[-1] + 1
                if x == TWO_LESS:
                    del trees[-1]
                    # two_count += 1
                    day += 1
                else:
                    trees[-1] = x
            elif two_count > 1:
                two_count -= 1
                one_count += 1
        else:
            if two_count:
                two_count -= 1
            elif trees:
                x = trees[-1] + 2
                if x == ONE_LESS:
                    del trees[-1]
                    # one_count += 1
                    day += 1
                elif x == TWO_LESS:
                    del trees[-1]
                    two_count += 1
                else:
                    trees[-1] = x
    return day


def main():
    print('\n'.join(f'#{t} {solution()}' for t in range(1, int(input()) + 1)))


main()
