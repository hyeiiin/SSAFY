T = int(input())

for test_case in range(1, T+1):
    N, K = map(int, input().split())
    input_letter = input().rstrip()

    num_side = N // 4
    #012 345 679 9 10 11
    #1B3 B3B 81F 75E

    #11 01 234 567 8910
    #E1B 3B3 B81 F75
    #5E1 B3B 3B8 1F7

    numbers = []
    # 각 회전마다
    for rotation in range(num_side):
        # 각 변마다
        for side in range(4):
            # 각 변의 글자수까지
            temp = []
            for letter in range(num_side, 0, -1):
                temp.append(input_letter[len(input_letter)-rotation - (num_side*side+letter) -1])
                #12+0 - 2     -1= 9
                #12+0 - 1     -1= 10
                #12+0 - 0     -1= 11
                #12+0 - (3+2) -1= 6
                #12+0 - (3+1) -1= 7
                #12+0 - (3+0) -1= 8
                #                 3
                #                 4
                #                 5
                #                 0
                #                 1
                #                 2
                #===================
                #12-1 - 2     -1 = 8
                #                  9
                #                  10
                #                  5
                #                  6
                #                  7
                #                  2
                #                  3
                #                  4
                #                  -1 == 11
                #                  0
                #                  1
            if temp not in numbers:
                numbers.append(temp)
            temp = []
    for n in range(len(numbers)):
        temp = ""
        for i in range(num_side):
            temp += numbers[n][i]
        numbers[n] = int(temp, 16)

    numbers.sort(reverse=True)
    print("#"+str(test_case)+" "+str(numbers[K-1]))

