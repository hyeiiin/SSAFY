
import java.awt.geom.Area;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution_5644_이도훈 {

	static int M;
	static int A;

	static int[][] move;
	static Area[][] map;
	static int[][] dirs = {{0, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, 0}};

	public static void main(String args[]) throws Exception {


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {

			st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			// 이동 경로 초기화
			move = new int[M][2];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				move[i][0] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				move[i][1] = Integer.parseInt(st.nextToken());
			}

			// 충전 영역 초기화
			map = new Area[10][10];

			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				Charger charger = new Charger(Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

				initArea(charger);
			}


			// 사람 초기화
			Person a = new Person(0, 0, map[0][0] != null ? map[0][0].chargers.get(0).power : 0);
			Person b = new Person(9, 9, map[9][9] != null ? map[9][9].chargers.get(0).power : 0);

			// 이동
			for (int i = 0; i < M; i++) {

				// 이동 하기
				a.move(dirs[move[i][0]]);
				b.move(dirs[move[i][1]]);


				// 충전 하기

				// 1. 둘이 같은 충전기인지
				Charger aCharger = map[a.y][a.x] == null ? null : map[a.y][a.x].chargers.get(0);
				Charger bCharger = map[b.y][b.x] == null ? null : map[b.y][b.x].chargers.get(0);

				// 1. 둘이 같은 충전기인 경우
				if (bCharger != null & aCharger == bCharger) {

					int half = aCharger.power / 2;

					// 둘다 여분의 충전이 가능한지 확인
					if (map[a.y][a.x].chargers.size() > 1 && map[b.y][b.x].chargers.size() > 1) {

						// 두 충전기 중 큰 파워를 가진 충전기 선택
						int other = Math.max(map[a.y][a.x].chargers.get(1).power,
							map[b.y][b.x].chargers.get(
								1).power);

						// 절반 충전이 나은지 다른 충전기를 선택하는 것이 좋은지 선택
						if (aCharger.power >= map[a.y][a.x].chargers.get(1).power + bCharger.power ||
						bCharger.power >= map[b.y][b.x].chargers.get(1).power + aCharger.power) {
							a.power += half;
							b.power += half;
						} else {
							if (map[a.y][a.x].chargers.get(1).power > map[b.y][b.x].chargers.get(
								1).power) {

								a.power += other;
								b.power += bCharger.power;
							} else {
								b.power += other;
								a.power += aCharger.power;
							}
						}
						// a만 여분 충전기가 있다면
					} else if (map[a.y][a.x].chargers.size() > 1) {
						if (aCharger.power >= map[a.y][a.x].chargers.get(1).power + bCharger.power) {
							a.power += half;
							b.power += half;
						} else {
							a.power += map[a.y][a.x].chargers.get(1).power;
							b.power += bCharger.power;
						}
					} else if (map[b.y][b.x].chargers.size() > 1) {
						if (aCharger.power >= map[b.y][b.x].chargers.get(1).power + aCharger.power) {
							a.power += half;
							b.power += half;
						} else {
							b.power += map[b.y][b.x].chargers.get(1).power;
							a.power += aCharger.power;
						}
						// 둘다 여분이 없다면 나눠서 충전
					} else {
						a.power += half;
						b.power += half;
					}
				} else {
					// 둘이 다른 충전기인 경우
					if(aCharger != null) a.power += aCharger.power;
					if(bCharger != null) b.power += bCharger.power;
				}
			}
			sb.append("#").append(test_case).append(" ").append(a.power + b.power).append("\n");
		}
		System.out.println(sb);

	}


	static void initArea(Charger charger) {

		int top = charger.y - charger.range;

		// 충전기 윗영역
		int cnt = 0;
		while (top <= charger.y) {
			for (int i = charger.x - cnt; i <= charger.x + cnt; i++) {
				if(isOutside(i,top)) continue;
				if (map[top][i] == null) map[top][i] = new Area();
				map[top][i].chargers.add(charger);
				map[top][i].chargers.sort((o1, o2) -> o2.power - o1.power);
			}

			top++;
			cnt++;
		}
		top = charger.y + charger.range;
		cnt = 0;
		while (top > charger.y) {
			for (int i = charger.x - cnt; i <= charger.x + cnt; i++) {
				if(isOutside(i,top)) continue;
				if (map[top][i] == null) map[top][i] = new Area();
				map[top][i].chargers.add(charger);
				map[top][i].chargers.sort((o1, o2) -> o2.power - o1.power);
			}

			top--;
			cnt++;
		}
	}

	static private boolean isOutside(int x, int y) {
		if (x < 0 || y < 0 || x >= 10 || y >= 10) {
			return true;
		}
		return false;
	}

	static class Area {
		ArrayList<Charger> chargers = new ArrayList<>();
	}

	static class Charger {

		int x;
		int y;
		int range;
		int power;

		public Charger(int x, int y, int range, int power) {
			this.x = x;
			this.y = y;
			this.range = range;
			this.power = power;
		}
	}

	static class Person {
		int x;
		int y;
		int power;

		public Person(int x, int y, int power) {
			this.x = x;
			this.y = y;
			this.power = power;
		}

		public void move(int[] dir) {
			this.x += dir[0];
			this.y += dir[1];
		}
	}
}
