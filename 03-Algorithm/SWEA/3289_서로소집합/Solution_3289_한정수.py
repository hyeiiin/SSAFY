
T = int(input())


def find(a):
    if parents[a] == a:
        return a
    parents[a] = find(parents[a])
    return parents[a]


for test_case in range(1,T+1):
    result_str = "#"+str(test_case)+" "
    N, M = map(int, input().rstrip().split())

    # 일단 N이하의 자연수에 대해 전부 자기 자신을 부모로 세팅.
    parents = [i for i in range(N+1)]

    for i in range(M):
        mode, a, b = map(int, input().rstrip().split())
        if mode == 0:
            # 0을 읽으면 a, b를 union 하라는거
            aRoot = find(a)
            bRoot = find(b)

            if(aRoot != bRoot):
                parents[bRoot] = aRoot

        else:
            # mode == 1
            # 1을 읽으면 a, b가 같은 집합에 있는지. 같은 집합이면 1, 아니면 0
            if find(a) == find(b):
                result_str += str(1)
            else:
                result_str += str(0)

    print(result_str)

