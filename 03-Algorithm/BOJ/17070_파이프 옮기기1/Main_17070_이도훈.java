import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	// 가로(0) ,세로(1), 대각선(2) ,
	static int[][][] dirs = {{{1, 0, 0}, {1, 1, 2}}, {{0, 1, 1}, {1, 1, 2}},
		{{1, 0, 0}, {0, 1, 1}, {1, 1, 2}}};

	static int N;
	static int end;
	static int answer = 0;
	static int[][] map;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		end = N - 1;

		map = new int[N][N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(new Pipe(1, 0, 0));

		System.out.println(answer);
	}

	static void dfs(Pipe cur) {
		if (cur.x == end && cur.y == end) {
			answer++;
			return;
		}

		// 현재 파이프의 방향에 따라 달라짐
		map[cur.y][cur.x] = 1;

		// 방향에 따라 차지하는 범위가 달라서 그걸 체크 해줘야 함
		for (int[] dir : dirs[cur.dir]) {
			int x = cur.x + dir[0];
			int y = cur.y + dir[1];

			if(!isMovable(x,y)) continue;
			if(isBlocked(x,y,dir[2])) continue;

			dfs(new Pipe(x, y, dir[2]));
		}

		map[cur.y][cur.x] = 0;


	}

	static boolean isMovable(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

	static boolean isBlocked(int x, int y, int dir) {
		if(map[y][x] == 1) return true;

		if (dir == 2) {
			int up = map[y - 1][x];
			int left = map[y][x-1];

			if (up == 1 || left == 1) {
				return true;
			}
		}
		return false;
	}

	static class Pipe {

		int x;
		int y;
		int dir;

		public Pipe(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

}
