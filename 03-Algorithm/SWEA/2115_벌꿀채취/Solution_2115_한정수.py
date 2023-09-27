T = int(input())

def pick_best(select):

    DP = [[0 for _ in range(C+1)]for _ in range(M+1)]

    cur_c = C
    for i in range(1, M+1):
        for j in range(1, C+1):
            if select[i-1] > j:
                DP[i][j] = DP[i-1][j]
            else:
                DP[i][j] = max(select[i-1]**2 + DP[i-1][j-select[i-1]], DP[i-1][j])


    return DP[M][C]

for test_case in range(1, T+1):
    max_result = -1
    N, M, C = map(int, input().split())
    arr = [[]for _ in range(N)]
    for i in range(N):
        arr[i] = list(map(int, input().split()))

    # 쉽게 보면 C라는 크기를 가진 가방에 최대한 우겨넣기.
    # 각각의 M에 대하여,
    #   M1 <= C 선택
    #   M2 <= C-M1 선택
    #   M3 <= C-M1-M2 선택.....

    # M개씩 묶어 놓은 배열을 만들어서, 각 영역마다 최대 이익을 우겨 넣고, 그 중 가장 큰 애들 2개 뽑으면 끝.
    select = []
    value_result = [[0 for _ in range(N-M+1)]for _ in range(N)]
    for i in range(N):
        for j in range(N-M+1):
            for m in range(M):
                select.append(arr[i][j+m])

            value_result[i][j] = pick_best(select)
            select = []

    max_value_result = []
    for i in range(N):
        max_value_result.append(max(value_result[i]))

    max_value_result.sort()
    print("#"+str(test_case)+" "+str(max_value_result[-1] + max_value_result[-2]))

    # for i in value_result:
    #     print(i)

    # print("==============")




