package Test;

import java.io.*;
import java.util.*;

/*
 * f(1) + f(2) = f(3)
 *  => 2 + 3 = 5
 *  
 * f(2) + f(3) = f(4)
 * 	=> 3 + 5 = 8
 */
public class 연습문제1_김수린 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int ans, dp[];
	
	public static int dp(int v) {
		if(v <= 1) dp[v] = v + 1;
		else dp[v] = dp(v - 1) + dp(v - 2);
		return dp[v];
	}
	
	public static void main(String[] args) throws IOException {
		int v = Integer.parseInt(br.readLine());
		dp = new int[v + 1];
		bw.write(dp(v) + "\n");
		bw.flush();
	}
}
