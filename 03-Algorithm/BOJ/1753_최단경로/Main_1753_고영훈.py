from heapq import heappop, heappush
from sys import stdin, stdout


def main():
    def dijkstra(start):
        dists[start] = 0
        pq = [(0, start)]
        while pq:
            d, u = heappop(pq)
            if d > dists[u]:
                continue
            for v, w in adj_list[u]:
                nd = d + w
                if nd < dists[v]:
                    dists[v] = nd
                    heappush(pq, (nd, v))

    V = int(stdin.buffer.readline().split()[0])
    K = int(stdin.buffer.readline())
    adj_list = [[] for _ in range(V + 1)]
    for line in stdin.buffer.read().splitlines():
        u, v, w = map(int, line.split())
        adj_list[u].append((v, w))

    INF = 1_000_000
    dists = [INF] * (V + 1)
    dijkstra(K)
