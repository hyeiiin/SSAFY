 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main  {

	static int[] arr;
	static Integer[] dp;
	static int n;
	static int max = -Integer.MAX_VALUE;

	public static void main(String args[]) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		dp = new Integer[n + 1];

//		dp[0] = 0;
		dp[1] = 0;

		System.out.print(solve(n));

	}

	static int solve(int num) {

		if (dp[num] == null) {

			if (num % 6 == 0)
				dp[num] = Math.min(Math.min(solve(num / 3), solve(num / 2)), solve(num - 1)) + 1;

			else if (num % 2 == 0)
				dp[num] = Math.min(solve(num / 2), solve(num - 1)) + 1;
//				dp[num] = solve(num / 2) + 1;

			else if (num % 3 == 0)
				dp[num] = Math.min(solve(num / 3), solve(num - 1)) + 1;

			else
				dp[num] = solve(num - 1) + 1;

		}

		return dp[num];

	}

}