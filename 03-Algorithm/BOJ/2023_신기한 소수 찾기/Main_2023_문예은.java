package com.ssafy.Algorithm;

import java.util.Scanner;

public class Main_2023_문예은 {
	static int num;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		num = sc.nextInt(); // 1~8 사이의 정수 입력, N자리의 숫자
		
		for (int i = 1; i <= 9; i++) {
			if(isPrime(i)) { // 2, 3, 5, 7 들어감
				zaritsu(i,1); // 자릿수 늘려가며 소수찾는 메소드 호출
			}
		}
	}
	
	private static void zaritsu(int addNum, int cnt) {
		if(cnt == num) { // 입력한 자릿수까지 도달하면 결과숫자 출력
			System.out.println(addNum);
			return;
		}
		addNum *= 10; // 들어온 소수의 자릿수 늘려주기
		for (int i = 1; i <= 9; i++) {
			if(isPrime(addNum + i)) zaritsu(addNum+i, cnt+1); // 늘어난 자릿수 내에서 소수찾기
		}
	}

	private static boolean isPrime(int n) {
		if(n<2) {
			return false;
		}
		// 숫자 n : 2 이상 n/2 이하의 수로 나누어 떨어지는지 확인
		for (int i = 2; i*i <= n; i++) { // 루트도 가능하지만, 근사값 문제있으므로 i*i <= n 으로 해야함.
			if(n%i == 0) { // 하나라도 나누어떨어지면 소수 아님
				return false;
			}
		}
		// 반복문 이후 리턴되지 않았다면, 소수 !
		return true;
	}
}
