import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static StringBuilder sb;
	static StringTokenizer st;
	static int N, M; // 연구실 크기
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static List<Pos> virus; // 바이러스 좌표
	static int res = Integer.MIN_VALUE; // 결괏값

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 연구실, 바이러스 좌표 저장 공간 초기화
		int[][] lst = new int[N][M];
		virus = new ArrayList<>();

		// 연구실 상태 입력받으면서, 바이러스 좌표를 따로 저장함.
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				lst[r][c] = Integer.parseInt(st.nextToken());
				if (lst[r][c] == 2) {
					virus.add(new Pos(r, c));
				}
			}
		}

		int w = 0;
		wall(lst, w);

		sb = new StringBuilder();
		sb.append(res);
		System.out.println(sb);

	} // Main

	/**
	 * 연구실에 무작위로 벽을 3개 설치해보는 함수
	 * 
	 * @param lst : 연구실
	 * @param w   : 벽
	 */
	static void wall(int[][] lst, int w) {
		// 2. 벽을 3개 세웠다면,
		if (w == 3) {
			// 3. 현재 맵을 복사 한 후 bfs()에 넘김
			int[][] temp = new int[N][M];
			for (int i = 0; i < N; i++) {
				System.arraycopy(lst[i], 0, temp[i], 0, M);
			}
			bfs(temp);
			return;
		}

		// 1. 무작위로 벽을 세워 봄
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (lst[r][c] == 0) {
					lst[r][c] = 3;
					wall(lst, w + 1);
					lst[r][c] = 0;
				}
			}
		}
	}

	/**
	 * 바이러스를 가능한 모두 퍼뜨린 후 안전 지대를 산출하는 함수
	 * 
	 * @param temp : 복사한 맵
	 */
	static void bfs(int[][] temp) {
		Deque<Pos> qu = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];

		// qu에 퍼뜨릴 바이러스 좌표를 삽입.
		for (int i = 0; i < virus.size(); i++) {
			qu.offer(new Pos(virus.get(i).x, virus.get(i).y));
			visited[virus.get(i).x][virus.get(i).y] = true;
		}

		while (!qu.isEmpty()) {
			Pos p = qu.poll();

			for (int k = 0; k < 4; k++) {
				int nx = p.x + dx[k];
				int ny = p.y + dy[k];

				if (isIn(nx, ny) && !visited[nx][ny] && temp[nx][ny] == 0) {
					// 바이러스 확산
					temp[nx][ny] = 2;
					visited[nx][ny] = true;
					qu.offer(new Pos(nx, ny));
				}

			}
		} // while

		// 안전지대 개수
		int cnt = 0;
		for (int[] element : temp) {
			for (int e : element) {
				if (e == 0) {
					cnt++;
				}
			}
		}
		
		// 최댓값 갱신
		res = Math.max(res, cnt);
	}

	static boolean isIn(int nx, int ny) {
		if (0 <= nx && nx < N && 0 <= ny && ny < M) {
			return true;
		}
		return false;
	}
}
