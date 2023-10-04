from sys import stdin


def main():
    def get_pi(p):
        p_len = len(p)
        pi = [0] * p_len
        j = 0
        for i in range(1, p_len):
            while j and p[i] != p[j]:
                j = pi[j - 1]
            if p[i] == p[j]:
                j += 1
                pi[i] = j
        return pi

    def kmp(t, p):
        answers = []
        pi = get_pi(p)
        t_len = len(T)
        p_len = len(P)
        i = 0
        j = 0
        for i in range(t_len):
            while j and T[i] != P[j]:
                j = pi[j - 1]
            if T[i] == P[j]:
                if j == p_len - 1:
                    answers.append(i - j + 1)
                    j = pi[j]
                else:
                    j += 1
        return answers

    T, P = stdin.read().splitlines()
    answers = kmp(T, P)
    print(len(answers))
    print(*answers)


main()
