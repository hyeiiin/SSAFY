package baekjoon;

import java.util.*;
import java.io.*;

public class Main_6987_김태훈 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static int arr[], map[][], sum_win, sum_tie, sum_lose, tie_team, total_size, team_sum;
	static int n = 6;
	static boolean is;

	private static void init() throws IOException {
		map = new int[n][3];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void solve() {
		sum_win = 0;
		sum_tie = 0;
		sum_lose = 0;
		tie_team = 0;
		total_size = 15;
		is = false;
		for (int i = 0; i < n; i++) {
			team_sum = 0;
//			total_size += i;
			sum_win += map[i][0];
			sum_tie += map[i][1];
			sum_lose += map[i][2];
			if (map[i][1] > 0)
				tie_team++;
			for (int j = 0; j < 3; j++) {
				team_sum += map[i][j];
			}
			if(team_sum!=5) {
				sb.append(0).append(" ");
				return;
			}
		}

		if (!check()) {
			sb.append(0).append(" ");
			return;
		}
		//가지치기에 안걸렸으면 완전 탐색으로 확인.
		else {
			is = false;
			gogo(0, 0, 1);
			if(is)
				sb.append(1).append(" ");
			else
				sb.append(0).append(" ");
		}
	}

	private static void gogo(int count, int first, int second) {
		if(is==true)
			return;
		if (count == total_size) {
			is = true;
			return;
		}
		// 재귀 함수로 완전 탐색
		int nextFirst = first;
		int nextSecond = second + 1;
		if (nextSecond == n) {
			nextFirst += 1;
			nextSecond = nextFirst + 1;
		}
		if (map[first][0] > 0 && map[second][2] > 0) {
			map[first][0]--;
			map[second][2]--;
			gogo(count + 1, nextFirst, nextSecond);
			// 원상복구
			map[first][0]++;
			map[second][2]++;
		}
		if (map[first][1] > 0 && map[second][1] > 0) {
			map[first][1]--;
			map[second][1]--;
			gogo(count + 1, nextFirst, nextSecond);
			map[first][1]++;
			map[second][1]++;
		}
		if (map[first][2] > 0 && map[second][0] > 0) {
			map[first][2]--;
			map[second][0]--;
			gogo(count + 1, nextFirst, nextSecond);
			map[first][2]++;
			map[second][0]++;
		}
	}

	private static boolean check() {
		if (sum_win != sum_lose || sum_tie % 2 != 0 || sum_win + (sum_tie / 2) != total_size
				|| sum_lose + (sum_tie / 2) != total_size) {
			return false;
		}
		return true;
	}

	private static void print() {
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 4; i++) {
			init();
			solve();
		}
		print();
	}

}
