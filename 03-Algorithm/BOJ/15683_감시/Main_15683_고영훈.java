import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15683_고영훈 {
	// 입력값
	static int N;
	static int M;
	static int[][] mat;
	// CCTV 좌표 목록
	final static List<Pos> cctvPosList = new ArrayList<>();

	// 상하좌우
	final static Pos up = new Pos(-1, 0);
	final static Pos down = new Pos(1, 0);
	final static Pos left = new Pos(0, -1);
	final static Pos right = new Pos(0, 1);
	// CCTV 종류별 delta 목록
	final static Pos[][] cctvDeltas = {
			null,
			{ right },
			{ left, right },
			{ up, right },
			{ up, left, right },
			{ up, down, left, right }
	};
	// CCTV 종류별 delta 목록을 회전시켜서 방향별로 저장한 목록
	final static Pos[][][] cctvDeltasbyRotate = {
			cctvDeltas,
			rotateCctvAll(cctvDeltas, 1),
			rotateCctvAll(cctvDeltas, 2),
			rotateCctvAll(cctvDeltas, 3)
	};

	/**
	 * 좌표, 방향
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
	 * 방향 회전
	 */
	private static Pos rotateDelta(final Pos d) {
		if (d == up) {
			return right;
		}
		if (d == right) {
			return down;
		}
		if (d == down) {
			return left;
		}
		if (d == left) {
			return up;
		}
		return null;
	}

	/**
	 * CCTV 회전
	 */
	private static Pos[] rotateCctv(final Pos[] ds) {
		final int length = ds.length;
		final Pos[] result = new Pos[length];
		for (int i = 0; i < length; i++) {
			result[i] = rotateDelta(ds[i]);
		}
		return result;
	}

	/**
	 * CCTV 목록을 받아서 회전된 CCTV 목록 반환
	 */
	private static Pos[][] rotateCctvAll(final Pos[][] dss, final int repeat) {
		final int length = dss.length;
		final Pos[][] result = new Pos[length][];
		for (int i = 0; i < length; i++) {
			if (dss[i] == null) {
				continue;
			}
			result[i] = rotateCctv(dss[i]);
			for (int j = 1; j < repeat; j++) {
				result[i] = rotateCctv(result[i]);
			}
		}
		return result;
	}

	/**
	 * CCTV의 위치와 CCTV가 볼 방향을 받아서 보는 데 성공한 좌표 목록 반환
	 */
	private static List<Pos> monitor(final Pos p, final Pos d) {
		final List<Pos> result = new ArrayList<>();
		for (int y = p.y + d.y, x = p.x + d.x;
				y >= 0 && y < N && x >= 0 && x < M;
				y += d.y, x += d.x) {
			final int v = mat[y][x];
			if (v == 6) {
				break;
			}
			if (v != 0) {
				continue;
			}
			result.add(new Pos(y, x));
		}
		return result;
	}

	/**
	 * 중복순열: CCTV를 회전시키는 모든 경우에서 CCTV가 볼 수 있는 영역의 최대값을 구합니다.
	 */
	private static int permutationRepeat(final int index) {
		int result = 0;
		if (index == cctvPosList.size()) {
			return result;
		}
		// CCTV의 좌표와 CCTV 종류를 구합니다.
		final Pos pos = cctvPosList.get(index);
		final int type = mat[pos.y][pos.x];
		// CCTV를 4방향으로 회전합니다.
		for (int rotate = 0; rotate < 4; rotate++) {
			// 1. 현재 CCTV로 새로 본 좌표 목록을 구합니다.
			final List<Pos> monitored = new ArrayList<>();
			for (final Pos delta : cctvDeltasbyRotate[rotate][type]) {
				monitored.addAll(monitor(pos, delta));
			}
			// 2. 나머지 CCTV를 위해 현재 CCTV로 새로 본 좌표의 값을 -1로 설정합니다.
			for (final Pos m : monitored) {
				mat[m.y][m.x] = -1;
			}
			// 3. 나머지 CCTV에서 최대값을 얻고 좌표 목록의 수를 더해서 현재 함수의 최대값을 갱신합니다.
			result = Math.max(result, permutationRepeat(index + 1) + monitored.size());
			// 4. 다음 회전을 위해 -1로 설정한 좌표들을 다시 0으로 복구합니다.
			for (final Pos m : monitored) {
				mat[m.y][m.x] = 0;
			}
			// 2는 90도로 한번만 회전하면 됩니다.
			if (type == 2) {
				rotate += 2;
			}
			// 5는 회전하지 않아도 됩니다.
			if (type == 5) {
				break;
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new int[N][M];
		int zeros = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				final int v = Integer.parseInt(st.nextToken());
				mat[i][j] = v;
				if (v == 0) {
					zeros++;
				} else if (v < 6) {
					cctvPosList.add(new Pos(i, j));
				}
			}
		}
		// 사각지대 = 처음 0 개수 - CCTV로 볼 수 있는 최대 영역
		System.out.println(zeros - permutationRepeat(0));
	}
}
