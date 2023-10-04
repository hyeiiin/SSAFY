""" SWEA_"1263_사람 네트워크2"""
from pprint import pprint

TC = int(input())

for tc in range(1, TC + 1):
    # 입력 숫자 저장
    data = list(map(int, input().split()))

    N = data[0]  # 첫 숫자 = 사람 수
    lst = []  # 사람들의 간선을 담는 리스트

    # 입력 숫자를 사람 숫자만큼 나누어 lst[] 만듦
    idx = 1
    for i in range(N):
        lst.append(data[idx: idx + N])
        idx += N

    # 플로이드-워샬 적용을 위해 간선이 없는 부분을 INF로 할당
    for r in range(N):
        for c in range(N):
            if not lst[r][c]:
                lst[r][c] = float('inf')

    # pprint(lst, width=30)

    """플로이드-워샬 알고리즘 적용"""
    # 각 회차 별 중간 노드가 될 노드를 k로 지정
    for k in range(N):
        for i in range(N):
            for j in range(N):
                # 문제에서 자기 자신의 사이클은 없다고 함.
                if i != j:
                    # 각 노드별 모든 거리를 조사
                    # 중간 노드로 거쳐서 가는 경로와, 아닌 경로간의 최솟값 찾기
                    lst[i][j] = min(lst[i][j], lst[i][k] + lst[k][j])

    # pprint(lst, width=30)

    res = float('inf')

    # 문제에서 구하는 CC의 최솟값을 찾음
    for r in range(N):
        sum = 0
        for c in range(N):
            if lst[r][c] != float('inf'):
                sum += lst[r][c]

        res = min(res, sum)

    print(f'#{tc} {res}')
