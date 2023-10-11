
def go_rotate(lst):

    rotate_cnt = N//4 #회전시켜야 할 수

    res_lst = []
    #0회전
    for i in range(0, N, rotate_cnt):
        str = ''.join(lst[i:i+rotate_cnt])
        res_lst.append(str)

    for i in range(rotate_cnt-1):
        #시계방향으로 돌리고 3개씩 나누어 리스트에 넣기
        last = lst.pop()
        lst.insert(0, last)

        for j in range(0, N, rotate_cnt):
            str = ''.join(lst[j:j+rotate_cnt])
            res_lst.append(str)

    res_lst = sorted(list(set(res_lst)))
    return res_lst[len(res_lst)-K]

T = int(input())
for tc in range(1, T+1):
    #N:숫자 개수 K: 크기 순서
    N, K = map(int,input().split())
    nums = list(input().rstrip())

    k_num = go_rotate(nums)

    res = 0
    for i in range(len(k_num)):
        if k_num[i]=='A':
            res += 10*pow(16, len(k_num)-i-1)
        elif k_num[i]=='B':
            res += 11 * pow(16, len(k_num)-i-1)
        elif k_num[i]=='C':
            res += 12 * pow(16, len(k_num)-i-1)
        elif k_num[i]=='D':
            res += 13 * pow(16, len(k_num)-i-1)
        elif k_num[i]=='E':
            res += 14 * pow(16, len(k_num)-i-1)
        elif k_num[i]=='F':
            res += 15 * pow(16, len(k_num)-i-1)
        else:
            res += int(k_num[i])*pow(16, len(k_num)-i-1)


    print("#{} {}".format(tc, res))

