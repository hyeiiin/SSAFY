T = int(input())
for tc in range(1, T+1):
    #N:셀의 개수, M: 격리시간, K: 미생물 군집의 개수
    N, M, K = map(int,input().split())

    info = []
    delta = [[-1, 0], [1, 0], [0, -1], [0, 1]]
    rev = [2, 1, 4, 3]  # 방향전환

    for _ in range(K):
        #y:열, x:행, mcs:미생물수, d:이동방향
        #1 상 2 하 3 좌 4 우
        info.append(list(map(int,input().split())))

    for _ in range(M):
        info_dic = dict()
        for k in range(K):
            x, y, mcs, d = info[k]
            if mcs==0: #군집의 미생물 수가 0이면 체크하지 않음
                continue

            nr = x + delta[d-1][0]
            nc = y + delta[d-1][1]

            info[k][0] = nr
            info[k][1] = nc
            #약품이 칠해진 셀: 0행, N-1행, 0열, N-1열
            if not(1<=nr<N-1 and 1<=nc<N-1):
                #약품이 칠해진 셀로 들어간 경우
                info[k][2] //= 2
                info[k][3] = rev[d-1]

            if (nr,nc) not in info_dic.keys():
                #이동한 군집의 정보를 딕셔너리에 담기
                info_dic[(nr,nc)] = [k,mcs]
            else: #이미 그 좌표에 군집이 존재하는 경우
                num, cur_mcs = info_dic[(nr, nc)]
                if info[k][2] > cur_mcs: #들어있는 미생물 수보다 큰 경우
                    info_dic[(nr,nc)] = [k, info[k][2]] #큰 값으로 갱신
                    info[k][2] += info[num][2] #미생물 수 큰값에 더해주기
                    info[num][2] = 0

                else: #들어있는 미생물 수보다 작다면
                    info[num][2] += info[k][2] #마찬가지로 큰쪽에 더해주기
                    info[k][2] = 0

    res = 0
    for info_mcs in info:
        res += info_mcs[2]

    #M시간 후 남아있는 미생물의 총수
    print("#{} {}".format(tc, res))
