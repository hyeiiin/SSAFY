""" SWEA_5658_보물상자 비밀번호 """
from collections import deque


def solution():
    N, K = map(int, input().split())
    qu = deque()  # 입력
    lst = []  # 생성 가능한 수를 저장

    # 입력받은 문자열을 큐에 저장
    for s in input().strip():
        qu.append(s)

    nums = N // 4  # 변의 숫자 개수

    # 총 (변의 숫자 개수 - 1) 번 회전
    for _ in range(N // 4):
        idx = 0
        while idx < N:
            temp = ""  # 각 변의 숫자를 저장
            for i in range(idx, idx+nums):
                temp += qu[i]

            # 생성 가능한 모든 수를 list에 저장
            lst.append(temp)
            idx += nums  # 다음 범위

        qu.rotate(1)  # 한 사이클이 끝나면 시계방향으로 한 칸 회전

    # set으로 중복을 제거 후 다시 list로 원상 복귀
    lst = list(set(lst))
    lst = sorted(lst, reverse=True)  # 내림 차순 나열

    # K번째로 큰 16진수를 10진수로 변환하여 반환
    return int(lst[K-1], 16)  


def main():
    for tc in range(1, int(input()) + 1):
        print(f"#{tc} {solution()}")


main()
