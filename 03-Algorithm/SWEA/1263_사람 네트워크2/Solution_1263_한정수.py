T = int(input())

for test_case in range(1, T+1):
    input_list = list(map(int, input().split()))
    N = input_list[0]
    input_list = input_list[1:]
    arr = [[] for _ in range(N)]
    for i in range(N):
        arr[i] = input_list[N*i:N*i+N]

    # 최초로, arr[0][0], arr[1][1]... 등은 놔두고, arr[i][j]이 0일경우, 큰수를 할당. N은 1000까지니까 1~1000까지의 합은 대충 1001*500 < 1,000,000
    for i in range(N):
        for j in range(N):
            if i == j:
                continue
            if arr[i][j] == 0:
                arr[i][j] = 1000000

    # 플루이드.
    for k in range(N):
        for i in range(N):
            if i == k:
                continue
            for j in range(N):
                if j == i or j == k:
                    continue
                arr[i][j] = min(arr[i][k] + arr[k][j], arr[i][j])



    sum_list = [sum(arr[i]) for i in range(N)]
    print("#"+str(test_case)+" "+str(min(sum_list)))