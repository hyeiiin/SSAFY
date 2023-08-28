import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {  // BOJ_15683_감시
	static StringBuilder sb;
	static StringTokenizer st;
	static int N, M;
	static List<CCTV> cctv;
	static int res = Integer.MAX_VALUE;

	// 상우하좌
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	// CCTV의 좌표, 타입 저장
	static class CCTV {
		int x, y, type;

		public CCTV(int x, int y, int type) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
		}

		@Override
		public String toString() {
			return "CCTV [x=" + x + ", y=" + y + ", type=" + type + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] lst = new int[N][M];
		cctv = new ArrayList<>();

		// 맵, CCTV 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int t = Integer.parseInt(st.nextToken());
				if (0 < t && t < 6) {
					cctv.add(new CCTV(i, j, t));
				}

				lst[i][j] = t;
			}
		}

		// 맵, CCTV 번호
		dfs(lst, 0);

		sb = new StringBuilder();
		sb.append(res);
		System.out.println(res);

	} // Main

	/**
	 * CCTV 각각 정해진 방향대로 감시해보는 함수
	 * @param lst : 작업할 맵
	 * @param cNum : CCTV 번호
	 */
	static void dfs(int[][] lst, int cNum) {

		// END : 모든 CCTV를 확인했다면,
		// 사각 지대의 최소 크기 갱신
		if (cNum == cctv.size()) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (lst[i][j] == 0) {
						sum++;
					}
				}
			}
			res = Math.min(res, sum);
			return;
		}

		// 작업할 CCTV 뽑아내기
		int cx = cctv.get(cNum).x;
		int cy = cctv.get(cNum).y;
		int ct = cctv.get(cNum).type;

		// 각 4방향에 대해서 검사
		for (int angle = 0; angle < 4; angle++) {
			// 작업할 맵을 미리 임시 저장
			int[][] backUp = deepCopy(lst);

			// CCTV의 Type에 따라 정해진 방향대로 체크함.
			// Deltas[]와 Index를 잘 비교하면 4방향 변경점이 보임.
			switch (ct) {
			case 1:
				sight(lst, cx, cy, angle + 1); // 1 2 3 4 -> 1 2 3 0
				break;

			case 2:
				sight(lst, cx, cy, angle + 1); // 1 2 3 4 -> 1 2 3 0
				sight(lst, cx, cy, angle + 3); // 3 4 5 6 -> 3 0 1 2
				break;
			case 3:
				sight(lst, cx, cy, angle); // 0 1 2 3 -> 0 1 2 3
				sight(lst, cx, cy, angle + 1); // 1 2 3 4 -> 1 2 3 0
				break;
			case 4:
				sight(lst, cx, cy, angle); // 0 1 2 3 -> 0 1 2 3
				sight(lst, cx, cy, angle + 1); // 1 2 3 4 -> 1 2 3 0
				sight(lst, cx, cy, angle + 3); // 3 4 5 6 -> 3 0 1 2
				break;
			case 5:
				sight(lst, cx, cy, angle);
				sight(lst, cx, cy, angle + 1);
				sight(lst, cx, cy, angle + 2);
				sight(lst, cx, cy, angle + 3);
				break;
			} // switch

			// 작업한 상태로 다음 CCTV로 재귀 호출
			dfs(lst, cNum + 1);
			
			// 작업한 맵은 다시 원상태로 돌린 후 다음 방향도 검사
			lst = deepCopy(backUp);

		} // for

	}

	/**
	 * 넘겨받은 방향대로 실제 감시 범위를 체크하는 함수
	 * @param lst : 작업할 맵
	 * @param x : 좌표
	 * @param y : 좌표
	 * @param dir : 방향
	 */
	static void sight(int[][] lst, int x, int y, int dir) {
		// 4방향에 대해서 순차적으로 검사
		dir %= 4;

		int nx = x;
		int ny = y;

		// 한 방향씩 감시 된 부분을 "9"로 마킹하여 체크하는 함수 
		while (true) {
			nx += dx[dir];
			ny += dy[dir];

			if (isIn(nx, ny)) {
				if (lst[nx][ny] == 0) {
					lst[nx][ny] = 9;  // 편의상 감시 된 부분을 9로 표현
				} 
				else if (lst[nx][ny] == 6) {  // 벽을 뚫지 못하므로 return
					return;
				}
			} 
			else {
				return;
			}
		}
	}

	// 깊은 복사
	static int[][] deepCopy(int[][] origin) {
		int[][] temp = new int[N][M];

		for (int i = 0; i < N; i++) {
			temp[i] = Arrays.copyOf(origin[i], M);
		}

		return temp;
	}

	// 범위 체크
	static boolean isIn(int nx, int ny) {
		return (0 <= nx && nx < N && 0 <= ny && ny < M);
	}

}
