package Test;

import java.io.*;
import java.util.*;

public class 연습문제2_김수린 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static Integer dp[];
	
	public static int dp(int v) {
		if(dp[v] != null) return dp[v];
		if(v == 0) return dp[v] = 0;
		else if(v == 1) return dp[v] = 2;
		else if(v == 2) return dp[v] = 5;
		else return dp[v] = 2 * dp(v - 1) + dp(v - 2);
	}
	
	public static void main(String[] args) throws IOException {
		int v = Integer.parseInt(br.readLine());
		dp = new Integer[v + 1];
		bw.write(dp(v) + "\n");
		bw.flush();
		bw.close();
	}
}
