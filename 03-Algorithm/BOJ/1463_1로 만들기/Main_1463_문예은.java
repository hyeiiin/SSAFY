import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1463_문예은 {
	static int[] memo;
	public static void main(String[] args) throws IOException {
		/*
		 * f(1) = 0
		 * f(2) = f(2-1)+1, f(2/2)+1 = 1
		 * f(3) = f(3-1)+1 > f(3/3)+1 = 1
		 * f(4) = f(4-1)+1, f(4/2)+1 = 2
		 * f(5) = f(5-1)+1 = 3
		 * f(6) = f(6-1)+1 > f(6/2)+1, f(6/3)+1 = 2
		 * 
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		memo = new int[N+1]; // 인덱스 0번은 버림
		System.out.println(go(N));
	}

	private static int go(int n) {
		if (n==1) { // 인덱스 1은 0으로 초기화
			return 0;
		}
		for (int i = 2; i <= n; i++) {
			memo[i] = memo[i-1]+1; // 1을 뺀다.
			if(i%2==0) memo[i] = Math.min(memo[i], memo[i/2]+1); // 2로 나눈다.
			if(i%3==0) memo[i] = Math.min(memo[i], memo[i/3]+1); // 3으로 나눈다.
		}
		return memo[n];
	}
}
