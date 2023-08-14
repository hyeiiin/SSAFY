package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1074
 * @author SSAFY
 *
 */
public class Main_1074_박정인 {
	static int N, r, c, result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		result = findOrder(N, r, c); 

		System.out.println(result);
	}

	private static int findOrder(int n, int r, int c) {
		int len = (int) Math.pow(2, n);
		int mid = len / 2;
		int area = mid * mid;
		 
		if (n == 0) {
			return 0;
		}

		if (r < mid && c < mid) {
			return findOrder(n - 1, r, c);
		} else if (r < mid && c >= mid) {
			return findOrder(n - 1, r, c - mid) + area;
		} else if (r >= mid && c < mid) {
			return findOrder(n - 1, r - mid, c) + 2 * area;
		} else {
			return findOrder(n - 1, r - mid, c - mid) + 3 * area;
		}
	}
}
