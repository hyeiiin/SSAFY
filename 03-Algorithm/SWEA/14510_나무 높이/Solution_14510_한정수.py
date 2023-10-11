T = int(input())
for test_case in range(1, T+1):
    N = int(input())
    arr = list(map(int, input().split()))

    # 입력받은 나무 중 가장 키 큰걸 찾아서 goal로 설정.
    arr.sort()
    goal = arr[-1]

    # (goal-임의의 나무의 키) 값을 저장.
    diff_arr = []
    for i in range(len(arr)):
        if arr[i] == goal:
            continue
        diff_arr.append(goal-arr[i])

    cnt_odd = 0
    cnt_even = 0
    result = 0

    for i in range(len(diff_arr)):
        # goal이 되기 위해 더해야하는 2의 개수 및 1의 개수를 각각 구해서
        # 총 +2의 개수와 +1의 개수를 각각 cnt_even, cnt_odd로 나타낸다.
        cnt_even += diff_arr[i] // 2
        cnt_odd += diff_arr[i] % 2

    # 나무에 최소 횟수로 물주기가 끝나는 경우는 다음과 같다.
    # 1. cnt_odd > cnt_even
    #   >> cnt_odd == 5, cnt_even == 3인 경우, 홀 짝 /홀 짝 /홀 짝 /홀 X /홀 와 같이, 홀수는 무조건 홀수날에 물주는 것 외에 방법이 없다.
    #      >> day = 1+ (cnt_odd -1)*2
    # 2. cnt_odd == cnt_even
    #   >> cnt_odd == 3, cnt_even == 3인 경우, 홀 짝 /홀 짝 /홀 짝/   딱 떨어진다.
    #      >> day = cnt_odd * 2 혹은 day = cnt_even * 2
    # 3. cnt_odd == cnt_even - 1
    #   >> cnt_odd == 3, cnt_even == 4인 경우, 홀 짝 /홀 짝 /홀 짝/X 짝/  1개 남은 짝수를 홀+홀로 나누는게 아니라 하루 쉬고 짝수날에 주는게 이득.
    #     >> day = cnt_even * 2

    # 그럼 cnt_odd < cnt_even -1 인 경우는?
    #  >> cnt_odd == 3, cnt_even == 5인 경우,
    #  >> 홀 짝/홀 짝/홀 짝/x 짝/x 짝/  >> 10일
    #  >> 홀 짝/홀 짝/홀 짝/홀 짝/홀  >> 9일.  즉, cnt_odd와 cnt_even의 개수차가 2개 이상 날 경우, cnt_even 1개를 cnt_odd 2개로 쪼개는게 이득이다.
    #    >> 즉, 위의 1. 2. 3. 중 하나를 만족할때까지 cnt_even -= 1, cnt_odd += 2를 반복하여 답을 구한다.
    while True:
        if cnt_odd >= cnt_even - 1:
            break

        elif cnt_odd < cnt_even - 1:
            cnt_even -= 1
            cnt_odd += 2

    if cnt_odd + 1 == cnt_even:
        result = cnt_even * 2
    elif cnt_odd == cnt_even:
        result = 2*cnt_odd
    else:
        result = 1+2*(cnt_odd-1)

    print("#" + str(test_case) + " " + str(result))

