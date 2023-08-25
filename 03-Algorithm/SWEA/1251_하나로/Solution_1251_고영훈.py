from heapq import heappop, heappush, heapify
from itertools import combinations


def solution():
    def prim(start):
        def extract_min():
            min_u = N
            min_w = weight_limit
            for u in range(N):
                if not visited[u] and min_edge[u] < min_w:
                    min_u = u
                    min_w = min_edge[u]
            return min_u

        # 인접 행렬
        adj_mat = [[0] * N for _ in range(N)]
        for (u, x1, y1), (v, x2, y2) in combinations(zip(range(N), X, Y), 2):
            w = (x1 - x2) ** 2 + (y1 - y2) ** 2
            adj_mat[u][v] = w
            adj_mat[v][u] = w
        # MST
        total_weight = 0
        visited = [False] * N
        min_edge = [weight_limit] * N
        min_edge[start] = 0
        for count in range(1, N + 1):
            u = extract_min()
            visited[u] = True
            total_weight += min_edge[u]
            if count == N:
                break
            for v in range(N):
                if u != v and not visited[v] and adj_mat[u][v] < min_edge[v]:
                    min_edge[v] = adj_mat[u][v]
        return total_weight

    def prim_pq(start):
        # 인접 리스트
        adj_list = [[] for _ in range(N)]
        for (u, x1, y1), (v, x2, y2) in combinations(zip(range(N), X, Y), 2):
            w = (x1 - x2) ** 2 + (y1 - y2) ** 2
            adj_list[u].append((w, v))
            adj_list[v].append((w, u))
        # MST
        total_weight = 0
        visited = [False] * N
        visited[start] = True
        pq = adj_list[start]
        heapify(pq)
        count = 1
        while True:
            w, u = heappop(pq)
            if visited[u]:
                continue
            visited[u] = True
            total_weight += w
            count += 1
            if count == N:
                break
            for edge in adj_list[u]:
                _, v = edge
                if not visited[v]:
                    heappush(pq, edge)
        return total_weight

    weight_limit = 2_000_000_000_000
    N = int(input())
    X = map(int, input().split())
    Y = map(int, input().split())
    E = float(input())
    weight = prim(0)
    # weight = prim_pq(0)
    return round(E * weight)


def main():
    for C in range(1, int(input()) + 1):
        print(f'#{C} {solution()}')


main()
