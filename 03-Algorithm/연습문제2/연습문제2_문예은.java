import java.util.Arrays;
import java.util.Scanner;
/*
 * 1 파랑
 * 1 노랑
 * 2 빨강
 * 
 * f(1) = 2
 * f(2) = 2^2 +1
 * f(3) = 2^3 + 2^2
 * 21 = 2^4 + 2^3 4*2 + 1
 * f(짝수) = 2*f(n-1)+1
 * f(홀수) = 2*f(n-1)
 */
public class 연습문제2_문예은 { // 막대 색칠하기
	static long memo[];
	private static long fibo(int n) { 
		if(n<=2) return memo[n];
		for (int i = 3; i < n+1; i++) {
			memo[i] = 2*memo[i-1] + memo[i-2]; 
		}
		return memo[n];
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		memo = new long[N+2]; // 초기값 0,1,2 저장하기 위함(N에 0,1이 입력되어도 기저조건2까지는 저장되도록)
		
		Arrays.fill(memo, -1); // 메모되지 않은 상태로 초기화
		memo[0]=1; // 미리 기저조건 값 메모하기 
		memo[1]=2;
		memo[2]=5;
		
		System.out.println(fibo(N));
	}
}
