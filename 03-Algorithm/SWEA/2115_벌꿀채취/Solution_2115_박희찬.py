""" SWEA_2115_벌꿀채취"""

"""
동작 과정
1. findHoneyBox() : 각 행마다 연속된 벌통 M개 뽑음
2. subset() : 뽑은 벌통에서 합이 C이하로 채취 가능한 부분 집합 구함.
3. benefit() : 각 부분집합의 벌꿀 수익을 계산하여 저장.
4. solve[] : 각 행에서 채취할 수 있는 벌꿀 경우의 수의 수익을 담은
             rowSum[]에서 최댓값을 저장 = N행의 수익 최댓값
5. 결과 출력 : solve[]에 모인 최대 수익 2개(=일꾼 수)를 합하여 출력 
"""


# 각 행마다 연속된 벌통 M개 뽑음
def findHoneyBox(row):
    global rowSum

    # Pruning : 모든 행을 탐색했다면 재귀 종료.
    if row == N:
        return

    # 행마다 연속된 M개의 벌통의 모든 경우를 찾음.
    idx = 0
    while idx + M <= N:
        # arr = lst[:]
        arr = lst[row][idx:idx + M]
        subset(arr)
        idx += 1

    # 행마다 나올 수 있는 모든 수익(=rowSum[]) 중 최댓값만 저장
    solve.append(max(rowSum))
    rowSum = []  # 다음 행 계산을 위해 비워줌
    findHoneyBox(row + 1)  # 다음 행으로 재귀 호출


# 부분 집합 : 연속된 M개의 벌통 중, C를 초과하지않을 벌꿀 찾기
def subset(arr):
    temp = []
    for i in range(1, 1 << M):
        subTemp = []  # M개 벌통의 부분 집합 찾기
        for j in range(M):
            if i & (1 << j):
                subTemp.append(arr[j])

        temp.append(subTemp)  # 모든 부분 집합을 저장

    # C를 초과하지 않는 경우를 체크하기 위한 리스트
    check = [False] * (len(temp))
    for idx, sub in enumerate(temp):
        # 부분집합의 원소 합이 C를 초과하지 않으면 채취 가능함.
        if sum(sub) <= C:
            check[idx] = True

    # 위에서 선별한 tmep를 수익 계산 시킴
    benefit(temp, check)
    return


# 채취 가능한 경우의 벌꿀 수익 계산
def benefit(temp, check):
    global rowSum

    cost = float('-inf')
    for i in range(len(check)):
        s = 0  # 임시 합

        if check[i]:  # True = 채취가 가능한 벌꿀
            # 꿀의 양으로 수익 계산
            for j in temp[i]:
                s += j ** 2

            # 계산한 수익 중 최댓값을 저장
            cost = max(cost, s)

    # M개의 벌통의 가짓수에서 각각의 최댓값을 저장함.
    rowSum.append(cost)


""" Main """
TC = int(input())
for tc in range(1, TC + 1):
    N, M, C = map(int, input().split())
    lst = [list(map(int, input().split())) for _ in range(N)]
    rowSum = []
    solve = []

    findHoneyBox(0)

    # 행마다 벌꿀 최대 수익값을 높은 순대로 정렬하여 최고 2개만 합산하여 출력.
    solve.sort(reverse=True)
    print(f'#{tc} {solve[0] + solve[1]}')
