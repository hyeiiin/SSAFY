import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main_14502_이도훈 {

	static int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int[][] map;
	static int N;
	static int M;
	static int max;
	static int size;
	static int wall;

	public static void main(String[] args) throws Exception {

		// 벽을 세워두면서 dfs?
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		max = Integer.MIN_VALUE;
		size = M * N;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 1) {
					wall++;
				}
			}
		}

		dfs(0, 0);

		System.out.println(max);

	}


	public static void dfs(int depth, int idx) {
		// 벽을 세울 수 있는 갯수는 3개

		if (depth == 3) {
			// 체크하기 , bfs
			int[][] checkMap = new int[N][M];

			for (int i = 0; i < N; i++) {
				checkMap[i] = map[i].clone();
			}

			int cnt = 0;

			Queue<Pos> queue = new ArrayDeque<>();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (checkMap[i][j] == 2) {
						queue.add(new Pos(j, i));
						cnt++;
						while (!queue.isEmpty()) {
							Pos cur = queue.poll();

							for (int[] dir : dirs) {
								int mx = cur.x + dir[0];
								int my = cur.y + dir[1];

								if (mx < 0 || my < 0 || mx >= M || my >= N) {
									continue;
								}
								if (checkMap[my][mx] == 0 ) {
									checkMap[my][mx] = 3;
									queue.add(new Pos(mx, my));
									cnt++;
								}
							}
						}
					}
				}
			}
			max = Math.max(max, size - cnt - wall - 3);
			return;
		}

		if(idx >= size) return;

		int x = idx % M;
		int y = idx / M;


		if (map[y][x] == 1 || map[y][x] == 2) {
			dfs(depth, idx + 1);
		}else {
			// 벽세우기
			map[y][x] = 1;
			dfs(depth + 1, idx + 1);

			// 안세우기
			map[y][x] = 0;
			dfs(depth, idx + 1);
		}
	}

	static class Pos {

		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
