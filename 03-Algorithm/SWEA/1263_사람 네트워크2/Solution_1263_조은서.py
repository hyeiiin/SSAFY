# 사람 네트워크2

import sys
input = sys.stdin.readline

T = int(input())

for tc in range(1, T+1):
    input_arr = list(map(int, input().split()))
    N = input_arr[0]
    graph = []

    for i in range(1, N+1):
        graph.append(input_arr[N*i-(N-1):N*i+1])


    for i in range(N):
        for j in range(N):
            if i != j and graph[i][j] == 0:
                graph[i][j] = int(1e9) # 다른 노드인데 0인 곳은 경로가 없다는 뜻이므로 10억으로 설정

    # 플로이드 워셜
    for k in range(N):
        for i in range(N):
            for j in range(N):
                graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])


    answer = int(1e9)
    for i in range(N):
        answer = min(answer, sum(graph[i]))


    print("#{} {}".format(tc, answer))


# 5 0 1 1 0 0 / 1 0 1 1 1 / 1 1 0 0 0 / 0 1 0 0 0 / 0 1 0 0 0
# => 사람수 행 우선으로 주어진 인접 행렬(사람수 * 사람수)
# i = 1 2 3 4 5
# start: 1 6 11 16 21 : Ni-4
# end : 6 11 16 21 26 : Ni+1

#    1 2 3 4 5
# ㅡ ㅡ ㅡ ㅡ ㅡ
# 1| 0 1 1 0 0
# 2| 1 0 1 1 1
# 3| 1 1 0 0 0
# 4| 0 1 0 0 0
# 5| 0 1 0 0 0



#0 1 0
#1 0 1
#0 1 0

# 0 1 2
# 1 0 1