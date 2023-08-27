package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/17281
 * 
 * @author SSAFY
 *
 */
public class Main_17281_박정인 {
	static int N, max = Integer.MIN_VALUE, score, orders[];
	static int[][] status;
	static boolean[] visited;

	static final int SINGLE = 1;
	static final int DOUBLE = 2;
	static final int TRIPLE = 3;
	static final int HOMERUN = 4;
	static final int OUT = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		status = new int[N][9];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				status[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		orders = new int[9]; // n번째 타순에 해당하는 선수 번호
		visited = new boolean[9]; // 각 번호의 순서가 정해졌는지 여부
		visited[0] = true; // 1번 선수는 이미 순서가 정해졌다.
		orders[3] = 0; // 4번째 선수는 1번 선수로 확정
		dfs(0);

		System.out.println(max);
	}

	private static void dfs(int cnt) { // 현재 이닝의 선수당 상태
		if (cnt == 9) {
			// 점수계산, 갱신
			int score = getScore();
			if (max < score) {
				max = score;
			}
			return;
		}

		if (cnt == 3) { // 4번째 타순의 타자
			dfs(cnt + 1);
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (visited[i])
				continue;

			visited[i] = true;
			orders[cnt] = i;
			dfs(cnt + 1);
			visited[i] = false;
		}

	}

	private static int getScore() {
		int score = 0, player = 0, index = 0, outCnt = 0;		

		for (int i = 0; i < N; i++) { // 이닝수
			boolean[] base = new boolean[4];	// 타석, 1, 2, 3루 주자 유무
			outCnt = 0;			
			
			while (true) {
				// 타석 입장
				player = orders[index];
				base[0] = true;
				
				if (status[i][player] == OUT) {	// 아웃
					outCnt++;
				} else {	// 진루
					for (int j = 3; j >= 0; j--) {
						if (base[j]) {
							// 홈으로 들어오는 경우
							if (j + status[i][player] >= 4) {
								score++;
							} else {	// 홈으로 들어오지 못하는 경우
								base[j + status[i][player]] = true;
							}
							
							// 진루했기에 원래 위치 비어있는 처리
							base[j] = false;
						}
					}
				}
				
				index = (index + 1) % 9;	// 다음 타순
				if (outCnt == 3) {
					break;
				}
			}												
		}

		return score;
	}
}
