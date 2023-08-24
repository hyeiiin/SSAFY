# import sys
# print(sys.maxsize) #9223372036854775807
T = int(input())

#swexpert는 sys 라이브러리를 못쓰게 해서 그냥 가장 큰값 하나를 max_size로 잡아주고
max_size = 9000000000000000000
for test_case in range(1, T+1):
    N = int(input())
    island_x = list(map(int, input().rstrip().split()))
    island_y = list(map(int, input().rstrip().split()))
    E = float(input())

    # 특정 정점에서 다음 정점으로 갈때 값은
    # a to b 일때, E*((island_x[a] - island_x[b])**2 + (island_y[a] - island_y[b])**2)

    #일단 노드 개수만큼 visited 깔아주고.
    visited = [False for _ in range(N)]
    min_edge = [max_size for _ in range(N)]
    adjMat = [[0 for _ in range(N)] for _ in range(N)]

    #각 노드 to 노드 인접행렬에 부담금 넣고
    for i in range(N):
        for j in range(N):
            adjMat[i][j] = E*((island_x[i] - island_x[j])**2 + (island_y[i] - island_y[j])**2)

    min = 0
    min_node = 0
    sum = 0

    #아무 노드 하나 잡고 0으로 박아놓고 시작.
    #prim.
    min_edge[0] = 0
    for i in range(N):
        min_node = -1
        min = max_size

        # 아직까지 한번도 방문 안했고, 그 중에서 가장 작은 노드를 찾아서
        for v in range(N):
            if not visited[v] and min > min_edge[v] :
                min_node = v
                min = min_edge[v]

        # 걔가 갖고있는 값을 그대로 sum에 더해주고. visited True 박고.
        visited[min_node] = True
        sum += min

        # 방금 선택된 노드와 인접한 노드들을 찾고,
        # 그 노드들 중에서 환경부담금 값(가중치)가 가장 작은애를 골라서 최솟값으로 선정.
        for v in range(N):
            if not visited[v] and adjMat[min_node][v] != 0 and min_edge[v] > adjMat[min_node][v]:
                min_edge[v] = adjMat[min_node][v]

        # for (v)가 끝나면 가장 작은 값이 min_edge[v]에 골라졌고, 아직 얘는 visited가 true가 아니기 때문에
        # for (i)가 다시 시작될때 방금 골라진 min_edge[v]가 sum에 더해지면서 계속 반복됨.


    print("#"+str(test_case)+" "+str(round(sum)))

