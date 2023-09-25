import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14502_이세은 {

	static Queue<int[]> q = new LinkedList<>();
	static ArrayList<int[]> arr = new ArrayList<>(); //바이러스 좌표 담을 리스트
	static int[][] map;
	static boolean[][] visited;
	static int n, m, max = Integer.MIN_VALUE;
	static int[] moveR = { -1, 1, 0, 0 };
	static int[] moveC = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					arr.add(new int[] {i, j});
					visited[i][j] = true; // 현재 바이러스 칸 방문처리
				}
			}
		}
		wall(0);
		System.out.println(max);

	}

	public static boolean isValid(int r, int c) {
		if (r < 0 || c < 0 || r >= n || c >= m || map[r][c] == 1)
			return false;
		return true;
	}

	public static void isMax() {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j] && map[i][j] == 0) { // 방문처리 안되어있고 빈칸인 경우는 안전 구역
					count++;
				}
			}
		}
		max = Math.max(max, count);
	}

	// 벽 놓기
	public static void wall(int cnt) {
		
		if (cnt == 3) { // 벽 3개 다 세웠다면 bfs로 바이러스 전파
			visited = new boolean[n][m]; //방문 배열 초기화
			bfs();
			isMax(); // 다 돌고 난 후 안전구역 최대 찾기
			return;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) { // 빈칸일 경우 벽 세우기
					map[i][j] = 1; // 벽 세우기
					wall(cnt+1);
					map[i][j] = 0; // 벽 없애기
				}
			}
		}
	}

	public static void bfs() {
		
		Queue<int[]> q = new LinkedList<>();
		for(int[] virus : arr) {
			q.offer(new int[] {virus[0], virus[1]});
		}
		

		// 바이러스 전파
		while (!q.isEmpty()) {
			int[] val = q.poll();
			for (int k = 0; k < 4; k++) {
				int newR = val[0] + moveR[k];
				int newC = val[1] + moveC[k];

				if (isValid(newR, newC) && !visited[newR][newC]) {
					if (map[newR][newC] == 0) { // 빈칸이라면 바이러스 전파
						visited[newR][newC] = true;
						q.offer(new int[] { newR, newC});
					}
				}
			}
		}
	}
}
