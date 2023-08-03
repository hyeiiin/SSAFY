package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2961_정준원 {
	static int[] sin; // 곱
	static int[] ssen; // 합
	static int res = Integer.MAX_VALUE;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		sin = new int[n];
		ssen = new int[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			sin[i] = Integer.parseInt(st.nextToken());
			ssen[i] = Integer.parseInt(st.nextToken());

		}

		solve(0, 0, 1, 0);
		System.out.println(res);
	}

	static void solve(int add, int idx, int sin_sum, int ssen_sum) {

		if (idx == n) {
			if (add != 0) {
				res = Math.min(res, Math.abs(sin_sum - ssen_sum));
			}
			return;
		}

		solve(add, idx + 1, sin_sum, ssen_sum); // idx번째 있는 재료 넣지 않은 것
		solve(add + 1, idx + 1, sin_sum * sin[idx], ssen_sum + ssen[idx]); // 재료를 넣은것

	}

}