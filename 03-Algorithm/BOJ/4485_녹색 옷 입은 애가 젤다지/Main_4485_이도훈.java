import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_4485_이도훈 {

	static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		StringBuilder sb = new StringBuilder();
		int idx = 0;
		while (true) {
			idx++;

			int N = Integer.parseInt(br.readLine());

			if(N == 0) break;

			int[][] map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[][] dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dp[i], Integer.MAX_VALUE);
			}

			Queue<Node> queue = new ArrayDeque<>();
			queue.add(new Node(0, 0, map[0][0]));

			dp[0][0] = map[0][0];

			while (!queue.isEmpty()) {
				Node cur = queue.poll();
				if(dp[cur.y][cur.x] < cur.cnt) continue;

				for (int[] dir : dirs) {
					int mx = cur.x + dir[0];
					int my = cur.y + dir[1];

					if(mx < 0 || my < 0 || mx >= N || my >= N) continue;
					int newCnt = cur.cnt + map[my][mx];
					if(dp[my][mx] <= newCnt) continue;

					dp[my][mx] = newCnt;

					queue.add(new Node(mx, my, newCnt));
				}
			}

			sb.append("Problem ").append(idx).append(": ").append(dp[N - 1][N - 1]).append("\n");
		}

		System.out.println(sb);
	}

	static class Node {
		int x;
		int y;
		int cnt;

		public Node(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}
