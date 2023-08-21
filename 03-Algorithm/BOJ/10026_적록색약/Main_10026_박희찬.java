import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main { // 10026
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N; // 맵 사이즈
	static char[][] lst;  // 맵
	static boolean[][] visited;  // 방문 처리
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Pos {
		int x = 0;
		int y = 0;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		
		lst = new char[N][N];
		
		// 구역 할당
		for (int row = 0; row < N; row++) {
			String str = br.readLine();
			for (int col = 0; col < N; col++) {
				lst[row][col] = str.charAt(col);
			}
		}
		
		sb = new StringBuilder();
		
		// 1. 적록색약이 아닌 사람
		visited = new boolean[N][N];
		int cnt = 0;
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c]) {
					bfs(r, c);  // Off
					cnt++;
				}
			}
		}
		
		sb.append(cnt).append(" ");
		
		// 2. 적록색약인 사람
		visited = new boolean[N][N];  // visited 초기화
		cnt = 0; // cnt 초기화
		
		// 적 == 록으로 변경
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (lst[r][c] == 'G') {
					lst[r][c] = 'R';
				}
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c]) {
					bfs(r, c);  // On
					cnt++;
				}
			}
		}
		
		sb.append(cnt);
		System.out.println(sb);
		
	}
	
	/**
	 * 적록색약과 아닌사람이 보는 구역을 찾기 위해 탐색하는 함수
	 * @param x : 시작 좌표
	 * @param y : 시작 좌표
	 */
	static void bfs(int x, int y) {
		Deque<Pos> qu = new ArrayDeque<>();
		
		qu.offer(new Pos(x, y));
		visited[x][y] = true;
		char ch = lst[x][y];  // 현재 보는 문자
		
		while (!qu.isEmpty()) {
			Pos p = qu.poll();
			
			// 4방향 탐색
			for (int k = 0; k < 4; k++) {
				int nx = p.x + dx[k];
				int ny = p.y + dy[k];
				
				// 같은 문자인 경우 같은 구역으로 처리
				if (isIn(nx, ny) && !visited[nx][ny] && lst[nx][ny] == ch) {
					visited[nx][ny] = true;
					qu.offer(new Pos(nx, ny));
				}
			}
		}  // while()
			
		
	}
	
	/**
	 * 다음 좌표가 맵을 벗어나는지 판단하는 함수
	 * @param nx : 다음 좌표
	 * @param ny : 다음 좌표
	 * @return : 벗어나는지 여부
	 */
	static boolean isIn(int nx, int ny) {
		if (0 <= nx && nx < N && 0 <= ny && ny < N) {
			return true;
		}
		
		return false;
	}
}
