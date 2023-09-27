from sys import stdin


def main():
    def recur(cur, visited):
        dist = abs(end[0] - cur[0]) + abs(end[1] - cur[1])
        if dist <= 1000:
            return True

        for i in range(n):
            if visited[i]:
                continue
            pos = stores[i]
            dist = abs(pos[0] - cur[0]) + abs(pos[1] - cur[1])
            if dist > 1000:
                continue
            visited[i] = True
            if recur(pos, visited):
                return True
        return False

    input = stdin.readline
    for t in range(int(input())):
        n = int(input())
        start = tuple(map(int, input().split()))
        stores = tuple(tuple(map(int, input().split())) for _ in range(n))
        end = tuple(map(int, input().split()))
        result = recur(start, [False] * n)
        print(['sad', 'happy'][result])


main()