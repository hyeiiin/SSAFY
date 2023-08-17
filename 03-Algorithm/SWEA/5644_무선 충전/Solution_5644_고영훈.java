import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_5644_고영훈 {
	// 상우하좌
	final static int[] dx = { 0, 0, 1, 0, -1 };
	final static int[] dy = { 0, -1, 0, 1, 0 };

	/**
	 * A, B 좌표
	 */
	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * AP 정보
	 */
	private static class AP {
		final int x;
		final int y;
		final int coverage;
		final int performance;

		public AP(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.coverage = c;
			this.performance = p;
		}
	}

	/**
	 * |x1 - x2| + |y1 + y2|
	 */
	private static int getDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	/**
	 * 좌표 이동
	 * 
	 * @param p: 이동시킬 대상
	 * @param n: dx, dy 인덱스
	 */
	private static void move(Pos p, int n) {
		p.x += dx[n];
		p.y += dy[n];
	}

	/**
	 * A와 B가 충전 가능한 AP 리스트에서 가능한 최대값을 어떻게든 구합니다.
	 * 
	 * @param as
	 * @param bs
	 * @return
	 */
	private static int getMaxCharge(final List<AP> as, final List<AP> bs) {
		int max = 0;
		if (as.isEmpty()) {
			// A가 없으면 B 혼자 순회해서 최대값을 구합니다.
			for (final AP b : bs) {
				max = Math.max(max, b.performance);
			}
		} else {
			// A와 B의 프로덕트로 조합을 구해서 합니다. (순회 낭비 있음)
			for (final AP a : as) {
				final int chargeA = a.performance;
				int chargeB = 0;
				for (final AP b : bs) {
					// A와 B가 같은 충전기를 쓰는 경우는 세지 않습니다.
					// 같이 충전하면 잘 되도 본전이고, 대부분 손해이기 때문입니다.
					if (a != b) {
						chargeB = Math.max(chargeB, b.performance);
					}
				}
				max = Math.max(max, chargeA + chargeB);
			}
		}
		return max;
	}

	public static void main(String[] args) throws Exception {
		final StringBuilder sb = new StringBuilder();
		// 입력 시작
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			final int M = Integer.parseInt(st.nextToken());
			final int A = Integer.parseInt(st.nextToken());
			// A, B가 움직이는 경로 배열
			final int[] pathA = new int[M];
			final int[] pathB = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				pathA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				pathB[i] = Integer.parseInt(st.nextToken());
			}
			// AP 배열
			final AP[] aps = new AP[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				final AP ap = new AP(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				aps[i] = ap;
			}
			// 입력 끝

			// 총 충전값을 구합니다.
			int totalCharge = 0;
			final Pos posA = new Pos(1, 1);
			final Pos posB = new Pos(10, 10);
			// 각 이동마다 A와 B가 닿는 충전기 리스트를 구합니다.
			final List<AP> apListA = new ArrayList<>();
			final List<AP> apListB = new ArrayList<>();
			for (int i = 0; i <= M; i++) {
				for (int j = 0; j < A; j++) {
					final AP ap = aps[j];
					if (getDist(posA.x, posA.y, ap.x, ap.y) <= ap.coverage) {
						apListA.add(ap);
					}
					if (getDist(posB.x, posB.y, ap.x, ap.y) <= ap.coverage) {
						apListB.add(ap);
					}
				}
				// 가능한 많이 충전합니다.
				totalCharge += getMaxCharge(apListA, apListB);
				// 다음 턴에 또 계산할 거니까 비웁니다.
				apListA.clear();
				apListB.clear();
				// A, B를 이동시키되, 마지막 계산이 끝나고는 이동하지 않습니다.
				if (i < M) {
					move(posA, pathA[i]);
					move(posB, pathB[i]);
				}
			}
			// 출력
			sb.append("#" + tc + " " + totalCharge + "\n");
		}
		System.out.println(sb);
	}
}
