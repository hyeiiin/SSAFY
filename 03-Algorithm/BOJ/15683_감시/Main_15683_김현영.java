import java.io.*;
import java.util.*;

public class Main_15683_김현영 {

	static int n, m, size;
	static int[][] room, temp; // 사무실 정보와 cctv감시표시가 된 room을 복사한 배열
	static Node[] cctv5;

	// 우 하 좌 상
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	static void findCCTV(int r, int c, int nowSize, int[][] map) {
		// 사무실에서 cctv를 찾으러 탐색
		// cctv를 만나면 번호에 맞추어 사방탐색 -> 한 방향 탐색이 끝나면 다음 cctv 찾으러 가기
		for (int i = r; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) {
					for (int k = 0; k < 4; k++) {
                        // 백트레킹을 위한 배열 복사
						temp = new int[n][m];
						for (int a = 0; a < n; a++) {
							for (int b = 0; b < m; b++) {
								temp[a][b] = map[a][b];
							}
						}

                        int nr = i + dr[k];
						int nc = j + dc[k];
						
						// 감시 영역 표시
						int cnt = 0;
						cnt = onCCTV(nr, nc, k, cnt);

						// 다음 cctv 탐색
						temp[i][j] = -1;	//현재 cctv 탐색이 끝났으므로 -1로 표시
						if (j == m - 1)
							findCCTV(i + 1, 0, nowSize - cnt, temp);
						else
							findCCTV(i, 0, nowSize - cnt, temp);
					}
				} 
				else if (map[i][j] == 2) {
					for (int k = 0; k < 2; k++) {
						// 백트레킹을 위한 배열 복사
						temp = new int[n][m];
						for (int a = 0; a < n; a++) {
							for (int b = 0; b < m; b++) {
								temp[a][b] = map[a][b];
							}
						}

						// 감시 영역 표시
						int cnt = 0;

						int nr = i + dr[k];
						int nc = j + dc[k];
						cnt = onCCTV(nr, nc, k, cnt);

						int nr2 = i + dr[k + 2];
						int nc2 = j + dc[k + 2];
						cnt = onCCTV(nr2, nc2, k + 2, cnt);

						// 다음 cctv 탐색
						temp[i][j] = -1;	//현재 cctv 탐색이 끝났으므로 -1로 표시
						if (j == m - 1)
							findCCTV(i + 1, 0, nowSize - cnt, temp);
						else
							findCCTV(i, 0, nowSize - cnt, temp);
					}
				} else if (map[i][j] == 3) {
					for (int k = 0; k < 4; k++) {
						// 백트레킹을 위한 배열 복사
						temp = new int[n][m];
						for (int a = 0; a < n; a++) {
							for (int b = 0; b < m; b++) {
								temp[a][b] = map[a][b];
							}
						}

						// 감시 영역 표시
						int cnt = 0;

						int nr = i + dr[k];
						int nc = j + dc[k];
						cnt = onCCTV(nr, nc, k, cnt);

						int nr2 = i + dr[(k + 1) % 4];
						int nc2 = j + dc[(k + 1) % 4];
						cnt = onCCTV(nr2, nc2, (k + 1) % 4, cnt);

						// 다음 cctv 탐색
						temp[i][j] = -1;	//현재 cctv 탐색이 끝났으므로 -1로 표시
						if (j == m - 1)
							findCCTV(i + 1, 0, nowSize - cnt, temp);
						else
							findCCTV(i, 0, nowSize - cnt, temp);

					}
				} 
				else if (map[i][j] == 4) {
					for (int k = 0; k < 4; k++) {
						// 백트레킹을 위한 배열 복사
						temp = new int[n][m];
						for (int a = 0; a < n; a++) {
							for (int b = 0; b < m; b++) {
								temp[a][b] = map[a][b];
							}
						}

						// 감시 영역 표시
						int cnt = 0;
						for (int p = 0; p < 4; p++) {
							if (p == k)
								continue;
							int nr = i + dr[p];
							int nc = j + dc[p];
							cnt = onCCTV(nr, nc, p, cnt);
						} 

						// 다음 cctv 탐색
						temp[i][j] = -1;	//현재 cctv 탐색이 끝났으므로 -1로 표시
						if (j == m - 1)
							findCCTV(i + 1, 0, nowSize - cnt, temp);
						else
							findCCTV(i, 0, nowSize - cnt, temp);
					}
				}
			}
		}

		// 사각지대 최소 크기 갱신
		size = size > nowSize ? nowSize : size;

	}

	//cctv의 감시 영역 표시하는 함수
	//사무실 크기 내에서 좌표를 이동하며 감시영역을 표시하고 벽을 만나면 감시를 종료한다.
	static int onCCTV(int r, int c, int i, int cnt) {
		while (checkIndex(r, c)) {
			if (temp[r][c] == 0) {
				temp[r][c] = -1; // 감시 영역으로 변경
				cnt++; // 감시된 영역 추가
			} else if (temp[r][c] == 6)
				break; // 벽 만나면 감시 종료

			r += dr[i];
			c += dc[i];
		}
		return cnt;
	}

	//5번cctv의 감시 영역 표시
	static void onCCTV5() {
		for (int i = 0; i < cctv5.length; i++) {
			int r = cctv5[i].x;
			int c = cctv5[i].y;
			// 사방탐색
			for (int j = 0; j < 4; j++) {
				int nr = r + dr[j];
				int nc = c + dc[j];
				// 감시 영역 표시
				while (checkIndex(nr, nc)) {
					if (room[nr][nc] == 0) {
						room[nr][nc] = -1; // 감시 영역으로 변경
						size--; // 감시된 영역 추가
					} 
					// 벽 만나면 감시 종료
					else if (room[nr][nc] == 6)
						break;
					nr += dr[j];
					nc += dc[j];
				}

			}
		}
	}

	static boolean checkIndex(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < m)
			return true;
		return false;
	}

	// 좌표 저장
	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
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

		cctv5 = new Node[8]; // 5번cctv의 개수를 알지 못하므로 최대 개수은 8로 지정
		int size5 = 0;
		// 사무실 정보 입력
		room = new int[n][m];
		size = n * m;	//사무실 크기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				//cctv와 벽의 개수 사무실 크기에서 제외
				if (room[i][j] != 0)
					size--;
				//5번cctv의 좌표 저장
				if (room[i][j] == 5) {
					cctv5[size5++] = new Node(i, j);
				}

			}
		}
		// 5번cctv는 방향을 돌려도 같으므로 먼저 처리
		cctv5 = Arrays.copyOf(cctv5, size5); // cctv5번을 개수에 맞게 배열 줄이기
		onCCTV5();

		//1번~4번 cctv 처리
		findCCTV(0, 0, size, room); 

		System.out.println(size);
	}

}
