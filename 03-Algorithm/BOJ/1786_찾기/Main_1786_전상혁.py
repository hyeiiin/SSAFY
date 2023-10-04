T = input()
P = input()

# 첫째줄에 P가 몇번 나타나는지 횟수 출력
# 둘째줄에 그 위치를 차례대로 공백으로 구분하여 출력
#ABC ABCDAB ABCDABCDABDE
#ABCDABD

#비교할 문자열과 같은 길이의 리스트 생성
p_lst = [0] * len(P)

j = 0
#접두사 확인하여 같은 인덱스를 가진 문자가 접미사에서 몇 번째 원소인지를 체크
for i in range(1, len(P)):

    while j > 0 and P[i] != P[j]:
        j = p_lst[j-1]

    if P[i] == P[j]:
        j += 1
        p_lst[i] = j

#p_lst = [0, 0, 0, 0, 1, 2, 0]

j = 0
cnt = 0
res_idx = []
#i = 문자열 T의 문자를 가르키는 용도
#j = 문자열 P의 문자를 가르키는 용도
for i in range(len(T)):
    #0에서 각각 비교, 일치하지 않으면 일치할 때까지 p_lst[j-1] 값을 j에 할당
    while j > 0 and T[i] != P[j]:
        j = p_lst[j-1]

    #문자가 일치한 경우
    if T[i] == P[j]:
        #P문자열을 모두 비교했을 때, 일치한 경우
        if j == len(P)-1:
            cnt += 1
            #P가 나타나는 위치 인덱스를 저장
            res_idx.append(i-len(P)+2)
            #그 후 p_lst[j]를 j에 할당하여 다시 탐색
            j = p_lst[j]
        #문자열이 일치하지 않으면 j+1하여 다시 탐색
        else:
            j += 1

#첫째줄
print(cnt)
#둘째줄
print(*res_idx)


