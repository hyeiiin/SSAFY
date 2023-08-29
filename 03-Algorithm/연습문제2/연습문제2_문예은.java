import java.util.Arrays;
import java.util.Scanner;
/*
 * 1 파랑
 * 1 노랑
 * 2 빨강
 * 
 * 				  4 2*10 + 1
 * 파노 파노 파노 파노 파노 3 2*5
 * 파노 파노 빨                  2 2*2+1
 * 파    노                         1 2
 * 
 * f(1) = 2
 * f(짝수) = 2* f(n-1)+1
 * f(홀수) = 2* f(n-1)
 */
public class 연습문제2_문예은 { // 막대 색칠하기
	static long memo[];
	private static long fibo(int n) { 
		if(n==1) return memo[1];
		if(memo[n]==-1) {
			fibo(n-1);
			if(n%2==0) memo[n] = 2*memo[n-1]+1;
			else memo[n] = 2*memo[n-1];
		}
		return memo[n];
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		memo = new long[N+1];
		
		Arrays.fill(memo, -1); // 메모되지 않은 상태로 초기화
		memo[0]=0; // 미리 기저조건 값 메모하기 
		memo[1]=2;
		
		System.out.println(fibo(N));
	}
}
