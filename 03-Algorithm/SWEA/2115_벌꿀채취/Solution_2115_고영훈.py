from itertools import combinations


def solution():
    N, M, C = map(int, input().split())
    mat = [list(map(int, input()[::2])) for _ in range(N)]

    # 벌꿀 수익 계산
    profits = []
    for y in range(N):
        for x in range(N - (M - 1)):
            weights = mat[y][x:x + M]
            max_profit = 0
            # 무게가 C를 초과하지 않는 부분집합 중에서 최대 수익 찾기
            for r in range(1, M + 1):
                for ws in combinations(weights, r):
                    if sum(ws) <= C:
                        max_profit = max(max_profit, sum(w * w for w in ws))
            profits.append((max_profit, y, x))
    profits.sort(reverse=True)

    # 불가능한 꿀통 조합 거르고 최대 수익 찾기
    answers = []
    for (p1, y1, x1), (p2, y2, x2) in combinations(profits, 2):
        if y1 == y2 and abs(x1 - x2) <= M - 1:
            continue
        answers.append(p1 + p2)
    return max(answers)


def main():
    for t in range(1, int(input()) + 1):
        print(f'#{t} {solution()}')


main()
