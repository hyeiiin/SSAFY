package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2023_탁하윤 {
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 자리수 받기
		
		primes(1, 2);	// 첫번째 자리는 소수로 시작해야함
		primes(1, 3);
		primes(1, 5);
		primes(1, 7);
		
	}
	
	// 자리수+소수 구하기
	private static void primes(int cnt, int nums) {	// cnt: 현재까지 뽑은 자리수, nums: 현재까지 뽑은 수
		if(cnt == N) {	// 자리수 만큼 소수를 찾았다면 신기한 소수를 출력하고 리턴
			System.out.println(nums);
			return;
		}
		
		for (int i = 1; i <= 9; i+=2) {	// 1부터 9까지 홀수만 소수이기 때문에 +2 하면서 for문 돌기
			int num = nums*10 + i;	// 받은 nums값에 10 곱하고 i값 넣은 num이 소수라면, cnt의 값을 1개 올리고 num값 전달
			if(isPrime(num)) {
				primes(cnt+1, num);
			}
		}
	}
	
	// 소수 판별
	private static boolean isPrime(int n) {
		if(n<2) {	// 1이하의 수는 소수가 아니므로 제외
			return false;
		}
		// 숫자 n이 2 이상 n-1이하의 수로 나누어 떨어지는지 확인
		// 근사값 오류로 i*i로 계산
		for (int i = 2; i*i <= n; i++) {
			if(n%i == 0) {// 하나라도 나누어 떨어지면 소수가 아님
				return false;
			}
		}
		// return이 안되었다면 소수이므로 ture 반환
		return true;
	}

}
