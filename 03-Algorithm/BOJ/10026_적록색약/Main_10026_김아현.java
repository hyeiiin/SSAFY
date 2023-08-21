package ssafy.boj;

import java.io.*;
import java.util.*;


// node클래스 생성
class Node {
	int r;
	int c;

	public Node(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}

public class _10026_Boj {
	static int n;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // map 사이즈

		// 입력
		map = new int[n][n]; // map 초기화
		visited = new boolean[n][n]; // 방문 배열 초기화
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			// R 0 G 1 B 2
			for (int j = 0; j < n; j++) {
				if (str.charAt(j) == 'R') {
					map[i][j] = 0;
				} else if (str.charAt(j) == 'G') {
					map[i][j] = 1;
				} else if (str.charAt(j) == 'B') {
					map[i][j] = 2;
				}
			}
		}

		// 적색녹색 영역, 적록색맹을 가진 사람이 보는 적색 녹색 영역, 파란색 영역 개수 
		int[] cnt = new int[3]; 
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 확인 안된 rgb값이면 check
				if (!visited[i][j]) {
					// 파란색 영역일 경우
					if(map[i][j] == 2) {
						move(i,j);
						cnt[2]++;
					}else {
					// 그 외 색일 경우
						move(i,j);
						cnt[0]++;
					}
				}
			}
		}

		// 적색, 녹색만 확인할라고 해당 부분만 visited false 처리
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					visited[i][j] = false;
				}else if(map[i][j] == 1) {
					visited[i][j] = false;
				}
			}
		}

		// 적록색맹인 경우 check
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					move(i, j);
					cnt[1]++;
				}
			}
		}
		
		System.out.println( (cnt[0]+cnt[2]) + " " + (cnt[1]+ cnt[2]));
	}

	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static void move(int row, int col) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(row, col));
		int now = map[row][col]; // 현재 컬러값

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			visited[node.r][node.c] = true;

			// 사방탐색
			for (int d = 0; d < 4; d++) {
				int nr = node.r + dr[d];
				int nc = node.c + dc[d];

				// 경계 안에 있고 방문하지 않은 컬러값이라면
				if (inArea(nr, nc) && map[nr][nc] == now && !visited[nr][nc]) {
					queue.offer(new Node(nr, nc));
					visited[nr][nc] = true;
				}

			}
		}
	}

	// 경계값 확인
	static boolean inArea(int row, int col) {
		return row >= 0 && row < n && col >= 0 && col < n;
	}
}
