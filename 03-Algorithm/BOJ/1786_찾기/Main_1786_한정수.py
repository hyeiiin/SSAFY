T = input().rstrip()
P = input().rstrip()

# KMP. 완전탐색으로 하면 오래걸리니니까 진짜 조금이라도 효율을 높이기 위해서
# 'P'라는 패턴 안의 패턴이 존재하는지 찾아보는 것. 패턴 안의 패턴(규칙/반복)을 찾기.
# 그 과정의 결과가 부분일치 테이블 배열.

S = P
i = 1
j = 0
cnt = 1
# 부분일치 테이블 배열 생성
pi = [0 for _ in range(len(P))]

for i in range(1, len(P)):

    while j > 0 and P[i] != P[j]:
        # j가 0이 될때까지 >> 접두사/접미사가 같은 구간이 없어질때까지 접두사/접미사가 같은 구간을 다 체크해봄
        j = pi[j - 1]

    if P[i] == P[j]:
        j += 1
        pi[i] = j


# print(pi)
# 부분일치 테이블 배열을 이용해 문자열 매칭
i = 0
j = 0
result = []
result_cnt = 0

for i in range(len(T)):
    while j > 0 and T[i] != P[j]:
        j = pi[j-1]

    if T[i] == P[j]:
        if j == (len(P)-1):
            result.append(i-j +1)
            result_cnt += 1
            # j를 0이 아니라, j = pi[패턴길이-1] 을 해줘야함.
            # 1251125112
            # 125112 의 예시의 경우,
            # 125112
            #     125112  처럼, 첫번째 패턴인 125112(i==0)의 끝부분이랑
            #                  두번째 패턴인 125112(i==4)의 시작 부분이랑 겹치는 케이스가 있음.
            j = pi[j]
        else:
            j += 1

print(result_cnt)
for res in result:
    print(res)




