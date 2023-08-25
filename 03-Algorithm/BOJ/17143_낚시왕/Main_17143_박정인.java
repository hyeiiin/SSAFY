package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/17143
 * 
 * @author SSAFY
 *
 */
public class Main_17143_박정인 {
	static class Shark {
		int x, y, speed, dir, size;

		public Shark(int x, int y, int speed, int dir, int size) {
			super();
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}

	static int R, C, M, result;
	static Shark[][] map;
	static int sy = -1; // 낚시왕 시작 위치

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static final int BLANK = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new Shark[R][C];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int speed = Integer.parseInt(st.nextToken());
			int dir = initDir(Integer.parseInt(st.nextToken()));
			int size = Integer.parseInt(st.nextToken());
			Shark s = new Shark(x, y, speed, dir, size);
			map[x][y] = s;
		}

		for (int col = 0; col < C; col++) {

			// 상어 잡기
			catchShark(col);

			// 상어 이동, 잡아먹음
			moveShark();
		}

		System.out.println(result);
	}

	private static void moveShark() {
		List<Shark> sharks = new ArrayList<>();

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != null) {
					sharks.add(map[i][j]);
					map[i][j] = null;
				}
			}
		}

		for (Shark s : sharks) {
			// 상어 이동, 방향 처리	
			int speed = s.speed;
			// 상하
			if (s.dir == 0 || s.dir == 2) {
				speed %= (R - 1) * 2;
			} else { // 좌우
				speed %= (C - 1) * 2;
			}

			for (int i = 0; i < speed; i++) {
				int nx = s.x + dx[s.dir];
				int ny = s.y + dy[s.dir];	

				if (!isRange(nx, ny)) {					
					nx = s.x - dx[s.dir];
					ny = s.y - dy[s.dir];
					s.dir= (s.dir + 2) % 4;					
				}
				
				s.x = nx;
				s.y = ny;
			}
		
			// 이동한 상어 처리
			if (map[s.x][s.y] == null) {
				map[s.x][s.y] = s;
			} else {
				if (map[s.x][s.y].size < s.size) {
					map[s.x][s.y] = s;
				}
			}
		}
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}

	private static void catchShark(int y) {
		for (int x = 0; x < R; x++) {
			if (map[x][y] != null) {
				result += map[x][y].size;
				map[x][y] = null;
				break;
			}
		}
	}

	private static int initDir(int dir) {
		if (dir == 1)
			return 0;
		else if (dir == 4)
			return 1;
		else // 2, 3
			return dir;
	}
}
