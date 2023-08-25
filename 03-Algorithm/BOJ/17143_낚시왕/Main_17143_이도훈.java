import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import sun.security.provider.SHA;

class Main_17143_이도훈 {

	static int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

	static int R;
	static int C;

	static ArrayList<Shark>[][] map;
	static ArrayList<Shark> list;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		// 상어 정보 수
		int M = Integer.parseInt(st.nextToken());

		map = new ArrayList[R][C];
		list = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		// 상어 초기화
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());

			Shark shark = new Shark(c, r, z, s, d);
			map[r][c].add(shark);
			list.add(shark);
		}

		int fishingKing = 0;
		int size = 0;
		// 낚시왕이 C를 넘어가면 끝
		while (fishingKing < C) {

			// 상어 낚시
			for (int i = 0; i < R; i++) {
				if (!map[i][fishingKing].isEmpty()) {
					size += map[i][fishingKing].get(0).size;
					Shark remove = map[i][fishingKing].remove(0);
					list.remove(remove);
					break;
				}
			}

			//  상어 이동
			for (Shark shark : list) {
				shark.move();
			}

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(map[i][j].isEmpty()) continue;
					if (map[i][j].size() > 1) {
						map[i][j].sort(Comparator.comparingInt(s -> s.size));
						while (map[i][j].size() != 1) {
							Shark remove = map[i][j].remove(0);
							list.remove(remove);
						}
					}
				}
			}

			fishingKing++;
		}
		System.out.println(size);
	}

	static class Shark {

		int x;
		int y;
		int size;
		int speed;
		int dir;

		public Shark(int x, int y, int size, int speed, int dir) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.speed = speed;
			this.dir = dir;
		}

		public void move() {
			// 이동한 거리
			map[y][x].remove(this);

			for (int i = 0; i < speed; i++) {
				int mx = x + dirs[dir][0];
				int my = y + dirs[dir][1];

				if (!isMovable(mx, my)) {
					changeDir();
					i--;
				} else {
					x = mx;
					y = my;
				}
			}

			map[y][x].add(this);
		}

		private void changeDir() {
			if (dir == 0) {
				dir = 1;
			} else if (dir == 1) {
				dir = 0;
			} else if (dir == 2) {
				dir = 3;
			} else {
				dir = 2;
			}
		}

		private boolean isMovable(int x, int y) {
			return x >= 0 && y >= 0 && x < C && y < R;
		}
	}


}
