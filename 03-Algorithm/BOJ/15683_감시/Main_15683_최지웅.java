package todo.lesson._0822;

import java.io.*;
import java.util.*;

public class Main_15683_최지웅 {

	/* define */
	static final int EMPTY = 0;
	static final int R = 1;
	static final int LR = 2;
	static final int UR = 3;
	static final int ULR = 4;
	static final int UDLR = 5;
	static final int WALL = 6;

	static final int DIR_CASE = 4;
	static final int UP = 0;
	static final int DOWN = 1;
	static final int LEFT = 2;
	static final int RIGHT = 3;

	static class Point {
		int r, c;
		int type;
		int dir;

		Point(int type, int r, int c) {
			this.type = type;
			this.r = r;
			this.c = c;
		}
	}

	static boolean[] watchList(int type, int dir) {

		if (type == R) {
			if (dir == UP) {
				return new boolean[] { true, false, false, false };
			} else if (dir == DOWN) {
				return new boolean[] { false, true, false, false };
			} else if (dir == LEFT) {
				return new boolean[] { false, false, true, false };
			} else if (dir == RIGHT) {
				return new boolean[] { false, false, false, true };
			}
		} else if (type == LR) {
			if (dir == UP || dir == DOWN) {
				return new boolean[] { true, true, false, false };
			} else if (dir == LEFT || dir == RIGHT) {
				return new boolean[] { false, false, true, true };
			}
		} else if (type == UR) {
			if (dir == UP) {
				return new boolean[] { true, false, false, true };
			} else if (dir == DOWN) {
				return new boolean[] { false, true, true, false };
			} else if (dir == LEFT) {
				return new boolean[] { true, false, true, false };
			} else if (dir == RIGHT) {
				return new boolean[] { false, true, false, true };
			}
		} else if (type == ULR) {
			if (dir == UP) {
				return new boolean[] { true, false, true, true };
			} else if (dir == DOWN) {
				return new boolean[] { false, true, true, true };
			} else if (dir == LEFT) {
				return new boolean[] { true, true, true, false };
			} else if (dir == RIGHT) {
				return new boolean[] { true, true, false, true };
			}
		} else if (type == UDLR) {
			return new boolean[] { true, true, true, true };
		}
		return null;
	}

	//
	static int N, M;
	static int[][] office;
	static int[][] copy;

	static int[] dirs;

	static List<Point> cameras;

	static int minArea;

	//
	static boolean isCamera(int n) {
		return R <= n && n <= UDLR;
	}

	static int calBlind() {
		int count = 0;
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (copy[n][m] == EMPTY)
					count++;
			}
		}
		return count;
	}

	static void copyOffice() {
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				copy[n][m] = office[n][m];
			}
		}
	}

	// UDLR
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	static int ny, nx;

	static boolean valid(int n, int m) {
		return 0 <= n && n < N && 0 <= m && m < M;
	}

	static void cameraOn() {
		copyOffice();

		int r, c;

		boolean[] canWatch = new boolean[DIR_CASE];

		for (Point camera : cameras) {
			r = camera.r;
			c = camera.c;

			canWatch = watchList(camera.type, camera.dir);

			for (int dir = 0; dir < DIR_CASE; dir++) {
				if (canWatch[dir]) {
					ny = r;
					nx = c;
					while (valid(ny, nx) && copy[ny][nx] != WALL) {
						copy[ny][nx] = '#';
						ny += dy[dir];
						nx += dx[dir];
					}
				}
			}
		}

		minArea = Math.min(minArea, calBlind());
	}

	static void setCamera() {
		for (int i = 0; i < cameras.size(); i++) {
			cameras.get(i).dir = dirs[i];
		}
		cameraOn();
	}

	static void permutation(int count) {
		if (count == cameras.size()) {
			setCamera();
		} else {
			for (int dir = 0; dir < DIR_CASE; dir++) {
				dirs[count] = dir;
				permutation(count + 1);
			}
		}
	}

	public static void main(String[] args) throws Exception {

		/* input */
		System.setIn(new FileInputStream("input.txt"));
		Scanner in = new Scanner(System.in);

		N = in.nextInt();
		M = in.nextInt();
		office = new int[N][M];
		copy = new int[N][M];

		cameras = new ArrayList<>();
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				office[n][m] = in.nextInt();
				if (isCamera(office[n][m])) {
					cameras.add(new Point(office[n][m], n, m));
				}
			}
		}

		minArea = Integer.MAX_VALUE;
		dirs = new int[cameras.size()];
		permutation(0);

		System.out.println(minArea);

		//
		in.close();
	}

}
