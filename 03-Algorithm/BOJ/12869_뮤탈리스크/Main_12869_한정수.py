import heapq
from itertools import permutations
from collections import deque
N = int(input())
arr = list(map(int, input().split()))
arr.extend([0,0]) # 3개인지, 2개인지 1개인지 모르는데, 일단 [0,0]을 뒤에 붙여서 arr 개수를 3개 이상으로 유지하고,
                  # 1번 2번 3번 scv를 부를때 arr[0], arr[1], arr[2] 이렇게 부르도록.
cnt = 0
heap = []

damage = [9, 3, 1]

perm_list = list(permutations(damage, r=3))
# 각각의 모든 공격조합.

# SCV 체력을 60으로 싹 깔아놓고 거기서 하나씩 뺄까?
# 문제는 각각의 조합에 대해서 싹 빼야되는데.
# scv 는 최대 3마리. 그럼 각 scv의 체력 3개를 한꺼번에 싹 깔아놓고 거기서 모든 조합에 대해서 빼면서?

# scv[61][61][61]로 만들고, 모든 배열을 -1로 채운다음에  > 너무 많은데, ixjxk 크기로 만들까? 상관은 없겠다.
# 최초로 scv[i][j][k]를  100으로 채우고.
# i,j,k != 100 이면, 모든 공격조합에 대해 i, j, k 즉 피통을 까고, 그 피통에 해당하는 ni, nj, nk를 scv[ni][nj][nk] 위치에서 최솟값 갱신을 한다.

# 즉, scv[i][j][k]의 값은 현재 scv의 체력 순서가(i, j, k)가 되기까지 뮤탈이 공격한 횟수를 의미.
#   >> 최종적으로 scv[0][0][0] >> scv가 다 죽었을때 뮤탈이 공격한 횟수를 구하면 됨.

scv = [[[100 for _ in range(arr[2]+1)] for _ in range(arr[1]+1)] for _ in range(arr[0]+1)]
scv[arr[0]][arr[1]][arr[2]] = 0

for i in range(arr[0], -1, -1):
    for j in range(arr[1], -1, -1):
        for k in range(arr[2], -1, -1):
            # 현재 scv의 체력 순서쌍에 대해 그 값이 최초값 100이 아니라면 1번이라도 공격이 실행됬다는 뜻.
            if scv[i][j][k] != 100:
                # 모든 공격 조합에 대해서 싹 다 테스트
                for perm in perm_list:
                    ni = i - perm[0]
                    if ni < 0:
                        ni = 0

                    nj = j - perm[1]
                    if nj < 0:
                        nj = 0

                    nk = k - perm[2]
                    if nk < 0:
                        nk = 0
                    # if문으로 0보다 작을때 0으로 안하면 OutofIndex 발생.

                    # 공격횟수 최솟값 갱신. 현재 공격이 최솟값인지, 아니면 다른 조합으로 이미 더 낮은 값이 나왔는지.
                    scv[ni][nj][nk] = min(scv[ni][nj][nk], scv[i][j][k] + 1)

# 마지막에 scv가 다 잡혔을때 출력
print(scv[0][0][0])








