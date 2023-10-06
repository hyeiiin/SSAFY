package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution_5658_정준원 {

	static HashSet<Integer> set;
	static char[] arr;
	static int N, K, T;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; ++t) {

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			arr = br.readLine().toCharArray();
			set = new HashSet<>();

			for (int i = 0; i < N - 1; ++i) {
				split();
				rotate();
			}

			ArrayList<Integer> arr = new ArrayList<>();

			for (int i : set)
				arr.add(i);

			Collections.sort(arr, Collections.reverseOrder());

			System.out.println("#" + t + " " + arr.get(K - 1));
		}

	}

	private static void rotate() {
		char temp = arr[N - 1];

		for (int i = N - 1; i > 0; --i) {
			arr[i] = arr[i - 1];
		}

		arr[0] = temp;
	}

	private static void split() {

		int len = N / 4;
		for (int i = 0; i < N; i += len) {
			String str = "";

			for (int j = i; j < i + len; ++j) {
				str += arr[j];
			}

			int res = Integer.parseInt(str, 16);
			set.add(res);
		}
	}

}