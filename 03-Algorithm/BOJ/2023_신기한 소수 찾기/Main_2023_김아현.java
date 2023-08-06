package ssafy.Boj;

import java.util.*;
/**
 * 2023. 신기한 소수
 * 소수 판별
 */
public class Main_2023_김아현 {
	
	static int digit;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		digit = sc.nextInt();
		
		// 한자릿수의 경우 소수인 수는 2,3,5,7
		findPrime(1,2);
		findPrime(1,3);
		findPrime(1,5);
		findPrime(1,7);
		
		System.out.println(sb.toString());
	}
	
	/**
	 * 소수 판별 메소드
	 * 0부터 루트 num까지 판별
	 * 시간 복잡도 O(n*(1/2))
	 * @param num 소수를 판별할 숫자
	 * @return true 소수 아님 false 소수임
	 */
	static boolean isPrime(int num) {
		if(num<2) {
			return true;
		}
		
		for (int i = 2; i*i <= num ; i++) {
			if(num % i == 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 자릿수 늘리면서 소수 판별 실행하는 메소드
	 * @param depth 자릿수
	 * @param num 소수 판별할 숫자
	 */
	static void findPrime(int depth, int num) {
		
		// 원하는 자릿수이고 소수라면 출력
		if(digit == depth) {
			sb.append(num).append("\n");
			return;
		}
		
		for (int i = 1; i < 10; i++) {
			// 자릿수 늘리기
			int nextNum = num*10+i;
			
			// 소수라면 자릿수 늘려서 소수 판별
			if(!isPrime(nextNum)) {
				findPrime(depth+1, nextNum);
			}
		}
	}
}
