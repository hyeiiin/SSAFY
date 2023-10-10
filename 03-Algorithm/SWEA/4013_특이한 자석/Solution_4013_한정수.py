from collections import deque
T = int(input())


def rotate_clock(magnet_num):
    # 최초로 돌아가는 시계.(시계 회전) 왼쪽 오른쪽 체크
    # 12시 == 0, 3시 == 2, 6시 == 4, 9시 == 6
    # 왼쪽 체크
    if magnet_num != 0:
        if arr[magnet_num-1][2] != arr[magnet_num][6]:
            chain_clock(magnet_num-1, 0, 0)
    # 오른쪽 체크
    if magnet_num != 3:
        if arr[magnet_num][2] != arr[magnet_num+1][6]:
            chain_clock(magnet_num+1, 0, 1)

    temp = arr[magnet_num].pop()
    arr[magnet_num].appendleft(temp)


def rotate_c_clock(magnet_num):
    # 최초로 돌아가는 시계.(반시계 회전) 왼쪽 오른쪽 체크
    # 왼쪽 체크
    if magnet_num != 0:
        if arr[magnet_num - 1][2] != arr[magnet_num][6]:
            chain_clock(magnet_num - 1, 1, 0)
    # 오른쪽 체크
    if magnet_num != 3:
        if arr[magnet_num][2] != arr[magnet_num + 1][6]:
            chain_clock(magnet_num + 1, 1, 1)

    temp = arr[magnet_num].popleft()
    arr[magnet_num].append(temp)

def chain_clock(magnet_num, rotation, direction):
    # 두번째 시계부터 진행되는 연쇄 반응 >> 양쪽이 아니라, 연쇄된 방향으로만 체크.
    # ex) 0번째 톱니가 회전해서 1번째 톱니가 영향을 받으면, 1번째 톱니는 연쇄된 방향인 (오른쪽)만 체크하면 된다.
    # rotation  0 : 반시계, 1 : 시계
    # direction 0 : 왼쪽, 1: 오른쪽

    # 연쇄 방향이 왼쪽이고,
    if direction == 0:
        if magnet_num != 0:
            if arr[magnet_num-1][2] != arr[magnet_num][6]:
                if rotation == 0:
                    # 현재 톱니가 반시계면, 왼쪽놈은 시계
                    chain_clock(magnet_num-1, 1, 0)
                else:
                    # 현재 톱니가 시계면, 왼쪽놈은 반시계
                    chain_clock(magnet_num-1, 0, 0)
    # 연쇄 방향이 오른쪽이면
    else:
        if magnet_num != 3:
            if arr[magnet_num][2] != arr[magnet_num+1][6]:
                if rotation == 0:
                    chain_clock(magnet_num+1, 1, 1)
                else:
                    chain_clock(magnet_num+1, 0, 1)

    # 본인 회전
    if rotation == 0:
        temp = arr[magnet_num].popleft()
        arr[magnet_num].append(temp)
    else:
        temp = arr[magnet_num].pop()
        arr[magnet_num].appendleft(temp)


for test_case in range(1, T+1):
    K = int(input())
    arr = []
    for i in range(4):
        queue = deque(list(map(int, input().split())))
        arr.append(queue)
        # 0 0 0 0 0 0 0 0
        # 0    2    4   6
        # 12시 3시  6시  9시

        # 0 == N, 1 == S

    score = [1, 2, 4, 8]
    result = 0
    for i in range(K):
        magnet_num, rotate = map(int, input().split())
        magnet_num -= 1

        if rotate == 1:
            # 시계 회전
            rotate_clock(magnet_num)
        else:
            # rotate == -1:
            # 반시계 회전
            rotate_c_clock(magnet_num)

    # 최종결과 계산
    for j in range(4):
        result += score[j] * arr[j][0]

    print("#"+str(test_case)+" "+str(result))