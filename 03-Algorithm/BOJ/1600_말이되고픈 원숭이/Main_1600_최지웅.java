package todo.lesson._0823;

import java.io.*;
import java.util.*;

public class Main_1600_최지웅 {

	static int H, W;
	static int K;

	static int[][] board;

	static final int NONE = 0;
	static final int BLOCK = 1;

	//
	static final int[] dr = { -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, -1, 1 };

	static final int[] drHorse = { -2, -2, -1, -1, 1, 1, 2, 2 };
	static final int[] dcHorse = { -1, 1, -2, 2, -2, 2, -1, 1 };

	static final int CASE_HORSE = 8;

	static int nr, nc;

	//
	static class Monkey {
		int r, c;
		int k;
		int count;

		Monkey(int r, int c, int k, int count) {
			this.r = r;
			this.c = c;
			this.k = k;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Monkey [r=" + r + ", c=" + c + ", k=" + k + ", count=" + count + "]";
		}
	}

	static boolean[][] visited;

	static boolean valid(int r, int c) {
		return 0 <= r && r < H && 0 <= c && c < W;
	}

	static boolean found;
	static int ans;
	
	static int[][] memo;
	
	static boolean isWorthVisit(int r, int c, int k) {
		if (!visited[r][c]) return true;
		else {
			if (memo[r][c] > k) return true;
			else return false;
		}
	}
	
	static void BFS() {
		
		// init memo
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				memo[r][c] = Integer.MAX_VALUE;
			}
		}
		
		//
		Queue<Monkey> queue = new ArrayDeque<>();

		visited[0][0] = true;
		memo[0][0] = 0;
		queue.offer(new Monkey(0, 0, 0, 0));

		Monkey monkey;
		int r, c;
		int k;
		int count;

		while (!queue.isEmpty()) {

			monkey = queue.poll();

			r = monkey.r;
			c = monkey.c;
			k = monkey.k;
			count = monkey.count;
			
			if (r == H - 1 && c == W - 1) {
				found = true;
				ans = count;
				return;
			}

			// normal
			for (int dir = 0; dir < 4; dir++) {
				nr = r + dr[dir];
				nc = c + dc[dir];

				if (valid(nr, nc) && isWorthVisit(nr, nc, k) && board[nr][nc] != BLOCK) {
					visited[nr][nc] = true;
					memo[nr][nc] = k;
					queue.offer(new Monkey(nr, nc, k, count + 1));
				}
			}

			// horse
			if (k < K) {
				for (int dir = 0; dir < CASE_HORSE; dir++) {
					nr = r + drHorse[dir];
					nc = c + dcHorse[dir];

					if (valid(nr, nc) && isWorthVisit(nr, nc, k + 1) && board[nr][nc] != BLOCK) {
						visited[nr][nc] = true;
						memo[nr][nc] = k + 1;
						queue.offer(new Monkey(nr, nc, k + 1, count + 1));
					}
				}
			}
		}
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		K = in.nextInt();

		//
		W = in.nextInt();
		H = in.nextInt();
		board = new int[H][W];

		//
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				board[h][w] = in.nextInt();
			}
		}

		visited = new boolean[H][W];
		memo = new int[H][W];
		BFS();

		final int NOT_FOUND = -1;
		System.out.println(found ? ans : NOT_FOUND);

		//
		in.close();
	}
}
