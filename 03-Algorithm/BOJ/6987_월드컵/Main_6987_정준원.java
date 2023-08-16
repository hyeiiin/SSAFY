package sdf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_6987_정준원 {
	static int win[], lose[], draw[], t1[], t2[];
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = 0;

		t1 = new int[15]; // 경기하는 2팀
		t2 = new int[15];

		for (int i = 0; i < 5; i++) {
			for (int j = i + 1; j < 6; j++) {
				t1[cnt] = i;
				t2[cnt] = j;
				cnt++;
			}
		}

		for (int i = 0; i < 4; i++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			win = new int[6];
			lose = new int[6];
			draw = new int[6];
			flag = true;

			int w = 0, l = 0, d = 0;
			int cnt2 = 0;

			for (int j = 0; j < 6; j++) {
				cnt2 = 0;
				win[j] = Integer.parseInt(st.nextToken());
				draw[j] = Integer.parseInt(st.nextToken());
				lose[j] = Integer.parseInt(st.nextToken());

				if (win[j] + draw[j] + lose[j] == 5) 
					cnt2++;

				if (cnt2 != 1) {
					flag = false;
				}

			}

			if (flag) {
				flag = false;
				dfs(0);
			}

			if (flag)
				System.out.print(1 + " ");

			else
				System.out.print(0 + " ");

		}
	}

	static void dfs(int idx) {
		if (flag)
			return;

		if (idx == 15) { // 여기까지 왔다는건 경기가 다 성립이 되었다는 소리..
			flag = true;
			return;
		}

		int a = t1[idx]; // 해당 팀 인덱스
		int b = t2[idx];

		// a가 이기는 경우
		if (win[a] > 0 && lose[b] > 0) { // 음수이면 안된다.
			win[a]--;
			lose[b]--;
			dfs(idx + 1);
			win[a]++;
			lose[b]++;
		}

		// a와 b가 비기는 경우
		if (draw[a] > 0 && draw[b] > 0) {
			draw[a]--;
			draw[b]--;
			dfs(idx + 1);
			draw[a]++;
			draw[b]++;
		}

		// a가 지는 경우
		if (lose[a] > 0 && win[b] > 0) {
			lose[a]--;
			win[b]--;
			dfs(idx + 1);
			lose[a]++;
			win[b]++;
		}

	}

}