import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Main_1600_이도훈 {

	static int K;
	static int W;
	static int H;

	static int answer = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][][] visited;

	static int[][] dirs = {{-2, -1, 1}, {-1, -2, 1}, {1, -2, 1}, {2, -1, 1}, {2, 1, 1},
		{1, 2, 1}, {-1, 2, 1},
		{-2, 1, 1}, {1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}};


	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		// map 전체 초기화
		int[][] map = new int[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[K + 1][H][W];

		Queue<Pos> queue = new ArrayDeque<>();
		queue.add(new Pos(0, 0, 0, 0));
		visited[0][0][0] = true;

		Pos cur = null;
		boolean isArrived = false;
		while (!queue.isEmpty()) {
			cur = queue.poll();

			if (cur.x == W - 1 && cur.y == H - 1) {
				isArrived = true;
				break;
			}

			for (int[] dir : dirs) {
				int mx = cur.x + dir[0];
				int my = cur.y + dir[1];
				int mz = cur.horse + dir[2];

				if (!isMovable(mx, my, mz)) {
					continue;
				}
				if (map[my][mx] == 1) {
					continue;
				}
				if (visited[mz][my][mx]) {
					continue;
				}

				visited[mz][my][mx] = true;

				queue.add(new Pos(mx, my, cur.cnt + 1, mz));
			}
		}

		if (isArrived) {
			System.out.println(cur.cnt);
		} else {
			System.out.println(-1);
		}
	}


	static boolean isMovable(int x, int y, int z) {
		return x >= 0 && y >= 0 && x < W && y < H && z >= 0 && z <= K;
	}

	static class Pos {

		int x;
		int y;
		int cnt;
		int horse;

		public Pos(int x, int y, int cnt, int horse) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.horse = horse;
		}
	}

}
