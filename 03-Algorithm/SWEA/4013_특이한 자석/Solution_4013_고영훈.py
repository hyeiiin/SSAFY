from collections import deque


def solution():
    def rotate_left(right, dir):
        left = right - 1
        if left < 0:
            return
        if magnets[left][2] != magnets[right][6]:
            rotate_left(left, -dir)
            magnets[left].rotate(dir)

    def rotate_right(left, dir):
        right = left + 1
        if right > 3:
            return
        if magnets[left][2] != magnets[right][6]:
            rotate_right(right, -dir)
            magnets[right].rotate(dir)

    K = int(input())
    magnets = [deque(map(int, input().split())) for _ in range(4)]
    for _ in range(K):
        n, d = map(int, input().split())
        i = n - 1
        rotate_left(i, -d)
        rotate_right(i, -d)
        magnets[i].rotate(d)
    return sum(2 ** x for x in range(4) if magnets[x][0])


def main():
    for t in range(1, int(input()) + 1):
        print(f'#{t} {solution()}')


main()
