T = int(input())

#어차피 f(n)이 되려면,
# f(n-1)의 모든 경우 각각에서 1cm 올리는 2가지 분기(노랑파랑)
# f(n-2)에서 올리는 2cm짜리 빨강 하나. 만 더해주면 끝
# f(n) = f(n-1) * 2 + f(n-2)

f = []
f.append(2)
f.append(5)

for i in range(T-2):
    f.append(f[i+1]*2 + f[i])

print(f[T-1])