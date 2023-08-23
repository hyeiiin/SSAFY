import sys
sys.setrecursionlimit(100000)
N = int(sys.stdin.readline())

temp_people = list(map(int, sys.stdin.readline().rstrip().split()))
people = []
people.append(0)
for i in temp_people:
    people.append(i)

graph = [[] for _ in range(N+1)]

for i in range(1, N+1):
    graph[i] = list(map(int, sys.stdin.readline().rstrip().split()))
    graph[i] = graph[i][1:]

# print(graph)
# 일단 주어진 간선은 양방향이 가능.

# 최대 구역 개수는 10개. 즉, 가능한 구역 할당 경우의수는 2^10 = 1024.
# 서로소로 해결이 가능한가?
# 가중치가 정점에 할당되어 있음.


# A. 모든 경우의 수 다 박고 끊겼는지 판단 후 두 구역의 차 판단.
# B. 혹은, 애초에 구역이 끊길일이 없게 배치를 한다.
#  *** 구획 나누기가 불가능한 경우도 있다는데 그게 되나???? N이 1이면 그러긴 하겠지
# A-1. 모든 노드의 0,1 경우의수 를 다 박고,
#   >> 각 경우의 수마다 dfs로 시작할때 0이었으면 1을 읽을때까지 계속 들어가고,
#      >> 마찬가지로 1에 대해서도 그렇게하고.
#         >>0과 1을 한번씩 진행해서 모든 노드가 visited == true면 ok. 하나라도 false면 잘못됨.

visited = [False for i in range(N+1)]
visited[0] = True
min_diff = sys.maxsize

# 입력 완료===========

def dfs(node, cur_color, dfs_sum, visited, color):
    # print("dfs node : ",node, "sum : ", dfs_sum)
    visited[node] = True

    # 현재 호출된 노드의 모든 인접 노드에 대해.
    for n in graph[node]:
        if not visited[n] and color[n-1] == cur_color:
            # 그 인접 노드가 방문한 적 없으면서 color가 최초 시작지점 color가 동일할때
            dfs_sum = dfs(n, cur_color, dfs_sum, visited, color)
        else:
            #즉, visited[n] = True거나, color가 다를 경우
            continue # 인접한 다음 노드로 넘기고

    return dfs_sum + people[node]  # 현재 노드에서 탐색이 끝나면 현재 노드의 weight만큼 더하고 리턴.
# dfs 구현====================끝


# main 시작=======================

# 각 노드의 모든 색깔의 경우의수에 해당하는 부분집합 생성.
# ==============파이썬 만세====================
from itertools import product
comb = [0, 1]
partial_list = list(product(comb, repeat=N))

for color_list in partial_list:
    # 각각의 노드별 색깔 조합(부분집합) 에 대해,
    # 0이 아에 없거나, 1이 아에 없으면 안됨.
    if 1 not in color_list or 0 not in color_list:
        continue
    # print(color)
    # 현재 색 조합으로 dfs 실행.
    # 방문 배열 초기화
    visited = [False for _ in range(N + 1)]
    visited[0] = True
    sum_a = -1
    sum_b = -1
    count = 0
    # 모든 노드에 대해서,
    for i in range(1, N + 1):
        # 방문하지 않았다면 dfs 시작.
        # 이미 1번 dfs를 갔다왔다면, 첫번째 노드와 동일하면서 같은 구역인 애들은 다 visited 됬을거임.
        # 그러므로 2번째 dfs때는 반드시 첫번째 노드와 다른 색깔일 것이며,
        # 2번, 즉 빨강/파랑 에 대해 각각 1번씩 dfs를 돌았다면 break
        if not visited[i]:
            count += 1
            temp_sum = dfs(i, color_list[i-1], 0, visited, color_list)
            if count == 1:
                sum_a = temp_sum
            elif count == 2:
                sum_b = temp_sum
                break

    if False in visited:
        # 2번 돌렸는데 하나라도 방문 안된게 있으면 잘못된거임.
        continue
    else:
        # 최솟값 갱신
        # global min_diff
        temp = abs(sum_a - sum_b)
        min_diff = min(min_diff, temp)

if min_diff == sys.maxsize:
    # 한번도 구획 결정이 되지 않은 경우.
    print(-1)
else:
    print(min_diff)




