package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17143_정준원 {

	static point[][] arr;
	static int min = Integer.MAX_VALUE;
	static int R, C, M;
	static boolean[] visited;
	static int[] dr = { 0, -1, 1, 0, 0 }; // 상하우좌
	static int[] dc = { 0, 0, 0, 1, -1 };
//	static ArrayList<point> shark;

	static class point {
		int r, c, s, d, z;

		public point(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

	}

	static int res;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new point[R + 1][C + 1];
// 

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int r, c, s, d, z;
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
//			shark.add(new point(r, c, s, d, z));
			// System.out.printf("p.d %d p.r %d p.c %d\n", d, r, c);

			arr[r][c] = new point(r, c, s, d, z);// 상어 크기저장
		}
		// System.out.printf("sharksize %d\n", shark.size());

		int kingidx = 0;
		res = 0;
		while (kingidx < C) {

			kingidx++;
			// System.out.printf("kingidx %d\n", kingidx);

			catchshark(kingidx);
			Queue<point> sharks = new LinkedList<>();
			for (int i = 1; i <= R; i++) {
				for (int j = 1; j <= C; j++) {
					if (arr[i][j] != null) { //
						sharks.add(new point(i, j, arr[i][j].s, arr[i][j].d, arr[i][j].z));
						// System.out.printf("after add i %d j%d z%d\n", i, j, arr[i][j].z);

					}
				}
			}
			arr = new point[R + 1][C + 1];
			moveshark(sharks);
			// System.out.printf("after move %d\n", shark.size());

		}
		System.out.println(res);
	}

	static void catchshark(int col) {

		int min = Integer.MAX_VALUE;
		int minidx = 0;

//		for (int i = 0; i < shark.size(); i++) {
		for (int i = 1; i <= R; i++) {
//			point p = shark.get(i);
			// System.out.printf(" i %d col %d \n", i, col);

			if (arr[i][col] != null) {

				point p = arr[i][col];

				// if (p.c == col) {
//					if (min > p.r) {
				// System.out.printf(" min %d col %d p.r %d \n", min, col, p.r);

				// minidx = i;
//					}
				// }
				res += arr[i][col].z;
				arr[i][col] = null;
				break;
			}

		}

		// System.out.printf("catch shark min %d col %d res %d \n", min, col, res);

	}

	// 상하우좌
	static void moveshark(Queue<point> sharks) {
		int size = sharks.size();
		// System.out.println("move");

		while (!sharks.isEmpty()) {

			point p = sharks.poll();

			// System.out.printf("before shark i %d p.d %d p.r %d p.c %dp.s %d\n", i, p.d,
			// p.r, p.c, p.s);
			int speed = p.s;

			int cnt = 0;
//			if (arr[p.r][p.c].z == p.z)
//				arr[p.r][p.c] = null;

			int nr = p.r;
			int nc = p.c;

			if (p.d == 1 || p.d == 2)
				p.s %= (R - 1) * 2;
			else if (p.d == 3 || p.d == 4)
				p.s %= (C - 1) * 2;

			while (p.s > cnt) {
				nr = p.r + dr[p.d];
				nc = p.c + dc[p.d];

				if (isvalid(nr, nc)) {
					//p = new point(nr, nc, speed, p.d, p.z);
					p.r = nr;
					p.c = nc;
				} else {
					if (p.d % 2 == 0) {
						p.d -= 1;
					} else {
						p.d += 1;
					}
					// System.out.printf("while shark p.d %d p.r %d p.c %d\n", p.d, p.r, p.c);

					nr = p.r + dr[p.d];
					nc = p.c + dc[p.d];
					//p = new point(nr, nc, speed, p.d, p.z);
					p.r = nr; //이거 위에거랑 밑에거 시간복잡도 차이 있
					p.c = nc;
					
				}
				cnt++;
			}

//		 System.out.printf("shark addd p.d %d p.r %d p.c %d\n", p.d, nr, nc);
			if (arr[nr][nc] != null) {

				if (arr[nr][nc].z < p.z)
					arr[nr][nc] = new point(nr, nc, speed, p.d, p.z);
				else {

					continue;
				}
			} else {
				arr[nr][nc] = new point(nr, nc, speed, p.d, p.z);

			}
			// System.out.printf("shark update p.d %d p.r %d p.c %d p.z%d arr[nr][nc] %d\n",
			// p.d, nr, nc, p.z,
			// arr[nr][nc].z);

		}
		// System.out.printf(" arr[3][4].size() %d\n", arr[3][4].size());
// 
	}
// 

	static boolean isvalid(int r, int c) {
		return r > 0 && r <= R && c > 0 && c <= C;
	}

}
