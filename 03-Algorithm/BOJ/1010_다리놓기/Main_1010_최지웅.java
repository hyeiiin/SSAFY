package todo.lesson._0830;

import java.util.*;

public class Main_1010_최지웅 {
	
	static Integer[][] memo = new Integer[30 + 1][30 + 1];
	
	static int nCr(int n, int r) {
		if (memo[n][r] != null) return memo[n][r];
		
		if (n == r || r == 0) return memo[n][r] = 1;
		else {
			return memo[n][r] = nCr(n - 1, r - 1) + nCr(n - 1, r);
		}
	}
	
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = in.nextInt();
		
		int N, M;
		for (int t = 1; t <= T; t++) {
			N = in.nextInt();
			M = in.nextInt();	
			sb.append(nCr(M, N) + "\n");
		}
		
		System.out.println(sb);
		
		
		//
		in.close();
	}

}
