def solution():
    N, K = map(int, input().split())
    s = input() * 2
    step = N // 4
    arr = [s[start + i:start + step + i]
           for i in range(step)
           for start in range(0, N, step)]
    return int(sorted(dict.fromkeys(arr), reverse=True)[K - 1], 16)


def main():
    for t in range(1, int(input()) + 1):
        print(f'#{t} {solution()}')


main()
