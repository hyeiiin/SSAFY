package sdf;

import java.io.*;
import java.util.*;

public class Solution_6808_정준원 {
	static int[] kyu;
	static int[] in;
	static int[] remaining;
	static boolean[] visit;
	static int win;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());

		for (int T = 1; T <= tc; T++) {
			win = 0;
			kyu = new int[9];
			in = new int[9];
			visit = new boolean[9]; // remaining 중에서
			remaining = new int[9];

			int idx = 0;
			boolean[] cnt = new boolean[18];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int i = 0; i < 9; i++) {
				kyu[i] = Integer.parseInt(st.nextToken());
				cnt[kyu[i] - 1] = true;
			}

			for (int i = 0; i < 18; i++) {
				if (cnt[i])
					continue;
				else
					remaining[idx++] = i + 1;
			}

			solve(0);
			sb.append("#").append(T).append(" ").append(win).append(" ").append(362880 - win).append("\n");
		}

		System.out.println(sb.toString());
	}

	public static void solve(int cnt) {

		if (cnt == 9) {
			int kyu_point = 0;
			int in_point = 0;

			for (int i = 0; i < 9; i++) {
				if (kyu[i] > in[i])
					kyu_point += (kyu[i] + in[i]);
				else
					in_point += (kyu[i] + in[i]);
			}

			if (kyu_point > in_point)
				win++;

			return;
		}

		for (int i = 0; i < 9; i++) {
			if (visit[i])
				continue;
			in[cnt] = remaining[i];
			visit[i] = true;
			solve(cnt + 1);
			visit[i] = false;
		}
	}
}