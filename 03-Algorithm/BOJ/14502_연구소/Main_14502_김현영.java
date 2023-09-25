import java.io.*;
import java.util.*;

public class Main_14502_김현영 {

	// 우하좌상
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int wall = 3;		//초기 벽의 수
	static int n, m, safeArea;	//행크기, 열크기, 최대 안전구역 수
	static int[][] map;			//지도
	static boolean visited[][];	//방문배열
	static Deque<Point> q;		//바이러스 좌표를 가진 큐

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// 지도 입력
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		safeArea = 0;	//최대안전구역 범위 0으로 초기화
		visited = new boolean[n][m];	
		construct();
		System.out.println(safeArea);
	}

	static void construct() {
		// 벽을 3개 세웠다면 안전구역 구하기
		if (wall == 0) {
			findSafeArea();
			return;
		}

		// 지도에서 0이 있으면 벽 세우기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0 && !visited[i][j]) {
					// 벽 세우고 방문처리
					map[i][j] = 1;
					wall--;
					visited[i][j] = true;
					construct();
					// 벽 없애고 방문처리 해제
					map[i][j] = 0;
					wall++;
					visited[i][j] = false;
				}
			}
		}

	}

	// 안전구역 찾기
	static void findSafeArea() {
		int area = 0; // 안전구역수
		q = new ArrayDeque<>();	//바이러스 좌표를 가진 큐
		// 배열 복사
		int[][] temp = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				temp[i][j] = map[i][j];
				if (temp[i][j] == 2)
					q.offer(new Point(i, j));
				if (temp[i][j] == 0)
					area++;

			}
		}

		// 바이러스 확산
		while (!q.isEmpty()) {
			Point now = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dr[i];
				int ny = now.y + dc[i];
				if (checkIndex(nx, ny) && temp[nx][ny] == 0) {
					temp[nx][ny] = 2;
					area--;	//안전구역 범위 축소
					q.offer(new Point(nx, ny));
				}
			}
		}
		//최대 안전구역 갱신
		safeArea = safeArea < area ? area : safeArea;
	}

	//인덱스가 지도를 벗어나는지 확인하는 함수
	static boolean checkIndex(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < m) {
			return true;
		}
		return false;
	}

}
