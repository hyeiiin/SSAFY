import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_17135_고영훈 {
	// 화살 방향 순서: 좌상우
	final static Pos[] deltas = { new Pos(0, -1), new Pos(-1, 0), new Pos(0, 1) };
	// R: 궁수는 3명
	// archerXArr: 조합으로 뽑을 궁수 x좌표 위치 배열
	final static int R = 3;
	final static int[] archerXArr = new int[R];
	static int N;
	static int M;
	static int D;
	// mat: 적들이 분포한 장소
	// matOri: 모든 공격이 끝나고 다시 되돌리기 위한 원본 배열
	// visited: BFS용
	static boolean[][] mat;
	static boolean[][] matOri;
	static boolean[][] visited;

	/**
	 * 좌표
	 */
	private static class Pos {
		final int y;
		final int x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	/**
	 * 궁수 위치를 받아 BFS로 적을 찾고 위치를 반환합니다.
	 */
	private static Pos bfs(final int y, final int x) {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
		List<Pos> q = new ArrayList<>();
		q.add(new Pos(y - 1, x));
		visited[y - 1][x] = true;
		for (int i = 0; i < D && !q.isEmpty(); i++) {
			final List<Pos> newQ = new ArrayList<>();
			for (final Pos p : q) {
				if (mat[p.y][p.x]) {
					return p;
				}
				for (final Pos d : deltas) {
					final int ny = p.y + d.y;
					final int nx = p.x + d.x;
					if (ny >= 0 && nx >= 0 && nx < M && !visited[ny][nx]) {
						newQ.add(new Pos(ny, nx));
						visited[ny][nx] = true;
					}
				}
			}
			q = newQ;
		}
		return null;
	}

	/**
	 * 궁수가 적을 찾은 다음에 동시에 쏘고 제거된 적 수를 반환합니다.
	 * 
	 * @param limitY: 궁수의 y좌표
	 */
	private static int attack(final int limitY) {
		final List<Pos> ps = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			final Pos p = bfs(limitY, archerXArr[i]);
			if (p != null) {
				ps.add(p);
			}
		}
		int removed = 0;
		for (final Pos p : ps) {
			if (mat[p.y][p.x]) {
				mat[p.y][p.x] = false;
				removed++;
			}
		}
		return removed;
	}

	/**
	 * 조합이 완성되면 게임이 끝날 때까지 공격합니다.
	 */
	private static int combination(final int count, final int start) {
		if (count == R) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					mat[i][j] = matOri[i][j];
				}
			}
			int removed = 0;
			for (int i = N; i > 0; i--) {
				removed += attack(i);
			}
			return removed;
		}
		int max = 0;
		for (int i = start; i < M; i++) {
			archerXArr[count] = i;
			max = Math.max(max, combination(count + 1, i + 1));
		}
		return max;
	}

	public static void main(String[] args) throws Exception {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		mat = new boolean[N][M];
		matOri = new boolean[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				matOri[i][j] = st.nextToken().equals("1");
			}
		}
		System.out.println(combination(0, 0));
	}
}
