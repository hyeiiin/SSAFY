import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class CCTV {
	int x, y;
	int type;

	public CCTV(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

}

/**
 * 모든 CCTV가 감시할 수 있는 방향을 각각 나누어 구현한 코드.(현재 모든 경우에 대해 늘어진 코드) 코드는 더 압축할 수 있습니다.
 */
public class Main_15683_감시 {
	static int N;
	static int M;
	static int[][] map;
	static ArrayList<CCTV> list = new ArrayList<>();
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 5) {
					list.add(new CCTV(j, i, map[i][j]));// cctv는 따로 기록해두기
				}
			}
		}
		dfs(0, map);
		System.out.println(result);
	}

	/**
	 * CCTV 방향 돌리면서 가보기
	 * 
	 * @param idx
	 * @param prev
	 */
	static void dfs(int idx, int[][] prev) {
		int[][] v = new int[N][M];// dfs 호출마다 map복사를 위해서 새로 생성
		// 모든 CCTV 방향이 결정 된 상황
		if (idx == list.size()) {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (prev[i][j] == 0) { // 사각지대 카운팅
						cnt++;
					}
				}
			}
			result = Math.min(result, cnt); // 사각지대 최소의 개수 갱신하기
			return;
		}

		CCTV cctv = list.get(idx);
		int x = cctv.x;
		int y = cctv.y;
		// cctv 타입에 따라서 분기
		switch (cctv.type) {
		case 1: // 1번 CCTV는 한개의 방향으로 상,하,좌,우 돌릴 수 있음
			for (int d = 0; d < 4; d++) { // 4개 방향으로 돌려보기
				for (int i = 0; i < N; i++) { // 전달 받은 map copy하기
					v[i] = Arrays.copyOf(prev[i], M);
				}
				detect(v, x, y, d); // 결정된 방향으로 cctv 감시 표시하기
				dfs(idx + 1, v);// 다음 cctv 고르러가기
			}
			break;
		case 2: // 2번 cctv : 상-하/좌-우 2개 방향 돌릴 수 있음
			for (int d = 0; d < 2; d++) { // 2번 돌리고
				for (int i = 0; i < N; i++) {
					v[i] = Arrays.copyOf(prev[i], M);
				}
				// 방향은 현재 상,좌,하,우로 구성되어 있음
				// 2개 방향 호출하여 cctv 감시 처리
				detect(v, x, y, d);
				detect(v, x, y, d + 2);
				dfs(idx + 1, v); // 다음 cctv 고르러 가기
			}
			break;
		case 3: // 3번 cctv 상우/우하/하좌/좌상 4개 방향으로 돌릴 수 있음
			for (int d = 0; d < 4; d++) {
				// map copy
				for (int i = 0; i < N; i++) {
					v[i] = Arrays.copyOf(prev[i], M);
				}
				// 감시처리하기
				detect(v, x, y, d);
				detect(v, x, y, (d + 1) % 4);
				// 다음 cctv 고르러가기
				dfs(idx + 1, v);
			}
			break;
		case 4: // 4번 CCTV 좌상우/상우하/우하좌/하좌상 4개 방향 돌릴 수 있음
			for (int d = 0; d < 4; d++) {
				for (int i = 0; i < N; i++) {
					v[i] = Arrays.copyOf(prev[i], M);
				}
				// 각 방향 감시처리
				detect(v, x, y, d);
				detect(v, x, y, (d + 1) % 4); // index 넘어가지 않도록 나머지 연산
				detect(v, x, y, (d + 2) % 4);
				// 다음 CCTV 고르러가기
				dfs(idx + 1, v);
			}
			break;
		case 5: // 5번 CCTV : 상하좌우 1개
			for (int i = 0; i < N; i++) {
				v[i] = Arrays.copyOf(prev[i], M);
			}
			detect(v, x, y, 0);
			detect(v, x, y, 1);
			detect(v, x, y, 2);
			detect(v, x, y, 3);
			dfs(idx + 1, v);
			break;
		}

	}

	/**
	 * 호출한 방향으로 감시처리하는 메소드
	 * 1:상, 2: 좌, 3:하, 4: 우
	 * @param vMap :복사한 맵
	 * @param x    : CCTV 위치
	 * @param y
	 * @param dir  : 방향
	 */
	static void detect(int[][] vMap, int x, int y, int dir) {
		switch (dir) {
		case 0: // 상
			for (int i = x; i >= 0; i--) {
				if (vMap[y][i] == 6) {
					break;
				}
				vMap[y][i] = 9; //상관없는 번호로 마킹하기
			}
			break;
		case 1: // 좌
			for (int i = y; i >= 0; i--) {
				if (vMap[i][x] == 6) {
					break;
				}
				vMap[i][x] = 9;
			}
			break;
		case 2: // 하
			for (int i = x; i < M; i++) {
				if (vMap[y][i] == 6) {
					break;
				}
				vMap[y][i] = 9;
			}
			break;
		case 3: // 우
			for (int i = y; i < N; i++) {
				if (vMap[i][x] == 6) {
					break;
				}
				vMap[i][x] = 9;
			}
			break;
		}
	}

}