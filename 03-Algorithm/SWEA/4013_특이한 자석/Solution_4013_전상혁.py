
def rotate_magnet(magnet, d):
    #시계방향
    if d==1:
        #마지막값 빼서 0 인덱스에 삽입
        last = magnet.pop()
        magnet.insert(0, last)
    #반시계
    else:
        #첫번째값 빼서 뒤로 삽입
        first = magnet.pop(0)
        magnet.append(first)

def calculate(magnets):
    scores = [1,2,4,8]
    total_score = 0

    for i in range(4):
        #N:0 S:1
        if magnets[i][0] == 1:
            total_score += scores[i]

    return total_score

T = int(input())
for tc in range(1, T+1):

    K = int(input()) #자석을 회전시키는 횟수
    #1~4번 자석까지 8개 날의 자성정보
    magnets = [list(map(int,input().split())) for _ in range(4)]
    #회전시키려는 자석의 번호, 회전방향
    # print(magnets)

    #1: 시계, -1: 반시계

    for _ in range(K):
        num, d = map(int,input().split())
        rotate_info = [0]*4

        rotate_info[num-1] = d

        #왼쪽 자석부터 체크, 인접한 자석 회전 방향 설정하기
        for i in range(num-1,0,-1):
            #자성이 다른 경우
            if magnets[i][6] != magnets[i-1][2]:
                rotate_info[i-1] = -rotate_info[i]

        # 왼쪽 자석부터 체크, 인접한 자석 회전 방향 설정하기
        for i in range(num-1,3):
            # 자성이 다른 경우
            if magnets[i][2] != magnets[i+1][6]:
                rotate_info[i+1] = -rotate_info[i]

        #회전시키기
        for i in range(4):
            if rotate_info[i] != 0:
                rotate_magnet(magnets[i], rotate_info[i])

    res = calculate(magnets)

    print("#{} {}".format(tc, res))