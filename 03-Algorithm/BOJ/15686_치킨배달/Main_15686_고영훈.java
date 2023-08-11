import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15686_고영훈 {
	final static List<Point> homes = new ArrayList<>();
	final static List<Point> chickens = new ArrayList<>();
	static int N;
	static int M;

	/**
	 * 좌표
	 */
	static class Point {
		final int y;
		final int x;

		public Point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

	/**
	 * @param chickenArray: 계산할 치킨집 배열입니다.
	 * @return sumDist: 도시의 치킨 거리
	 */
	private static int calculate(final Point[] chickenArray) {
		int sumDist = 0;
		for (final Point h : homes) {
			int minDist = Integer.MAX_VALUE;
			for (final Point c : chickenArray) {
				minDist = Math.min(minDist, Math.abs(c.y - h.y) + Math.abs(c.x - h.x));
			}
			sumDist += minDist;
		}
		return sumDist;
	}

	/**
	 * 조합: chickens.size() (>= M)에서 M개의 치킨집을 고릅니다.
	 * 
	 * @param       chickenArray: 고른 치킨집을 저장하는 배열입니다.
	 * @param count
	 * @param start
	 * @return 도시의 치킨 거리의 최소값을 반환합니다.
	 */
	private static int combination(final Point[] chickenArray, final int count, final int start) {
		if (count == M) {
			return calculate(chickenArray);
		}
		int minSumDist = Integer.MAX_VALUE;
		for (int i = start, end = chickens.size(); i < end; i++) {
			chickenArray[count] = chickens.get(i);
			minSumDist = Math.min(minSumDist, combination(chickenArray, count + 1, i + 1));
		}
		return minSumDist;
	}

	public static void main(String[] args) throws Exception {
		// 입력 시작
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				final int n = Integer.parseInt(st.nextToken());
				if (n == 1) {
					homes.add(new Point(i, j));
				} else if (n == 2) {
					chickens.add(new Point(i, j));
				}
			}
		}
		// 입력 끝
		final int answer = combination(new Point[M], 0, 0);
		System.out.println(answer);
	}
}
