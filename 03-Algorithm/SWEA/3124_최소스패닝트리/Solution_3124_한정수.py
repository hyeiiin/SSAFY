def find(a):
    if parents[a] == a:
        return a
    parents[a] = find(parents[a])
    return parents[a]


def union(a, b):
    aRoot = find(a)
    bRoot = find(b)
    if aRoot == bRoot:
        return False
    parents[bRoot] = aRoot
    return True


"""Main 시작"""
T = int(input())

for test_case in range(1, T+1):
    V, E = map(int, input().rstrip().split())

    parents = [i for i in range(V+1)]
    edge = [[] for _ in range(E)]
    for i in range(E):
        # start, end, weight = map(int, input().rstrip().split())
        edge[i] = list(map(int, input().rstrip().split()))

    # weight 기준으로 정렬
    edge.sort(key=lambda x: x[2])

    result = 0
    cnt = 0
    for e in edge:
        if union(e[0], e[1]):
            result += e[2]
            cnt += 1;
            if cnt == V-1:
                break

    print("#"+str(test_case)+" "+str(result))


