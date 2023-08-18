
package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17135_정준원 {

	static int N = 0;
	static int M = 0;
	static int D = 0;
	static int max = 0;
	static boolean[][] visit;
	static int[][] arr;
	static int[] dr = { 1, 0, -1, 0 }; // 아 오 위 왼
	static int[] dc = { 0, 1, 0, -1 };

	static class point {
		int r, c;

		public point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static point[] archer;
	static int res = 0;

	public static void main(String[] args) throws IOException { // 궁수는 3명.. 최대 죽일 적 수 출력. 궁수가 쏘면 움직인다.
//		가장 가까운 적 1명 죽인다. 가까운게 여러개면 왼쪽 먼저 죽인다.

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		arr = new int[N + 1][M];
		visit = new boolean[N + 1][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		placearcher(0, 0);
		System.out.println(res);
	}

	static void play(int[][] arrcopy) {
		// TODO Auto-generated method stub
//		System.out.println("play");
//		System.out.println(Arrays.toString(visit[N]));
		int kill = 0;

		while (!endgame(arrcopy)) {
			kill += shoot(arrcopy);
//			System.out.println("playkill" + kill);
			// System.out.println("move");
			move(arrcopy);
		}

		res = Math.max(res, kill);
//	 
	}

	private static void move(int[][] arrcopy) {
		// TODO Auto-generated method stub
//		int[] tmp = new int[M];
//
//		for (int i = 0; i < M; i++) {
//			tmp[i] = arr[N - 1][i];
//		}
//		System.out.println("in move");
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				arrcopy[i + 1][j] = arrcopy[i][j];
			}
		}

		for (int j = 0; j < M; j++) {
			arrcopy[0][j] = 0;
		}
		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arrcopy[i]));
		}

	}

	static int shoot(int[][] arrcopy) {
		int kill = 0;
		boolean tmpvisit2[][] = new boolean[N][M];

		for (int i = 0; i < M; i++) {
			HashSet<int[]> hs = new HashSet<>();
//			System.out.println("shoot");

			if (visit[N][i]) {
				kill += findandremove(N, i, arrcopy, tmpvisit2); // 증복되는 놈을 죽일경우 0 더한다.
			}

		}

		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arrcopy[i]));
		}
		return kill;
	}

	static int findandremove(int r, int c, int[][] arrcopy, boolean tmpvisit2[][]) {
		// 가까운 왼쪽 찾기.
		// TODO Auto-generated method stub
		Queue<int[]> q = new LinkedList<>();
		boolean tmpvisit[][] = new boolean[N][M];
		int arr2[][] = new int[N + 1][M];

		int fr = 0;
		int fc = 0;
		// System.out.println("findand");
		q.add(new int[] { r, c, 0 });
		arr2[r][c] = 0;
		int min = Integer.MAX_VALUE;
//		System.out.println("findandremove");

		while (!q.isEmpty()) {

			int[] v = q.poll();
			for (int i = 0; i < 4; i++) {

				int nr = v[0] + dr[i];
				int nc = v[1] + dc[i];

				if (isvallid(nr, nc) && !tmpvisit[nr][nc]) {

					q.add(new int[] { nr, nc, v[2] + 1 });
					tmpvisit[nr][nc] = true;
					arr2[nr][nc] = v[2] + 1;

					if (arrcopy[nr][nc] == 1 || tmpvisit2[nr][nc])
						min = Math.min(min, v[2] + 1);
				}
			}

		}

//		System.out.println("mid");
//		for (int i = 0; i < N; i++)
//			System.out.println(Arrays.toString(arr2[i]));

		for (int i = 0; i <= M - 1; i++) {
			for (int j = N - 1; j >= 0; j--) {
//				System.out.println("chekc j i" + j + " " + i + "tmpvisit2 " + tmpvisit2[j][i] + "min " + min);
				if ((arrcopy[j][i] == 1 || tmpvisit2[j][i]) && min == arr2[j][i] && min <= D) {
//					arr[i][j] = 0;
					if (!tmpvisit2[j][i])// 이미 방문한 경우 0 , 아니면 1 왜냐면 중복되서 죽이는경우는 1번이라 치므로
					{
//						System.out.println("remove " + j + " " + i + " me " + r + " " + c);
						tmpvisit2[j][i] = true;
//						System.out.println("tmpvisit2 " + j + " " + i + " " + tmpvisit2[j][i]);
						arrcopy[j][i] = 0; // 죽인경우... 0으로 바꾼다.
						return 1;

					} else {
//						System.out.println("remove jungbok" + j + " " + i + " me " + r + " " + c);

						tmpvisit2[j][i] = true;
						arrcopy[j][i] = 0;
						return 0;
					}

				}
			}
		}

		return 0;

	}

	static boolean isvallid(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	static boolean endgame(int[][] arrcopy) {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arrcopy[i][j] == 1) {
					return false;
				}
			}
		}
		return true;

	}

	static void disappear(int[][] arrcopy) {
		for (int i = 0; i < M; i++) {
			arrcopy[N - 1][i] = 0;
		}
	}

	static void placearcher(int idx, int depth) {
		if (3 == depth) {

			int[][] arrcopy = new int[N + 1][M];

			for (int i = 0; i < N + 1; i++) {
				for (int j = 0; j < M; j++) {
					arrcopy[i][j] = arr[i][j];
				}
			}

			play(arrcopy);
			return;
		}
//		System.out.println("idx depth" + idx + " " + depth);
		for (int i = idx; i < M; i++) {
			visit[N][i] = true;
			// System.out.println("i+1 " + (i + 1));
			placearcher(i + 1, depth + 1);
			visit[N][i] = false;
		}

	}

}