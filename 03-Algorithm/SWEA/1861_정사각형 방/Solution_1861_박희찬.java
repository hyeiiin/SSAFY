import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution { // SWEA_1861
	static int TC;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N;
	static int[][] lst;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc < TC + 1; tc++) { // TC
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			lst = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					lst[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int temp; // bfs() 결과 받을 임시 변수
			int res = 0; // 최대 이동 방 개수
			int room = Integer.MAX_VALUE; // 방 번호
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					temp = bfs(i, j);

					// 1. 최대 이동 방 갱신
					if (res < temp) {
						res = temp;
						room = lst[i][j];
					} else if (res == temp && room > lst[i][j]) {  // 최대 이동 개수는 같지만, 방 번호가 더 작은 경우
						room = lst[i][j];
					}
					//
				}
			}

			sb.append("#" + tc + " " + room + " " + res);
			System.out.println(sb.toString());
		} // TC

	}

	public static int bfs(int x, int y) {
		LinkedList<Point> qu = new LinkedList<>();

		qu.add(new Point(x, y));
		int cnt = 1;

		while (!qu.isEmpty()) {
			Point p = qu.removeFirst();

			for (int k = 0; k < 4; k++) {
				int nx = p.x + dx[k];
				int ny = p.y + dy[k];

				// 범위를 벗어나지 않으면서 + 다음 방 번호가 1만큼 많은 경우.
				if (isIn(nx, ny) && (lst[nx][ny] - lst[p.x][p.y] == 1)) {
					cnt++;
					qu.addLast(new Point(nx, ny));
				}
			}
		}

		return cnt;
	}

	public static boolean isIn(int nx, int ny) {
		return (0 <= nx && nx < N && 0 <= ny && ny < N);
	}

}
