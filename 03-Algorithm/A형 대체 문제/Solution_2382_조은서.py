t = int(input())

def move(area):
    area_copy = [[[] for _ in range(n)] for _ in range(n)]

    for r in range(n):
        for c in range(n):
            if area[r][c]:
                num, dir = area[r][c][0]

                nr = r + dx[dir]
                nc = c + dy[dir]

                if nr == 0 or nc == 0 or nr == n-1 or nc == n-1:
                    num //= 2
                    if dir == 1:
                        dir = 2
                    elif dir == 2:
                        dir = 1
                    elif dir == 3:
                        dir = 4
                    elif dir == 4:
                        dir = 3

                if num > 0:
                    area_copy[nr][nc].append((num, dir))

    for r in range(n):
        for c in range(n):
            if len(area_copy[r][c]) >= 2:
                total = 0
                max_value = 0
                direction = 0
                while area_copy[r][c]:
                    num, dir = area_copy[r][c].pop()
                    total += num

                    if num > max_value:
                        max_value = num
                        direction = dir

                area_copy[r][c].append((total, direction))

    return area_copy


for tc in range(1, t+1):
    n, m, k = list(map(int, input().split()))
    area = [[[] for _ in range(n)] for _ in range(n)]
    dx = [0, -1, 1, 0, 0]
    dy = [0, 0, 0, -1, 1]

    for _ in range(k):
        x, y, num, dir = list(map(int, input().split()))
        area[x][y].append((num, dir))

    for _ in range(m):
        area = move(area)

    ans = 0
    for i in range(n):
        for j in range(n):
            while area[i][j]:
                num, dir = area[i][j].pop()
                ans += num

    print("#{} {}".format(tc, ans))
