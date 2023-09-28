package study.swexpertacademy.test_sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V4A46AdIDFAWu
 */
public class Solution_2115_박정인 {
	static class Honey {
		int x, sy, ey, max;

		public Honey(int x, int sy, int ey, int max) {
			this.x = x;
			this.sy = sy;
			this.ey = ey;
			this.max = max;
		}
	}

	// N : 컬통 크기
	// M : 일꾼이 가로로 연속해서 채취할 벌통 수
	// C : 한 일꾼이 채취할 수 있는 총 벌꿀 양
	static int N, M, C, map[][], max, result;
	static List<Honey> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			list = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 일꾼이 선택할 벌통
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= N - M; j++) {
					max = Integer.MIN_VALUE;
					findMaxHoney(i, j, M, 0, 0);
					list.add(new Honey(i, j, j + M - 1, max));
				}
			}

			Collections.sort(list, (o1, o2) -> Integer.compare(o2.max, o1.max));
			result = list.get(0).max;

			for (int i = 1; i < list.size(); i++) {
				if (!isSameLine(list.get(0), list.get(i))) {
					result += list.get(i).max;
					break;
				}
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	// 일꾼1, 2가 선택한 범위가 겹치는 경우
	private static boolean isSameLine(Honey h1, Honey h2) {
		return (h1.x == h2.x) && ((h1.sy <= h2.sy && h1.ey >= h2.sy) || (h1.sy <= h2.ey && h1.ey >= h2.ey));
	}

	// 해당 범위에서 최대 수익
	private static void findMaxHoney(int x, int y, int cnt, int total, int honey) {
		if (honey > C) {
			return;
		}

		if (cnt == 0) {
			max = Math.max(max, total);
			return;
		}

		// 현재 위치 꿀이 선택된 경우
		findMaxHoney(x, y + 1, cnt - 1, total + map[x][y] * map[x][y], honey + map[x][y]);
		// 현재 위치 꿀이 선택 안된경우
		findMaxHoney(x, y + 1, cnt - 1, total, honey);
	}
}
