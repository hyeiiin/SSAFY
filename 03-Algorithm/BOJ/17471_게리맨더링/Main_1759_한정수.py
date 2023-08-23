import sys
L, C = map(int, sys.stdin.readline().rstrip().split())

#최소 1개모음, 최소 2개자음.
#최종 결과는 오름차순일것.

#즉, C개의 문자셋을 주고, 그 중에서 L개를 순열로 조합하되,
# 오름차순으로 가기 때문에 visited 체크하면서,

# 1 2 3 4 5
#   2 3 4 5
#     3 4 5
#       4 5   이런식으로 체크하면 될듯. 사실상 조합 문제.

# 단, 모음 1개이상, 자음 2개이상이 되어야함.

#일단 모음들 하나에 담아놓고
VOWEL = ['a', 'e', 'i', 'o', 'u']

letters = list(sys.stdin.readline().rstrip().split())
letters.sort()

selected_letters = ["" for _ in range(L)]


def comb(cnt, start, vowel_cnt, non_v_cnt):
    if cnt == L:
        if vowel_cnt < 1 or non_v_cnt < 2:
            #암호 조건에 안맞음
            return
        str = ""
        for i in selected_letters:
            str += i
        print(str)
        return
    for i in range(start, C):
        selected_letters[cnt] = letters[i]

        # 선택을 하되, 모음이면 모음개수 +1
        if letters[i] in VOWEL:
            comb(cnt+1, i+1, vowel_cnt + 1, non_v_cnt)
        # 자음이면, 자음개수 +1
        else:
            comb(cnt+1, i+1, vowel_cnt, non_v_cnt + 1)

comb(0, 0, 0, 0)
