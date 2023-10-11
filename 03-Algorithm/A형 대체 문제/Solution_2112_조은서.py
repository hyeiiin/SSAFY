# SWEA 2112 보호필름, 파이썬 15초(50개 테스트케이스)

def test():
    for col in range(w):
        cnt = 1
        for r in range(1, d):
            if film[r][col] == film[r-1][col]:
                cnt += 1
            else:
                cnt = 1
            if cnt >= k:
                break
        if cnt < k:
            return False
    return True

# 막의 개수 cnt와 depth가 같아질 때까지 재귀 호출
# init은 약품을 주입할 막의 위치
def change(depth, init, cnt):
    global min_drug

    if depth >= min_drug: # 최소 주입 횟수보다 depth가 큰 경우 가지치기
        return

    if depth == cnt: # 약품을 주입할 막의 개수와 depth가 같아지면 성능 테스트
        if test():

            min_drug = min(min_drug, depth)
        return

    for i in range(init, d):
        for row in range(2): # a를 넣을건지 b를 넣을건지
            isSelected.append(i)
            film[i] = drugs[row]
            change(depth+1, i+1, cnt)
            film[i] = film_copy[i]
            isSelected.pop()

t = int(input())

for tc in range(1, t+1):
    # d : 보호필름 두께 (높이)
    # w : 가로 크기
    # k : 합격 기준
    d, w, k = list(map(int, input().split()))

    # 특성 A는 0, B는 1
    film = [list(map(int ,input().split())) for _ in range(d)]
    film_copy = [ f[:] for f in film ]
    drugs = [[0] * w, [1] * w]
    # 약품 처리하지 않아도 되는 경우 바로 0 출력
    if test():
        print("#{} {}".format(tc, 0))

    # 약품 처리 해야하는 경우
    else:
        min_drug = 99
        isSelected = []
        for cnt in range(1, d+1):
            change(0, 0, cnt)

        print("#{} {}".format(tc, min_drug))
