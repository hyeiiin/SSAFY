import sys
import heapq
V, E = map(int, sys.stdin.readline().split())
K = int(sys.stdin.readline())

# 인접행렬, 리스트로 다 해봤는데
# 인접행렬은 메모리가 터지고
# 인접리스트는 시간이 터짐.
# >> 인접리스트로 힙 써서 시간 줄이기

# adjMat = [[0 for _ in range(V+1)] for _ in range(V+1)]
adjList = [[] for _ in range(V+1)]
heap = []

# visited = [False for _ in range(V+1)]
max_size = sys.maxsize
min_weight = [max_size]*(V+1)


def dijkstra(start):
    min_weight[start] = 0
    heapq.heappush(heap, (0, start))
    while heap:
        weight, vertex = heapq.heappop(heap)

        # 현재 가중치가 원래 이 노드에 할당된 최솟값보다 큼 >> 다른 경로로 가는게 더 싼 경우
        if weight > min_weight[vertex]:
            continue

        for ver, wei in adjList[vertex]:
            temp = wei + weight
            if min_weight[ver] > temp:
                min_weight[ver] = temp
                heapq.heappush(heap, (temp, ver))



for i in range(E):
    u, v, w = map(int, sys.stdin.readline().split())
    adjList[u].append((v, w))


dijkstra(K)


for i in range(1, V+1):
    if min_weight[i] == max_size:
        print("INF")
    else:
        print(min_weight[i])







