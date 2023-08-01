package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_15649_서지원 {
	
	static int n, m;
	static int[] arr;
	static boolean[] isused;
	static StringBuilder sb = new StringBuilder();

	private static void solve(int k) {
		if (k == m) {
			for (int i = 0; i < m; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = 1; i < n + 1; i++) {
			if (isused[i] == true) continue;
			arr[k] = i;
			isused[i] = true;
			solve(k + 1);
			isused[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		
		arr = new int[m];
		isused = new boolean[n + 1];
		solve(0);
		System.out.println(sb.toString());
	}

}
