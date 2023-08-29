package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1463_탁하윤 {
	static int N, f[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 정수 X
		f = new int[N+10];	// 입력 값보다 10크게 설정
		dp(N);	// 1로 만들 횟수 최솟값
		System.out.println(f[N]);
	}
	private static void dp(int n) {	// 정수 X까지 1로 만들기 위한 최솟값 찾기
		f[1] = 0;
		f[2] = 1;
		for (int i = 3; i <= N; i++) {
			f[i] = f[i-1] + 1;	// 다음 정수는 이전 정수의 더한 값으로 표현할 수 있음
			if(i%3 == 0) {	// 만약 i가 3으로 나누어 떨어진다면
				f[i] = Math.min(f[i/3] + 1, f[i]);	// i를 3으로 나눈 인덱스의 +1한 값과 이전 정수에 +1한 값 중 최솟값이 i를 1로 만들 최솟값
			}
			if(i%2 == 0) { // 만약 i가 2으로 나누어 떨어진다면
				f[i] = Math.min(f[i/2] + 1, f[i]); // i를 2으로 나눈 인덱스의 +1한 값과 이전 정수에 +1한 값 중 최솟값이 i를 1로 만들 최솟값
			}
		}
	}

}
