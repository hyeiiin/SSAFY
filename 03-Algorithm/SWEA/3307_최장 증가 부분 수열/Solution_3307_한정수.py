T = int(input())

for test_case in range(1, T+1):
    N = int(input())
    arr = list(map(int, input().split()))
    arr_result = [1 for _ in range(N)]
    # 1 3 2 5 4
    # 1
    # 1+1 1
    # 1+1 1 1+1
    # 2+1 1+1

    # arr_result[i] 는, arr[0]부터 arr[i]까지 계속 증가하고 있는 개수.
    # 즉, arr[i]는 arr[0]~arr[i-1]보다 커야 하므로,
    # arr[0]~arr[i-1] < arr[i] 인 개수.
    # 근데 이걸 일일이 카운팅하면 부분합 문제에 부딪히니까, 부분합으로 이것들을
    # 구할 수 있게, arr_result[i] = arr_result[k] + 1 형식으로.(단, 0<= k < i)

    # dp1
    # for i in range(N):
    #     for j in range(i):
    #         if arr[j] < arr[i]:
    #             arr_result[i] = max(arr_result[i], arr_result[j] + 1)
    #
    #
    # print("#"+str(test_case)+" "+str(max(arr_result)))


    # dp2 이진 검색????
    # 최초로 arr[0]을 arr_result2에 집어넣고,
    # 각각의 arr[i] 에 대해( 단, i > 0, 0번째는 제외) arr_result2를 처음부터 끝까지 1부터 끝까지 탐색을 하는데,
    #  >> arr[i]에 대해서,
    #   >>  arr_result[j] >= arr[i] 인 값이 나올때까지 찾는다.
    #      >> 1. arr_result[j] >= arr[i] 인 값을 찾았다면, 그 즉시 arr_result[j]는 arr[i]로 대체 하고 종료 >> 다음 arr[i] 시작
    #      >> 2. arr_result[j] < arr[i]라면 다음 j로 넘어가고, 만약 arr_result[j]를 다 탐색했다면 arr[i]를 arr_result2에 추가한다.
    # 최종 arr_result2 길이가 곧 답
    arr_result2 = []
    arr_result2.append(arr[0])
    for i in range(1,N):
        for j in range(len(arr_result2)):
            if arr_result2[j] < arr[i]:
                if j == len(arr_result2) -1:
                    arr_result2.append(arr[i])
                    break
                else:
                    continue
            else:
                arr_result2[j] = arr[i]
                break
    print("#"+str(test_case)+" "+str(len(arr_result2)))



