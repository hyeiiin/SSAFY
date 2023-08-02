package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_15650_서지원 {

	static int n, m;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	
	private static void solve(int k, int idx) {
		if (k == m) {
			for (int i = 0; i < m; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = idx; i <= n; i++) {
			arr[k] = i;
			solve(k + 1, i + 1);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		arr = new int[m];
		solve(0, 1);
		System.out.println(sb.toString());
	}

}
