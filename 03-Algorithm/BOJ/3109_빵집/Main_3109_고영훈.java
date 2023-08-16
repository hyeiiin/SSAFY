import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3109_고영훈 {
	// 파이프라인 방향은 우상, 우, 우하입니다.
	final static int[][] deltas = { { -1, 1 }, { 0, 1 }, { 1, 1 } };
	static int R;
	static int C;
	// 파이프가 지나간 곳은 건물로 취급합니다.
	static boolean[][] mat;

	/**
	 * 끝에 도착하는 데 성공하면 true를 반환하고 아니면 false를 반환합니다.
	 * 
	 * @param y
	 * @param x
	 * @return 도착 여부
	 */
	private static boolean dfs(final int y, final int x) {
		// 끝까지 가는 데 성공하면 true를 반환합니다.
		if (x == C - 1) {
			return true;
		}
		// 빵집 주인은 상남자라서 파이프 연결에 실패했다고 복구 같은 거 안 합니다.
		mat[y][x] = true;
		for (final int[] d : deltas) {
			final int ny = y + d[0];
			final int nx = x + d[1];
			if (ny >= 0 && ny < R && nx >= 0 && nx < C && !mat[ny][nx]) {
				// 끝까지 가는 데 성공했으면 다른 데 볼 필요 없이 바로 리턴합니다.
				if (dfs(ny, nx)) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		mat = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			final String line = br.readLine();
			for (int j = 0; j < C; j++) {
				if (line.charAt(j) == 'x') {
					mat[i][j] = true;
				}
			}
		}
		// 파이프라인에 성공한 횟수를 기록하고 출력합니다.
		int count = 0;
		for (int i = 0; i < R; i++) {
			if (dfs(i, 0)) {
				count++;
			}
		}
		System.out.println(count);
	}
}
