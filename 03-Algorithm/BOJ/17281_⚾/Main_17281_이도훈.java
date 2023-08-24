import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main_17281_이도훈 {

	static boolean[] visited;
	static int[] orders;
	static int[][] points;
	static int N;

	static int answer = Integer.MIN_VALUE;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 이닝 수
		N = Integer.parseInt(br.readLine());

		points = new int[9][N];
		visited = new boolean[9];
		orders = new int[9];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				points[j][i] = Integer.parseInt(st.nextToken());
			}
		}

		// 1번타자 3번 타순으로
		orders[3] = 0;
		// 1번 타자 방문
		visited[0] = true;
		dfs(0);

		System.out.println(answer);

	}


	static void dfs(int depth) {
		if (depth == 9) {
			// 야구 시작

			int sum = 0;

			int idx = 0;
			int player = 0;

			// 이닝
			for (int i = 0; i < N; i++) {
				// 베이스 초기화
				boolean[] base = new boolean[4];

				// 첫 선수 지정
				player = orders[idx];

				int outCnt = 0;

				// 이닝 시작
				while (true) {
					// 타석 입장
					player = orders[idx];
					base[0] = true;

					// 아웃될 경우
					if (points[player][i] == 0) {
						outCnt++;
					} else { // 진루
						for (int j = 3; j >= 0; j--) {
							if (base[j]) {
								if (j + points[player][i] >= 4) {
									sum += 1;
								} else {
									base[j + points[player][i]] = true;
								}
								base[j] = false;
							}
						}
					}

					idx = (idx + 1) % 9;
					if (outCnt == 3) {
						break;
					}
				}
			}

			if (answer < sum) {
				answer = sum;
			}
			return;
		}

		if (depth == 3) {
			dfs(depth + 1);
			return;
		}
		// 타순 지정
		for (int i = 0; i < 9; i++) {
			if (visited[i]) {
				continue;
			}

			visited[i] = true;
			orders[depth] = i;
			dfs(depth + 1);
			visited[i] = false;
		}

	}


}
