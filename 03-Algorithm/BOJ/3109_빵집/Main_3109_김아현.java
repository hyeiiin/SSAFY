package ssafy.Boj;

import java.io.*;
import java.util.*;
//3109. 빵집
public class Main_3109_김아현 {

	// 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선
	static int[] dr = { -1, 0, 1 };
	static int[] dc = { 1, 1, 1 };

	static char[][] map;
	static boolean[][] visited;
	static int r, c, cnt;
	static boolean complete;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new char[r][c];
		visited = new boolean[r][c];
		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			map[i] = str.toCharArray();
		}

		cnt = 0;
		// 위에 열부터 확인
		for (int i = 0; i < r; i++) {
			complete = false;
			moveToEnd(i, 0);
		}

		System.out.println(cnt);

	}

	// dfs 탐색
	static void moveToEnd(int row, int col) {
		
		// 기저조건
		// 마지막 행에 도달했으면 파이프 연결 성공
		if (col == c - 1) {
			complete = true;
			cnt++;
			return;
		}
	
		
		// 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선 순서로 순회
		for (int d = 0; d < 3; d++) {
			int nr = row + dr[d];
			int nc = col + dc[d];

			// 경계 안에 있고
			if (inArea(nr, nc)) {
				// 방문하지 않은 빈칸이라면
				if (map[nr][nc] == '.' && !visited[nr][nc]) {
					// 방문처리
					visited[nr][nc] = true;
					// 이동할 라인 검사
					moveToEnd(nr, nc);
					// 완료되었으면 다음으로 넘어가지 않음
					if(complete) return;
				}
			}
		}
		return;
	}

	/**
	 * 경계 확인 메소드
	 * @param row
	 * @param col
	 * @return
	 */
	static boolean inArea(int row, int col) {
		return row >= 0 && row < r && col >= 0 && col < c;
	}

}
