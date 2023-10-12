t = int(input())

def move():
    total = charge()
    for time in range(m):
        players[0][0] += dx[pathA[time]]
        players[0][1] += dy[pathA[time]]
        players[1][0] += dx[pathB[time]]
        players[1][1] += dy[pathB[time]]

        total += charge()

    return total

def charge():
    maxCharge = 0
    for a in range(apCnt):
        for b in range(apCnt):
            sum = 0
            aSum = check(a, players[0][0], players[0][1])
            bSum = check(b, players[1][0], players[1][1])

            if a != b:
                sum = aSum + bSum
            else:
                sum = max(aSum, bSum)

            if sum > maxCharge:
                maxCharge = sum

    return maxCharge

def check(a, x, y):
    if abs(ap[a][0] - x) + abs(ap[a][1] - y) <= ap[a][2]:
        return ap[a][3]
    else:
        return 0

for tc in range(1, t+1):
    # m : 이동시간
    # ap : bc의 개수
    m, apCnt = map(int, input().split())

    players = [[0 for _ in range(2)] for _ in range(2)]

    players[0][0] = players[0][1] = 1
    players[1][0] = players[1][1] = 10

    pathA = list(map(int, input().split()))
    pathB = list(map(int, input().split()))

    # dx, dy 방향 주의 !
    dx = [0, 0, 1, 0, -1]
    dy = [0, -1, 0, 1, 0]

    ap = [[] for _ in range(apCnt)]
    for i in range(apCnt):
        apList = list(map(int, input().split()))
        ap[i].append(apList[0])
        ap[i].append(apList[1])
        ap[i].append(apList[2])
        ap[i].append(apList[3])
        apList.clear()

    print("#{} {}".format(tc, move()))

