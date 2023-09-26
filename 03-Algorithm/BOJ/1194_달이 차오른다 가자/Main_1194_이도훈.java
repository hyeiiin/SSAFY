import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1194_이도훈 {

	static int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

	static int N;
	static int M;
	static char[][] map;
	static boolean[][][] visited;

	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[7][N][M];

		Pos start = null;

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);

				if (map[i][j] == '0') {
					start = new Pos(j, i, 0, 0);
				}
			}
		}

		boolean[][][] visited = new boolean[64][N][M];

		Queue<Pos> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start.keys][start.y][start.x] = true;

		int answer = -1;

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			for (int[] dir : dirs) {
				int mx = cur.x + dir[0];
				int my = cur.y + dir[1];

				if (!isMovable(mx, my)) {
					continue;
				}
				if (map[my][mx] == '#') {
					continue;
				}
				if (visited[cur.keys][my][mx]) {
					continue;
				}
				if (map[my][mx] == '1') {
					System.out.println(cur.cnt + 1);
					return;
				}

				visited[cur.keys][my][mx] = true;

				if (map[my][mx] >= 'a' && map[my][mx] <= 'f') {
					// 열쇠인 경우
					int key = 1 << (map[my][mx] - 'a');

					// 열쇠 추가
					Pos pos = new Pos(mx, my, cur.cnt + 1, cur.keys | key);

					queue.add(pos);
					visited[pos.keys][pos.y][pos.x] = true;

					continue;
				}
				if (map[my][mx] >= 'A' && map[my][mx] <= 'F') {
					// 문인 경우
					int door = 1 << (map[my][mx] - 'A');

					// 열쇠 확인
					if ((cur.keys & door) != (int) Math.pow(2, map[my][mx] - 'A')) {
						// 키가 없는 경우
						continue;
					}
				}

				queue.add(new Pos(mx, my, cur.cnt + 1, cur.keys));
			}
		}
		System.out.println(-1);
	}

	static boolean isMovable(int x, int y) {
		return x >= 0 && y >= 0 && x < M && y < N;
	}

	static class Pos {

		int x;
		int y;
		int cnt;
		int keys;

		public Pos(int x, int y, int cnt, int keys) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.keys = keys;
		}

	}

}
