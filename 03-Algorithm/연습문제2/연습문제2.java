import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 연습문제2 {
	// 펠린드롬 만들기
	static int N;
	static int res;
	static int[] arr;
	static int[] dp;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		String str;

		dp = new int[9];

		dp[1] = 2;
		dp[2] = 5; // 1 1 /2 0 ->2*2+1
// 8+ 4=12
//		8+2+2=10
		// dp[3]= 1 2 / // 10
		// dp[4]= 1 3 /2 2 -- 20+1
		// dp[5]= 1 4 /4 1/ 2 3/3 2

		for (int i = 3; i < 7; i++) {
			dp[i] = dp[i - 1] * 2 + dp[i - 2];
		}

		System.out.println(dp[6]);
	}

}
