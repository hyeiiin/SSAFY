t = int(input())

# 자석 회전
def rotate(num, dir):

    check[num] = 1

    if num < 3: # 1, 2, 3번 자석은 오른쪽의 맞물리는 부분을 확인해야함
        if magnet[num][2] != magnet[num+1][6] and check[num+1] == 0:
            rotate(num+1, dir * -1)

    if num > 0: # 2, 3, 4번 자석은 왼쪽의 맞물리는 부분을 확인해야함
        if magnet[num][6] != magnet[num-1][2] and check[num-1] == 0:
            rotate(num-1, dir * -1)

    if dir == 1: # 시계방향으로 회전
        magnet[num] = [magnet[num].pop()] + magnet[num]

    if dir == -1: # 반시계방향으로 회전
        magnet[num] = magnet[num][1:] + [magnet[num][0]]



for tc in range(1, t+1):
    k = int(input())

    magnet = []

    for _ in range(4):
        magnet.append(list(map(int, input().split())))

    for _ in range(k):
        num, dir = list(map(int, input().split())) # 자석 번호(num), 회전 방향(dir)
        check = [0] * 4
        # print(magnet)
        rotate(num-1, dir) # 자석 회전하기

    result = 0
    for i in range(4):
        result += magnet[i][0] * (2**i)
    print("#{} {}".format(tc, result))

