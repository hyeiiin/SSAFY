import sys

X, Y, N = map(int, sys.stdin.readline().rstrip().split())

# arr = [[[] for _ in range(Y)] for _ in range(X)]

# shark = [[] for _ in range(N)]
shark = {}
new_shark = {}
input_list = sys.stdin.read().splitlines()
for line in input_list:
    x, y, s, d, z = map(int, line.split())
    # shark[i] = list(map(int, sys.stdin.readline().rstrip().split()))
    shark[x,y] = [s, d, z]


# print(shark)

#shark[x, y, s, d, z]
#      x,y, 속도, 방향, 크기
# d : [1, 2, 3, 4]
#      위 아래 왼쪽 오른쪽
# direction = [[], [-1, 0], [1, 0], [0, 1], [0, -1]]

def get_next_loc(i, j, speed, dir):

    if dir == 1 or dir == 2:  # i
        cycle = X * 2 - 2
        if dir == 1:
            speed += 2 * (X - 1) - i
        else:
            speed += i

        speed %= cycle
        if speed >= X:
            return (2 * X - 2 - speed, j, 1)
        return (speed, j, 2)

    else:  # j
        cycle = Y * 2 - 2
        if dir == 4:
            speed += 2 * (Y - 1) - j
        else:
            speed += j

        speed %= cycle
        if speed >= Y:
            return (i, 2 * Y - 2 - speed, 4)
        return (i, speed, 3)



human_pos = 1
sum_size = 0
while True:
    # 사람이 현재 위치에서 상어 잡고 점수 추가.
    for i in range(1, X+1):
        # 해당 열을 0행부터 검사해서 찾으면 sum_size 더해주고 그 상어(키)를 삭제
        if (i, human_pos) in shark.keys():
            sum_size += shark[i, human_pos][2]
            del shark[i, human_pos]
            break

    # 상어 움직이고
    #  >> nx, ny가 밖에 나가려하면 d를 바꿔주고.
    #  >> 상어가 겹쳐 있는가 확인.   >>> 겹쳐있는거 확인용도로 배열 필요함. dict[x, y] = [상어 번호]
    #     >>   지금보니까 이 문제는 전체 맵을 나타내는 arr이 필요한게 아니라,
    #     >>   각 상어별 위치 좌표만 가지고 있으면 됨.
    #         >> 상어 위치를 갱신하면서, 이미 그 키 값이 있으면 사이즈 큰놈으로 대체

    for x, y in shark.keys():
        s = shark[x,y][0]
        # d = direction[shark[x,y][1]]
        d = shark[x,y][1]
        z = shark[x,y][2]
        start_x = x
        start_y = y

        temp_x = x-1
        temp_y = y-1

        nx, ny, nd = get_next_loc(temp_x, temp_y, s, d)
        nx += 1
        ny += 1
        shark[x,y][1] = nd


        # 상어 1마리의 턴이 끝났고, 이 상어를 새로운 dict인 new_shark에 넣는다.
        # 이때, 이미 new_shark에 key가 존재한다면(x,y), 둘 중 사이즈가 더 큰놈으로 대체.
        if (nx, ny) in new_shark.keys():
            if shark[x,y][2] > new_shark[nx, ny][2]:
                new_shark[nx,ny] = shark[x,y].copy()
        else:
            new_shark[nx, ny] = shark[x, y].copy()

    #for x,y  종료
    # 모든 상어에 대해서 1턴이 끝났고, new_shark를 원본 shark로 넘김.
    shark = new_shark.copy()
    new_shark.clear()
    new_shark = {}

    # 사람 움직이고
    #   >> 배열 밖에(Y) 나갔으면 break
    human_pos += 1
    if human_pos == Y+1:
        break

print(sum_size)
