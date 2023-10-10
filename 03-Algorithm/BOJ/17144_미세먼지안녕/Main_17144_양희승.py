import sys
input = sys.stdin.readline

def main():

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    # 미세먼지 확산
    def spread():
        new_room = [[0] * c for _ in range(r)]
        new_room[air1_x][0] = -1
        new_room[air2_x][0] = -1

        for x in range(r):
            for y in range(c):
                if room[x][y] > 0:
                    cnt = 0
                    for d in range(4):
                        nx, ny = x + dx[d], y + dy[d]
                        if 0 <= nx < r and 0 <= ny < c and room[nx][ny] != -1:
                            new_room[nx][ny] += (room[x][y] // 5)
                            cnt += 1
                    new_room[x][y] += room[x][y] - ((room[x][y] // 5) * cnt)

        return [row[:] for row in new_room]

    # 위쪽
    def rotate_reverse():
        for i in range(air1_x-2, -1, -1):
            room[i+1][0] = room[i][0]
        for i in range(1, c):
            room[0][i-1] = room[0][i]
        for i in range(1, air1_x+1):
            room[i-1][c-1] = room[i][c-1]
        for i in range(c-2, 0, -1):
            room[air1_x][i+1] = room[air1_x][i]
        room[air1_x][1] = 0

    # 아래쪽
    def rotate():
        for i in range(air2_x+1, r-1):
            room[i][0] = room[i+1][0]
        for i in range(1, c):
            room[r-1][i-1] = room[r-1][i]
        for i in range(r-2, air2_x-1, -1):
            room[i+1][c-1] = room[i][c-1]
        for i in range(c-2, 0, -1):
            room[air2_x][i+1] = room[air2_x][i]
        room[air2_x][1] = 0

    # r, c <= 50 / t <= 1000
    r, c, t = map(int, input().split())
    room = [list(map(int, input().split())) for _ in range(r)]
    air1_x, air2_x = 0, 0
    for i in range(r):
        if room[i][0] == -1:
            air1_x = i
            air2_x = i+1
            break

    for second in range(t):
        room = spread()
        rotate_reverse()
        rotate()

    # 미세먼지 총합 출력 (공기청정기 -1을 고려, 초기 total=2 시작)
    total = 2
    for i in room:
        total += sum(i)
    print(total)

main()
