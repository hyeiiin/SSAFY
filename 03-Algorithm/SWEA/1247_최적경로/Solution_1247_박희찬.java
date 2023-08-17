import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution { // 1247
	static StringBuilder sb;
	static StringTokenizer st;
	static int TC;
	static int N; // 고객의 수
	static int[] home, company;  // 집, 회사 좌표
	static int[][] customer;  // 고객 좌표
	static int[] numbers;  // 순열: 선택한 숫자 배열
	static boolean[] visited; // 순열: 방문 배열
	static int res; // 최종 결괏값

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc < TC + 1; tc++) {
			res = Integer.MAX_VALUE;  // 매 TestCase마다 최댓값으로 초기화.
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			customer = new int[N][2];
			home = new int[2];
			company = new int[2];

			// 집 좌표 초기화
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 2; i++) {
				home[i] = Integer.parseInt(st.nextToken());
			}

			// 회사 좌표 초기화
			for (int i = 0; i < 2; i++) {
				company[i] = Integer.parseInt(st.nextToken());
			}

			// 고객 좌표 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < 2; j++) {
					customer[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 순열용 배열
			numbers = new int[N];
			visited = new boolean[N];

			perm(0);
			
			sb = new StringBuilder();
			sb.append("#").append(tc).append(" ").append(res);
			System.out.println(sb);
			
		} // TC
	} // Main

	/**
	 * 순열을 통해 고객의 방문 순서를 정하는 함수
	 * @param cnt : 현재 선택된 숫자의 개수
	 */
	private static void perm(int cnt) {
		if (cnt == N) {
			distance();
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				numbers[cnt] = i;
				perm(cnt + 1);
				visited[i] = false;
			}
		}
	}
	
	/**
	 * perm()에서 넘어온 순서대로 회사 -> 고객 -> 집까지의 거리를 계산하는 함수
	 */
	private static void distance() {
		int sum = 0;
		
		// 출발(=현재) 위치 = 회사로 고정
		int x = company[0];
		int y = company[1];
		int nx, ny;  // 다음 위치
		
		for (int idx = 0; idx < N; idx++) {
			// N명의 고객을 차례대로 방문
			nx = customer[numbers[idx]][0];
			ny = customer[numbers[idx]][1];
			sum += Math.abs(x - nx) + Math.abs(y - ny);
			
			// 방문하여 거리 계산 후, 다음 고객좌표로 현재 좌표를 이동.
			x = nx;
			y = ny;
		}
		
		sum += Math.abs(x - home[0]) + Math.abs(y - home[1]);
		
		// 총 이동거리가 가장 짧은 경로로 갱신.
		res = Math.min(res, sum);
	}
}
