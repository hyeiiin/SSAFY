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

    for i in range(N):
        for j in range(i):
            if arr[j] < arr[i]:
                arr_result[i] = max(arr_result[i], arr_result[j] + 1)


    print("#"+str(test_case)+" "+str(max(arr_result)))





