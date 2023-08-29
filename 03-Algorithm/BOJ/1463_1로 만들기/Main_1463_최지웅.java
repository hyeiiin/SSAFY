package todo.lesson._0829;

import java.util.*;

public class Main_1463_최지웅 {

	static Integer[] memo;
	
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		
		memo = new Integer[N + 1];
		memo[1] = 0;
		
		for (int n = 2; n <= N; n++) {
			if (memo[n] != null) continue;
			memo[n] = memo[n - 1] + 1;
			if (n % 2 == 0) memo[n] = Math.min(memo[n], memo[n / 2] + 1);
			if (n % 3 == 0) memo[n] = Math.min(memo[n], memo[n / 3] + 1);
		}
		
		System.out.println(memo[N]);
		
		//
		in.close();
	}

}
