package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1463_전상혁 {
	static int res = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		/*
		 * 1. 3으로 나누어 떨어지면 3으로 나눔
		 * 2. 2로 나누어 떨어지면 2로 나눔
		 * 3. 1을 뻄
		 * 최소 연산을 하여 1을 만들고 연산 사용하는 횟수의 최솟값 출력
		 */

		calculate(N, 0);
		System.out.println(res);
				
		
	}
	private static void calculate(int number, int cnt) {
		//초기  N값이 3으로 나누어 떨어지면 효율적
		//2로 나누어보고 떨어지면 나누어주기
		//3과 2로 안나눠지면 -1 해주고 반복
		//반복

		if (number==1) { //1이 완성되었으면 종료.

			res = Math.min(res,cnt);
			return;
		}
		//기존 연산한 최솟값보다 클 경우 계산할 필요 없으므로 종료.
		if (cnt >= res) return;
		
		//3으로 나누어 떨어지는 경우
		if (number%3==0) {

			calculate(number/3, cnt+1);
		}
		//2로 나누어 떨어지는 경우
		if (number%2==0) {

			calculate(number/2, cnt+1);
		}
		//3,2로 안떨어지는 것은 -1
		calculate(number-1, cnt+1);

		

	}

}
