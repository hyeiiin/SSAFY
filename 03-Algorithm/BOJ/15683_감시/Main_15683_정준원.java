package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.print.attribute.standard.Sides;

public class Main_15683_정준원 {
	static char arr[][];
	static int[] dr = { -1, 0, 1, 0 }; // 상좌하우
	static int[] dc = { 0, -1, 0, 1 };
	static int res = Integer.MAX_VALUE;
	static int N, L, M;
	// 상좌하우
	static int cctvsize = 0;
	static int cctv[][] = { { 0, 1, 0, 0 }, { 0, 1, 0, 1 }, { 1, 1, 0, 0 }, { 1, 1, 0, 1 }, { 1, 1, 1, 1 } };
	static int[][] visit;
	static int[] output;
	static ArrayList<int[]> list;

	public static void main(String args[]) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		visit = new int[N][M];
		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = st.nextToken().charAt(0);
				if (arr[i][j] >= '1' && arr[i][j] <= '5') {
					cctvsize++;
					list.add(new int[] { i, j });
				}
			}

		}

		for (int i = 0; i < N; i++) {
			// System.out.println(Arrays.toString(arr[i]));
		}
		// 조합
		output = new int[cctvsize];
		permu(0);
		System.out.println(res);
	}

	static void permu(int depth) { // 중복조합...
		if (depth == cctvsize) {
			int[][] cctvcopy = new int[5][4];
			char[][] arrcopy = new char[N][M];

			for (int s = 0; s < 5; s++)
				System.arraycopy(cctv[s], 0, cctvcopy[s], 0, cctv[0].length);

			for (int s = 0; s < N; s++)
				System.arraycopy(arr[s], 0, arrcopy[s], 0, M);

			for (int i = 0; i < cctvsize; i++) {
				int[] v = list.get(i);

				roatatecnt(output[i], cctvcopy, arrcopy[v[0]][v[1]] - '0');
				spread(v[0], v[1], arrcopy, cctvcopy);
			}

			res = Math.min(res, getcount(arrcopy));
			// System.out.println("show");
//			System.out.println("cctvsize" + cctvsize);

			for (int i = 0; i < arr.length; i++) {
				// System.out.println(Arrays.toString(arrcopy[i]));
			}
			// System.out.println("getcount" + getcount(arrcopy));
			return;
		}

		for (int i = 0; i < 4; i++) {
			output[depth] = i;
			permu(depth + 1);

		}

	}
	// 상좌하우

	static void spread(int r, int c, char[][] arrcopy, int[][] cctvcopy) {
		Queue<int[]> q = new LinkedList<>();

		ArrayList<Integer> dir = new ArrayList<>();
		// System.out.println("spread start");
		// System.out.println("cctvcidx r c" + arrcopy[r][c] + " " + r + " " + c);

		for (int i = 0; i < 4; i++) {
//			System.out.println("      c" + Arrays.toString(cctvcopy[i]));
		}

		for (int i = 0; i < 4; i++) {
			if (1 == cctvcopy[(arrcopy[r][c]) - '0' - 1][i]) {
				dir.add(i);
			}
		}
//
		// System.out.println("dir " + (int) ((arrcopy[r][c]) - '0') + " " +
		// dir.toString() + " r c " + r + " " + c);

		for (int i = 0; i < dir.size(); i++) {
			q.add(new int[] { r, c, dir.get(i) });
		}

		int size = q.size();

		for (int i = 0; i < size; i++) {
			int[] v = q.poll();
			spread2(v[0], v[1], arrcopy, cctvcopy, v[2]);
		}

	}

	static void spread2(int r, int c, char[][] arrcopy, int[][] cctvcopy, int dir) {
		Queue<int[]> q = new LinkedList<>();

		q.add(new int[] { r, c, dir });

		while (!q.isEmpty()) {
			int[] v = q.poll();
//			System.out.println("v0 v1 v2 " + v[0] + " " + v[1] + " " + v[2]);
			int nr = v[0] + dr[v[2]];
			int nc = v[1] + dc[v[2]];

			if (isvalid(nr, nc) && arrcopy[nr][nc] != '6') {
				// System.out.println("nr nc" + nr + " " + nc);
				q.add(new int[] { nr, nc, v[2] });
				visit[nr][nc] += 1;

				if (arrcopy[nr][nc] < '1' || arrcopy[nr][nc] > '5')
					arrcopy[nr][nc] = '#';

			}
		}

	}
	// 상좌하우

	static void respread(int r, int c, char[][] arrcopy, int[][] cctvcopy) {
		Queue<int[]> q = new LinkedList<>();

		ArrayList<Integer> dir = new ArrayList<>();
		// System.out.println("respread start");
		// System.out.println("cctvcidx " + arrcopy[r][c]);

		for (int i = 0; i < 4; i++) {
//			System.out.println("      c" + Arrays.toString(cctvcopy[i]));
		}
		for (int i = 0; i < 4; i++) {
			if (1 == cctvcopy[(arrcopy[r][c]) - '0' - 1][i]) {
				dir.add(i);
			}
		}
		// System.out.println("dir " + (int) ((arrcopy[r][c]) - '0') + " " +
		// dir.toString() + " r c " + r + " " + c);

		for (int i = 0; i < dir.size(); i++) {

			q.add(new int[] { r, c, dir.get(i) });
		}

		int size = q.size();

		for (int i = 0; i < size; i++) {
			int[] v = q.poll();
			respread2(v[0], v[1], arrcopy, cctvcopy, v[2]);
		}

	}

	static void respread2(int r, int c, char[][] arrcopy, int[][] cctvcopy, int dir) {
		Queue<int[]> q = new LinkedList<>();

		q.add(new int[] { r, c, dir });

		while (!q.isEmpty()) {
			int[] v = q.poll();
//			System.out.println("v0 v1 v2 " + v[0] + " " + v[1] + " " + v[2]);
			int nr = v[0] + dr[v[2]];
			int nc = v[1] + dc[v[2]];

			if (isvalid(nr, nc) && arrcopy[nr][nc] == '#') {
				// System.out.println("nr nc" + nr + " " + nc);
				q.add(new int[] { nr, nc, v[2] });
				visit[nr][nc] -= 1;
//				if (visit[nr][nc] <= 0)
				if (visit[nr][nc] <= 0 && (arrcopy[nr][nc] < '1' || arrcopy[nr][nc] > '5'))
					arrcopy[nr][nc] = '0';
			}
		}

	}

	static boolean isvalid(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	static void rotate(int cctvidx, int[][] cctvcopy) {
		int tmp;
		cctvidx--;
		// System.out.println("cctvidx" + cctvidx);
		int size = cctvcopy[cctvidx].length;
		tmp = cctvcopy[cctvidx][size - 1];

		for (int j = size - 1; j > 0; j--) {
			cctvcopy[cctvidx][j] = cctvcopy[cctvidx][j - 1];
		}

		cctvcopy[cctvidx][0] = tmp;
	}

	static void roatatecnt(int cnt, int[][] cctvcopy, int cctvidx) {
		for (int i = 0; i < cnt; i++) {
			rotate(cctvidx, cctvcopy);
		}
	}

	static int getcount(char[][] arrcopy) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arrcopy[i][j] == '0')
					cnt++;
			}
		}

		return cnt;
	}

}
