package ssafy.boj;

import java.io.*;
import java.util.*;

/*
 * 15683. 감시
 * ? 사각 지대의 최소 크기 ? 
 *  = 0인 공간의 개수의 최소값
 * 1. 4방향 중에서 cctv가 가질 수 있는 경우의 수를 뽑는다.
 * 2. 사각지대의 최소 크기를 계산하여 갱신한다. 
 * 
 * 0 빈 칸  1~5 CCTV 6 벽
 */

// CCTV 클래스 생성
class CCTV {
	int r; // CCTV row 위치
	int c; // CCTV column 위치
	int t; // CCTV 번호

	public CCTV(int r, int c, int type) {
		super();
		this.r = r;
		this.c = c;
		this.t = type;
	}
}

public class _15683_Boj {

	// 상우하좌
	// 0 1 2 3
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[] cIdx;
	static int n, m, cnt, ccnt;
	static int minCnt = Integer.MAX_VALUE;

	static ArrayList<CCTV> cctvList;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 세로
		m = Integer.parseInt(st.nextToken()); // 가로

		// 사무실 map 초기 입력
		map = new int[n][m]; // map 초기화
		ccnt = 0; // cctv 개수
		cctvList = new ArrayList<>(); // cctv리스트 (cctv 좌표, cctv 번호)
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0 && map[i][j] < 6) {
					cctvList.add(new CCTV(i, j, map[i][j]));
					ccnt++;
				}
			}
		}

		cIdx = new int[ccnt]; // cctv의 개수에 대하여 가능한 4방향의 가짓수
		makePer(0); // cctv의 4방향 조합 계산

		System.out.println(minCnt);
	}

	/**
	 * 사각지대 최소 크기를 계산하는 메소드.
	 */
	static void countingBlindSpot() {
		cnt = 0; // 사각지대 개수
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// 벽이 있는 곳도 방문 처리 (사각지대 영역에서 제외)
				if (map[i][j] == 6) {
					visited[i][j] = true;
				}

				// 방문하지 않은 곳은 사각지대임.
				if (!visited[i][j]) {
					cnt++;
				}
			}
		}

		// 사각 지대의 최소 크기 갱신
		minCnt = Math.min(cnt, minCnt);
	}

	/**
	 * cctv가 가질 수 있는 4방향 조합 (중복 O, 순서 O) 중복 순열
	 * 
	 * @param cnt
	 */
	static void makePer(int cnt) {
		// 길이가 ccnt 사이즈로 완성되면
		if (cnt == ccnt) {
			visited = new boolean[n][m]; // 방문 배열 초기화
			for (int i = 0; i < ccnt; i++) {
				// 방향값 확인해서 감시할 구역 찾기
				getDiR(cctvList.get(i), cIdx[i]);
			}

			countingBlindSpot(); // 사각지대 최소 크기 세기
			return;
		}

		for (int i = 0; i < 4; i++) {
			cIdx[cnt] = i;
			makePer(cnt + 1);
		}
	}

	/**
	 * 확인할 cctv의 방향값을 확인하는 메소드
	 * 
	 * @param type cctv 번호 (1~5)
	 * @param dir  방향
	 */
	static void getDiR(CCTV cctv, int dir) {
		int row = cctv.r;
		int col = cctv.c;
		int type = cctv.t;

		// CCTV가 움직일 수 있는 상우하좌 방향 0~5 (cctv : 1~5)

		switch (type) {
		// 1번 CCTV
		// 상 하 좌 우
		case 1:
			if (dir == 0) {
				searchArea(row, col, 0);
			} else if (dir == 1) {
				searchArea(row, col, 1);
			} else if (dir == 2) {
				searchArea(row, col, 2);
			} else {
				searchArea(row, col, 3);
			}
			break;
		// 2번 CCTV
		// 우좌 상하 우좌 상하
		case 2:
			if (dir == 0) {
				searchArea(row, col, 1);
				searchArea(row, col, 3);
			} else if (dir == 1) {
				searchArea(row, col, 0);
				searchArea(row, col, 2);
			} else if (dir == 2) {
				searchArea(row, col, 1);
				searchArea(row, col, 3);
			} else {
				searchArea(row, col, 0);
				searchArea(row, col, 2);
			}
			break;
		// 3번 CCTV
		// 상우 우하 하좌 좌상
		case 3:
			if (dir == 0) {
				searchArea(row, col, 0);
				searchArea(row, col, 1);
			} else if (dir == 1) {
				searchArea(row, col, 1);
				searchArea(row, col, 2);
			} else if (dir == 2) {
				searchArea(row, col, 2);
				searchArea(row, col, 3);
			} else {
				searchArea(row, col, 3);
				searchArea(row, col, 0);
			}
			break;
		// 4번 CCTV
		// 좌상우 상우하 우하좌 하좌우
		case 4:
			if (dir == 0) {
				searchArea(row, col, 0);
				searchArea(row, col, 1);
				searchArea(row, col, 3);
			} else if (dir == 1) {
				searchArea(row, col, 1);
				searchArea(row, col, 2);
				searchArea(row, col, 0);
			} else if (dir == 2) {
				searchArea(row, col, 2);
				searchArea(row, col, 3);
				searchArea(row, col, 1);
			} else {
				searchArea(row, col, 3);
				searchArea(row, col, 0);
				searchArea(row, col, 2);
			}
			break;
		// 5번 CCTV
		// 4 방향값 상관없이 상하좌우
		case 5:
			searchArea(row, col, 0);
			searchArea(row, col, 1);
			searchArea(row, col, 2);
			searchArea(row, col, 3);
			break;
		}

	}

	/**
	 * 방향에 따라 카메라가 감시하는 구역을 찾는 메소드
	 * 
	 * @param r   확인할 row 값
	 * @param c   확인할 col 값
	 * @param dir 상하좌우 방향 값
	 */
	static void searchArea(int r, int c, int dir) {
		// 방문하지 않은 값이면 true 처리
		if (!visited[r][c]) {
			visited[r][c] = true;
		}

		// 다음 방향 값.
		int nr = r + dr[dir];
		int nc = c + dc[dir];

		// 벽을 만나거나 경계를 벗어나면 종료.
		if (inArea(nr, nc)) {
			if (map[nr][nc] == 6) {

				return;
			}
			// 다음 좌표 기준으로 확인
			searchArea(nr, nc, dir);
		} else {
			return;
		}
	}

	/**
	 * 경계 확인 메소드
	 */
	static boolean inArea(int row, int col) {
		return row >= 0 && row < n && col >= 0 && col < m;
	}
}
