from sys import stdin


def main():
    # 상하우좌
    ds = (None, -1, 1, 1, -1)

    # 입력
    R, C, M = map(int, stdin.readline().split())
    see = {}
    for line in stdin.read().splitlines():
        r, c, s, d, z = map(int, line.split())
        see[r, c] = (s, d, z)

    # 상어 크기의 합
    answer = 0
    for c in range(1, C + 1):
        # 상어 낚시해버리기
        for r in range(1, R + 1):
            if (r, c) in see:
                answer += see.pop((r, c))[-1]
                break
        # 상어 움직여버리기
        new_see = {}
        for (r, c), (s, d, z) in see.items():
            # 상어가 경계를 벗어나면 경계 안에 들어갈 때까지 위치와 방향을 뒤집음
            # 상하
            if d <= 2:
                r += ds[d] * s
                while not 1 <= r <= R:
                    if d == 1:
                        d = 2
                        r = -r + 2
                    else:
                        d = 1
                        r = R * 2 - r
            # 우좌
            else:
                c += ds[d] * s
                while not 1 <= c <= C:
                    if d == 4:
                        d = 3
                        c = -c + 2
                    else:
                        d = 4
                        c = C * 2 - c
            # 상어 잡아먹어버리기
            if (r, c) not in new_see or z > new_see[r, c][-1]:
                new_see[r, c] = (s, d, z)
        see = new_see
    print(answer)


main()
