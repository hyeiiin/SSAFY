T = int(input())

arr = []
arr.append(1)
arr.append(1)
# arr[0] = 1 , arr[1] = 1
# 1층 = arr[1+1] = arr[0]+ arr[1]

# i층 = arr[i+1] = arr[i] + arr[i-1]
# arr[2] 부터 피보나치로 추가
for i in range(T):
    arr.append(arr[i] + arr[i+1])

print(arr[T+1])