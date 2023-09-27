import sys
T = int(sys.stdin.readline())




for test_case in range(T):
    # 편의점 개수
    N = int(sys.stdin.readline())
    conv_list = [[] for _ in range(N)]

    home = list(map(int, sys.stdin.readline().split()))

    for i in range(N):
        conv_list[i] = list(map(int, sys.stdin.readline().split()))


    concert = list(map(int, sys.stdin.readline().split()))

    # 좌표 음수 가능.
    # 이차원.

    # 일단 한병 까고 50미터
    # 편의점 들어갔다 나오는 순간 한병 무조건 까고
    # 갈 수 있냐 없냐의 문제. 즉, 병 개수는 의미가 없고 거리가 1000 안에 있냐 없냐만 따지면 된다.

    # 그럼 집 > 편의점 > 편의점 > 편의점 > ... > 콘서트 를 가는데?
    # 편의점1 > 편의점2가 가능한지 안한지 거리로 따져서 그래프로 나타내면?
    # 집, 편의점, 콘서트를 노드로 생각하고
    # 각 노드별로 갈수 있는가 없는가를 인접행렬로 0,1로 표시.

    graph = [[0 for _ in range(N+2)] for _ in range(N+2)]
    # 0번이 집.
    # N-1번이 콘서트.

    new_list = []
    new_list.append(home)
    for i in range(len(conv_list)):
        new_list.append(conv_list[i])
    new_list.append(concert)

    for i in range(len(new_list)):
        for j in range(len(new_list)):
            if i == j:
                graph[i][j] = 1000000
            else:
                x_diff = abs(new_list[i][0] - new_list[j][0])
                y_diff = abs(new_list[i][1] - new_list[j][1])
                if x_diff + y_diff <= 1000:
                    graph[i][j] = 1



    # >> 1. 플로이드 활용.
    # 정점k(== 편의점 1개)를 추가할때마다 D[i][j]를 구하는데,  k를 그럼 편의점개수까지 0부터 N까지 돌릴텐데,
    # 돌리면서 한 번이라도 D[0][N-1]이 존재하게 된다면 >> happy , 끝까지 안바뀌면 sad.

    cango = False

    for i in range(N+2):
        for j in range(N+2):
            if graph[i][j] == 0:
                graph[i][j] = 1000000



    for k in range(N+2):
        for i in range(N+2):
            if i == k:
                continue
            for j in range(N+2):
                if j==i or j==k:
                    continue

                temp = graph[i][k]+graph[k][j]
                graph[i][j] = min(temp, graph[i][j])



        if graph[0][N+1] != 1000000:
            print("happy")
            cango = True
            break


    if not cango:
        print("sad")


