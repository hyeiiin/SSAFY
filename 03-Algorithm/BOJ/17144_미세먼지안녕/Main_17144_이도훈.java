import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17144_이도훈 {

	static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	static int[][] dust;
	static int C;
	static int R;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		int airconIdx = 0;

		Aircon[] aircon = new Aircon[2];

		dust = new int[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int num = Integer.parseInt(st.nextToken());

				if (num == -1) {
					aircon[airconIdx++] = new Aircon(j, i);
				}

				dust[i][j] = num;
			}
		}

		Queue<Dust> queue = new ArrayDeque<>();
		for (int t = 0; t < T; t++) {
			// 1. 먼지 날리기
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (dust[i][j] == -1 || dust[i][j] < 5) continue;

					int amount = dust[i][j] / 5;

					int cnt = 0;
					for (int[] dir : dirs) {
						int mx = j + dir[0];
						int my = i + dir[1];

						if (mx < 0 || my < 0 || mx >= C || my >= R) {
							continue;
						}
						if (dust[my][mx] == -1) {
							continue;
						}
						cnt++;

						// 여기서 바로 더해주면 안되고, 나중에 한번에 이동시켜야함
						queue.add(new Dust(mx, my, amount));
					}

					dust[i][j] = dust[i][j] - cnt * amount;
				}
			}

			while (!queue.isEmpty()) {
				Dust poll = queue.poll();

				dust[poll.y][poll.x] += poll.amount;
			}

			// 잘돌아가는지 체크해봐야할 듯?
			// 2. 공기청정기 작동
			aircon[0].up();
			aircon[1].down();
		}

		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(dust[i][j] == -1 || dust[i][j] == 0) continue;
				sum += dust[i][j];
			}
		}

		System.out.println(sum);
	}


	static class Aircon {

		int x;
		int y;

		public Aircon(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void up() {
			int[][] moves = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
			int[][] pushes = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

			int curX = x;
			int curY = y;

			int dir = 0;
			while (true) {
				int mX = curX + moves[dir][0];
				int mY = curY + moves[dir][1];

				if (mX < 0 || mY < 0 || mX >= C || mY > y) {
					dir = (dir + 1) % 4;
					continue;
				}
				curX = mX;
				curY = mY;

				if (dust[curY][curX] == -1) {
					break;
				}

				// 현재 위치 먼지
				int curDust = dust[curY][curX];

				dust[curY][curX] = 0;

				// 먼지 밀어내기
				int dX = curX + pushes[dir][0];
				int dY = curY + pushes[dir][1];

				if (dust[dY][dX] == -1) {
					continue;
				}
				dust[dY][dX] = curDust;
			}
		}

		public void down() {
			int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
			int[][] pushes = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

			int curX = x;
			int curY = y;

			int dir = 0;
			while (true) {
				int mX = curX + moves[dir][0];
				int mY = curY + moves[dir][1];

				if (mX < 0 || mY < y || mX >= C || mY >= R) {
					dir = (dir + 1) % 4;
					continue;
				}
				curX = mX;
				curY = mY;

				if (dust[curY][curX] == -1) {
					break;
				}

				// 현재 위치 먼지
				int curDust = dust[curY][curX];

				dust[curY][curX] = 0;

				// 먼지 밀어내기
				int dX = curX + pushes[dir][0];
				int dY = curY + pushes[dir][1];

				if (dust[dY][dX] == -1) {
					continue;
				}
				dust[dY][dX] = curDust;
			}
		}



	}

	static class Dust {

		int x;
		int y;
		int amount;

		public Dust(int x, int y, int amount) {
			this.x = x;
			this.y = y;
			this.amount = amount;
		}
	}


}
