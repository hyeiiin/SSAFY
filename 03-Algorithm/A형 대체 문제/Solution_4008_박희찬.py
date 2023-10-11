''' SWEA_4008_숫자 만들기 '''
'''
TC 10번 기준의 재귀 호출 횟수
기존 순열 방식 -> 본 DFS 풀이 = 39,916,800 -> 46,123
따로 순열을 적용하기보단, 아이디어를 잘 생각하여 푸는것이 좋음.
'''


def dfs(index, cal):
    global operSum, big, small, cc
    # print(cal)
    cc += 1

    if index == len(lst):
        # 모든 수의 계산이 끝나면 최대, 최소 갱신
        big = max(big, cal)
        small = min(small, cal)
        return

    # 연산자의 개수만큼 반복
    for cur in range(4):
        if operand[cur]:  # 입력받은 연산자의 개수가 1개 이상일 경우
            operand[cur] -= 1  # backTracking

            # 정해진 연산자에 맞춰 연산한 후 재귀 호출
            if cur == 0:
                dfs(index + 1, cal + lst[index])
            elif cur == 1:
                dfs(index + 1, cal - lst[index])
            elif cur == 2:
                dfs(index + 1, cal * lst[index])
            else:
                dfs(index + 1, int(cal / lst[index]))

            operand[cur] += 1  # backTracking


''' Main '''
for tc in range(1, int(input()) + 1):
    N = int(input())

    # 각 연산자의 개수를 저장할 리스트
    operand = list(map(int, input().split()))

    lst = list(map(int, input().split()))  # 계산할 숫자들

    # 모든 연산 결과에 대한 최댓값, 최솟값
    big, small = float('-inf'), float('inf')

    cc = 0
    dfs(1, lst[0])

    print(f'#{tc} {big - small}')
