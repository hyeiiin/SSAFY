from itertools import combinations

def combi(nums):

    cnt = 0
    for i in range(1, M+1):
        com = list(combinations(nums, i))

        for num in com:
            tmp_cnt = 0

            #C를 초과하는 경우
            if sum(num) > C:
                continue

            for j in range(i):
                tmp_cnt += num[j-1]**2

            cnt = max(cnt, tmp_cnt)

    return cnt

T = int(input())
for tc in range(1, T+1):
    #N: 벌통의 크기, M: 선택할 수 있는 벌통의 개수, C: 꿀을 채취할 수 있는 최대 양
    N, M, C = map(int,input().split())

    arr = [list(map(int,input().split())) for _ in range(N)]

    #[[6, 1, 9, 7], [9, 8, 5, 8], [3, 4, 5, 3], [8, 2, 6, 7]]

    #일꾼 수: 2
    #각 일꾼은 가로로 연속되도록 M개의 벌통을 선택, 겹치면 안됨
    #하나의 벌통에서 채취한 꿀은 하나의 용기에 담아야 함
    #두 일꾼이 채취할 수 있는 꿀의 최대 양 = C

# =====================첫번째 일꾼==========================================
    first_nums = []
    first_idx = []
    first_cnt = 0

    for i in range(N):
        for j in range(N-M+1):

            tmp_idx = []
            tmp_nums = []
            tmp_cnt = 0
            sums = 0
            for k in range(M):
                tmp_idx.append((i, j+k))
                tmp_nums.append(arr[i][j+k])
                sums += arr[i][j+k]

            if sums <= C:
                for num in tmp_nums:
                    tmp_cnt += num**2

            elif sums > C:
                tmp_cnt = combi(tmp_nums)

            if first_cnt < tmp_cnt:
                first_cnt = tmp_cnt
                first_nums = tmp_nums
                first_idx = tmp_idx

    while first_idx:
        r, c = first_idx.pop()
        arr[r][c] = -1

# =====================두번째 일꾼==========================================
    second_nums = []
    second_idx = []
    second_cnt = 0

    for i in range(N):
        for j in range(N-M+1):

            tmp_idx = []
            tmp_nums = []
            tmp_cnt = 0
            sums = 0
            for k in range(M):

                tmp_idx.append((i, j + k))
                tmp_nums.append(arr[i][j + k])
                sums += arr[i][j + k]
            if -1 in tmp_nums:
                continue

            if sums <= C:
                for num in tmp_nums:
                    tmp_cnt += num**2

            elif sums > C:
                tmp_cnt = combi(tmp_nums)

            if second_cnt < tmp_cnt:
                second_cnt = tmp_cnt
                second_nums = tmp_nums
                second_idx = tmp_idx

    res = first_cnt + second_cnt

    print("#{} {}".format(tc, res))






