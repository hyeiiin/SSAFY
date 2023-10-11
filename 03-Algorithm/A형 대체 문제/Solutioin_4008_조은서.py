# SWEA 4008. 숫자 만들기

t = int(input())

def dfs(idx, res):
    global max_value, min_value

    if idx == n: # 입력 받은 숫자 개수만큼 재귀를 돌았으면 최댓값, 최솟값 구하기
        max_value = max(max_value, res)
        min_value = min(min_value, res)
        return

    for i in range(4):
        if opers[i] <= 0: # 연산자가 없으면 넘어가기
            continue
        opers[i] -= 1 # 연산자 사용
        if i == 0:
            new_res = res + numbers[idx]
        elif i == 1:
            new_res = res - numbers[idx]
        elif i == 2:
            new_res = res * numbers[idx]
        elif i == 3:
            new_res = int(res / numbers[idx])
        dfs(idx+1, new_res) # 다음 연산자 사용하기 위해 재귀 호출
        opers[i] += 1 # 연산자 사용했던거 다시 복원 시키기

for tc in range(1, t+1):
    n = int(input())

    opers = list(map(int, input().split()))
    numbers = list(map(int, input().split()))

    max_value = -1e9
    min_value = 1e9

    dfs(1, numbers[0])

    print("#{} {}".format(tc, max_value - min_value))
