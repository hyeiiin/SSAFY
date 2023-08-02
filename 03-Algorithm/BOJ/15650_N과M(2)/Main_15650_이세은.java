package practice;

import java.io.*;
import java.util.*;

public class Main_15650_이세은 {
	static int n, m;
	static boolean[] visited;
	static int[] rslt;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		rslt = new int[m]; // 결과 배열
		combination(0, 1);

	}

	public static void combination(int cnt, int start) {
		if (cnt == m) {
			for (int i = 0; i < m; i++)
				System.out.print(rslt[i] + " ");
			System.out.println();
			return;
		}
		for (int i = start; i <= n; i++) {
			rslt[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}

}
