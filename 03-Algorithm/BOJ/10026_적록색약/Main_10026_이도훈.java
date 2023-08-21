import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main_10026_이도훈 {

	static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	static char[][] normal;
	static char[][] weak;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		normal = new char[N][N];
		weak = new char[N][N];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			normal[i] = input.toCharArray();

			for (int j = 0; j < N; j++) {
				weak[i][j] =
					input.charAt(j) == 'R' || input.charAt(j) == 'G' ? 'R' : input.charAt(j);
			}
		}

		System.out.println(bfs(normal) +" " +bfs(weak));


	}

	private static int bfs(char[][] map) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 'N' ) continue;
				char base = map[i][j];

				Queue<int[]> queue = new ArrayDeque<>();

				queue.add(new int[]{j, i});
				map[i][j] = 'N';

				while (!queue.isEmpty()) {
					int[] cur = queue.poll();

					for (int[] dir : dirs) {
						int mx = dir[0] + cur[0];
						int my = dir[1] + cur[1];

						if(mx < 0 || my < 0 || mx >= N || my >= N) continue;
						if(map[my][mx] != base) continue;

						map[my][mx] = 'N';
						queue.add(new int[]{mx, my});
					}
				}
				cnt++;
			}
		}
		return cnt;
	}



}
