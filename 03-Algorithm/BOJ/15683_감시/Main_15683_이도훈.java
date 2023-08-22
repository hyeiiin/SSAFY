import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15683_이도훈 {

	static int[][] office;
	static int N;
	static int M;
	static int end;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		end = N * M;

		office = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0);
		System.out.println(answer);
	}


	static void dfs(int depth) {
		if (depth == end) {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(office[i][j] == 0) cnt++;
				}
			}

			answer = Math.min(answer, cnt);
			return;
		}

		int x = depth % M;
		int y = depth / M;

		switch (office[y][x]) {
			case 1:
				up(x, y, true);
				dfs(depth + 1);
				up(x, y, false);
				down(x, y, true);
				dfs(depth + 1);
				down(x, y, false);
				left(x, y, true);
				dfs(depth + 1);
				left(x, y, false);
				right(x, y, true);
				dfs(depth + 1);
				right(x, y, false);
				break;
			case 2:
				left(x, y, true);
				right(x, y, true);
				dfs(depth + 1);
				left(x, y, false);
				right(x, y, false);
				up(x, y, true);
				down(x, y, true);
				dfs(depth + 1);
				up(x, y, false);
				down(x, y, false);
				break;
			case 3:
				up(x, y, true);
				right(x, y, true);
				dfs(depth + 1);
				up(x, y, false);
				down(x, y, true);
				dfs(depth+1);
				right(x, y, false);
				left(x, y, true);
				dfs(depth + 1);
				down(x, y, false);
				up(x, y, true);
				dfs(depth + 1);
				left(x, y, false);
				up(x, y, false);
				break;
			case 4:
				left(x, y, true);
				up(x, y, true);
				right(x, y, true);
				dfs(depth + 1);
				left(x, y, false);
				down(x, y, true);
				dfs(depth + 1);
				up(x, y, false);
				left(x, y, true);
				dfs(depth + 1);
				right(x, y, false);
				up(x, y, true);
				dfs(depth + 1);
				down(x, y, false);
				left(x, y, false);
				up(x, y, false);
				break;
			case 5:
				left(x, y, true);
				up(x, y, true);
				right(x, y, true);
				down(x, y, true);
				dfs(depth + 1);
				left(x, y, false);
				up(x, y, false);
				right(x, y, false);
				down(x, y, false);
				break;
			default:
				dfs(depth + 1);
				break;
		}

	}

	static void up(int x, int y, boolean isMinus) {
		for (int i = y - 1; i >= 0; i--) {
			if (office[i][x] == 6) {
				break;
			}
			if(checkCCTV(x,i) ) continue;
			if (isMinus) {
				office[i][x]--;
			} else {
				office[i][x]++;
			}
		}
	}

	static void down(int x, int y, boolean isMinus) {
		for (int i = y + 1; i < N; i++) {
			if (office[i][x] == 6) {
				break;
			}
			if(checkCCTV(x,i)) continue;
			if (isMinus) {
				office[i][x]--;
			} else {
				office[i][x]++;
			}
		}
	}

	static void left(int x, int y, boolean isMinus) {
		for (int i = x - 1; i >= 0; i--) {
			if (office[y][i] == 6) {
				break;
			}
			if (checkCCTV(i,y) ) continue;
			if (isMinus) {
				office[y][i]--;
			} else {
				office[y][i]++;
			}
		}
	}

	static void right(int x, int y, boolean isMinus) {
		for (int i = x + 1; i < M; i++) {
			if (office[y][i] == 6) {
				break;
			}
			if (checkCCTV(i,y)) continue;
			if (isMinus) {
				office[y][i]--;
			} else {
				office[y][i]++;
			}
		}
	}

	static boolean checkCCTV(int x, int y) {
		if (office[y][x] == 1 || office[y][x] == 2 || office[y][x] == 3 || office[y][x] == 4
			|| office[y][x] == 5) {
			return true;
		}
		return false;
	}
}
