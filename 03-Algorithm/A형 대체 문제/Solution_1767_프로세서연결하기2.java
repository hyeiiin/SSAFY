package swea;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1767_프로세서연결하기2 {
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine()); 
			map = new int[N][N];
			ArrayList<Core> coreList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1 && i != N && j != N && i != 0 && j != 0) { // Core인 경우
						coreList.add(new Core(i, j));
					}
				}
			}

			maxCoreCntAns = Integer.MIN_VALUE;
			mincoreValueAns = Integer.MAX_VALUE;
			chooseCore(coreList, 0, 0, 0);
			System.out.println("#"+tc+" "+mincoreValueAns);
		}
	}

	private static int maxCoreCntAns;
	private static int mincoreValueAns;

	private static void chooseCore(ArrayList<Core> coreList, int idx, int core, int value) {

		if (idx == coreList.size()) { // 다 탐색해봤으면
			if(maxCoreCntAns<core) {
				maxCoreCntAns = core;
				mincoreValueAns = value;
			}else if(maxCoreCntAns == core) {
				mincoreValueAns = Math.min(mincoreValueAns, value);
			}
			return;
		}

		Core c = coreList.get(idx);
		for (int dir = 0; dir < 4; dir++) {
			int cableLen = setCable(c, dir);
			if (cableLen != -1) {
				chooseCore(coreList, idx + 1, core + 1, value + cableLen);
				int x = c.r;
				int y = c.c;
				for (int i = 0; i < cableLen; i++) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					map[nx][ny] = 0;
					x = nx;
					y = ny;
				}
			}
		}
		chooseCore(coreList, idx + 1, core, value);
	}

	private static int setCable(Core core, int dir) {
		boolean canSetCable = false;
		int coreLen = 0;
		int x = core.r;
		int y = core.c;
		while (true) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (nx < 0 || ny < 0 || nx >= map.length || ny >= map.length) { // 설치 가능
				canSetCable = true;
				break;
			}
			if (map[nx][ny] != 0) {
				break;
			}
			x = nx;
			y = ny;
		}
		x = core.r;
		y = core.c;
		if (canSetCable) { // 해당 방향은 전선 설치 가능
			while (true) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if (nx < 0 || ny < 0 || nx >= map.length || ny >= map.length) { // 설치 가능
					break;
				}
				coreLen++;
				map[nx][ny] = 2;
				x = nx;
				y = ny;
			}
			return coreLen;
		} else {
			return -1;
		}
	}

	private static class Core {
		int r, c;

		public Core(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
