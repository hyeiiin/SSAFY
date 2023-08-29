package ssafy;

/**
 * 1cm 파란 막대
 * 1cm 노란 막대
 * 2cm 빨간 막대
 * ncm인 막대를 만드는 방법의 수
 */
public class 연습문제2_김아현 {
	
	static int[] dp; 
	public static void main(String[] args) {
		
		int n = 6;
		dp = new int[n+1];
		
		count(n);
		
		System.out.println(dp[n]);
	}
	
	static void count(int n) {
		
		dp[1] = 2;
		dp[2] = 5;
		for (int i = 3; i <= n; i++) {
			dp[i] = 2*dp[i-1] + dp[i-2];
		}
	}
}
