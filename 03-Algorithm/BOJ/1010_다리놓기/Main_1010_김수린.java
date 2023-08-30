package BOJ;

import java.io.*;
import java.util.*;

// 파스칼의 법칙
// https://st-lab.tistory.com/159#%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98
// 다리를 고르는 경우는 조합이다.
// 조합 공식 중 점화식(파스칼의 법칙) => BOJ11050
// 1. n+1Cr+1 = nCr + nCr+1
//	= nCr = n-1Cr-1 + n-1Cr
// 2. nC0 = nCn = 1
public class Main_1010_김수린 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T, N, M, dp[][];
	
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dp = new int[M + 1][N + 1];
	}
	
	public static int comb(int n, int r) {
		if(dp[n][r] > 0) {
			return dp[n][r];
		}
		if(n == r || r == 0) {
			return dp[n][r] = 1;
		}
		return dp[n][r] = comb(n - 1, r - 1) + comb(n - 1, r);
	}
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			init();
			bw.write(comb(M, N) + "\n");
		}
		bw.flush();
		bw.close();
	}
}
