package sdf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main_3109_정준원 {
	static int dx[] = { -1, 0, 1 }; // 우측 위, 우측, 우측 아래 순서로 탐색
	static int dy[] = { 1, 1, 1 };

	static int R, C;
	static char[][] map;
	static boolean[][] visit;

	static int cnt;
	static boolean check;

	static class point {
		int x;
		int y;

		point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void dfs(point p) {

		if (check) {
			visit[p.x][p.y] = false;
			return;

		}
		visit[p.x][p.y] = true;

		if (p.y == C - 1) {
//			System.out.println("suc " + p.x + " " + p.y);
			check = true;
			cnt++;
			return;
			// 최종 목적지에 도달하였기 때문에 check를 true로.
		}

		for (int j = 0; j < 3; j++) {
			int newX = p.x + dx[j];
			int newY = p.y + dy[j];

			if (isvalid(newX, newY) && !visit[newX][newY] && map[newX][newY] == '.') {
//				System.out.println("new x y" + newX + " " + newY);

				visit[newX][newY] = true;
				dfs(new point(newX, newY));
//				visit[newX][newY] = false;
			}

		}

	}

	static boolean isvalid(int newX, int newY) {
		return newX >= 0 && newY >= 0 && newX < R && newY < C;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visit = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
//			System.out.println("start" + i + " 0");
			dfs(new point(i, 0));
			check = false; // 새로 탐색을 하기 때문에 false로 변경
		}

		System.out.println(cnt);
	}

}
