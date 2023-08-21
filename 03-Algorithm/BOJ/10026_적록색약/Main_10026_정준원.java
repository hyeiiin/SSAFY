package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.FutureTask;

public class Main_10026_정준원 {

	static int n = 0;

	static int cntnorm = 0;
	static int cntnotnorm = 0;

	static boolean[][] visitnorm;
	static boolean[][] visitnotnorm;

	static char[][] arr;
	static char[][] arr2;

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static class point {
		int r;
		int c;
		char ch;

		public point(int r, int c, char ch) {
			super();
			this.r = r;
			this.c = c;
			this.ch = ch;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		arr = new char[n][n];
		arr2 = new char[n][n];

		visitnorm = new boolean[n][n]; // 일반인
		visitnotnorm = new boolean[n][n]; // 적록색맹

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			arr[i] = str.toCharArray(); // 이차원 배열 할당받는다.

		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				if (!visitnorm[i][j]) { // visit 이지 않으면 bfs 수ㅐㅇ.
					// System.out.println("bfsnorm i j" + i + " " + j + arr[i][j]);
					bfsnorm(i, j, arr[i][j]);

					cntnorm++; // 해당 덩어리의 개수를 알기위해.
//					System.out.println("i j cntnorm" + i + j + " " + cntnorm);
				}

			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				if (arr[i][j] == 'R')
					arr2[i][j] = 'G'; // 적록색맹을 위해 r 을 g 로 바꾼다. 어차피 r g 같은 색으로 보기 때문에.
				else
					arr2[i][j] = arr[i][j];
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				if (!visitnotnorm[i][j]) {
					bfsnotnorm(i, j, arr2[i][j]);
					cntnotnorm++; // 마찬가지로 적록색맹의 입장에서 덩어리 개수를 구한다.
				}
			}
		}

		System.out.println(cntnorm + " " + cntnotnorm);
	}

	static void bfsnorm(int r, int c, char ch) {

		Queue<point> q = new LinkedList<>();

		q.add(new point(r, c, ch));
		visitnorm[r][c] = true;
		// System.out.println("ch" + ch);

		while (!q.isEmpty()) {

			point v = q.poll();

			// System.out.println("v.r v.c" + v.r + v.c);

			for (int i = 0; i < 4; i++) {
				// System.out.println("forstart");

				int nr = v.r + dx[i];
				int nc = v.c + dy[i];
//				System.out.println("middle");

//				System.out.println("out i nr nc arr[nr][nc] ch" + i + " " + nr + " " + nc + " " + arr[nr][nc] + ch);

				if (isvalid(nr, nc) && arr[nr][nc] == ch && !visitnorm[nr][nc]) {

//					System.out.println("nr nc arr[nr][nc]" + nr + " " + nc + " " + arr[nr][nc]);
					visitnorm[nr][nc] = true;
					q.add(new point(nr, nc, ch));
				}
			}
		}

	}

	static void bfsnotnorm(int r, int c, char ch) {

		Queue<point> q = new LinkedList<>();
		q.add(new point(r, c, ch));
		visitnotnorm[r][c] = true;

		while (!q.isEmpty()) {
//
			point v = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = v.r + dx[i];
				int nc = v.c + dy[i];

				if (isvalid(nr, nc) && arr2[nr][nc] == ch && !visitnotnorm[nr][nc]) {
					visitnotnorm[nr][nc] = true;
					q.add(new point(nr, nc, ch));
				}
			}
		}

	}

	static boolean isvalid(int r, int c) {
		return r >= 0 && c >= 0 && r < n && c < n;
	}
}