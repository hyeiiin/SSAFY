N = int(input())

arr = [0 for _ in range(N+1)]

arr[0] = 0
arr[1] = 0
# 기본적으론 1씩 더해줌. 즉, arr[i] = i임.
# 그런데 만약 i가 2로 나눠 떨어지거나 3으로 나눠떨어지면, i에서 해당 수를 나눈 값에서 +1을 해주면됨.
# 예를 들어 arr[9]의 경우, arr[3]에서 + 1을 해주면 됨.
for i in range(2, N+1):
    arr[i] = arr[i-1] + 1
    if i % 3 == 0:
        arr[i] = min(arr[i], arr[i//3]+1)

    if i % 2 == 0:
        arr[i] = min(arr[i], arr[i//2]+1)

print(arr[-1])

