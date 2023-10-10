package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeV9sKkcoDFAVH&
 * 
 * @author SSAFY
 *
 */
public class Solution_4013_박정인 {
	static int K; // 회전횟수
	static int[][] arr; // 자석 정보 저장
	static int[] dirArr;
	
	static final int RIGHT = 1; // 시계 방향 회전
	static final int LEFT = -1; // 반시계 방향 회전
	static final int N = 0; // N극
	static final int S = 1; // S극	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int total = 0;

			K = Integer.parseInt(br.readLine());

			arr = new int[4][8];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());

				// 자석 번호
				int idx = Integer.parseInt(st.nextToken()) - 1;
				// 회전방향
				int dir = Integer.parseInt(st.nextToken());
				
				dirArr = new int[4];
				dirArr[idx] = dir;	// 자석번호 idx의 회전방향 설정
				
				// 현재 idx자석 기준 좌우를 체크하며 회전방향 설정
				checkDir(idx, dir);

				// 설정된 회전방향 정보를 통해 회전
				for (int j = 0; j < 4; j++) {
					if (dirArr[j] == 0)	continue;
					rotate(j, dirArr[j]);
				}
			}

			int score = 1;
			// 1, 2, 4, 8 == 2진수 1, 10, 100, 1000
			// 1 << n 의 경우 2ⁿ
			// 0번 자석: 2의 0승, 1번 자석: 2¹, 2번 자석: 2², 3번 자석: 2³
			for (int i = 0; i < 4; i++) {
				if (arr[i][0] == S) {
					total += (score << i);
				}
			}

			sb.append("#").append(tc).append(" ").append(total).append("\n");
		}

		System.out.println(sb);

//		System.out.println(1 << 0);
	}

	// 회전방향 설정
	private static void checkDir(int idx, int dir) {
		// i번째 자석기준 왼쪽 체크
		for (int i = idx; i >= 1; i--) {
			// i자석의 6번 톱니, i-1자석의 2번 톱니 비교
			if (arr[i][6] != arr[i - 1][2]) {
				dirArr[i - 1] = -dirArr[i];
			} else {
				break;
			}					
		}
		
		// i번째 자석 기준 오른쪽 체크
		for (int i = idx; i < 3; i++) {
			// i자석의 6번 톱니, i + 1번 자석의 2번 톱니 비교
			if (arr[i][2] != arr[i + 1][6]) {
				dirArr[i + 1] = -dirArr[i];
			} else {
				break;
			}
		}
	}

	// 회전
	private static void rotate(int idx, int dir) { // 자석번호, 회전방향
		if (dir == RIGHT) { // 시계
			int tmp = arr[idx][7];
			for (int i = 7; i >= 1; i--) {
				arr[idx][i] = arr[idx][i - 1];
			}
			arr[idx][0] = tmp;
		} else if (dir == LEFT) { // 반시계
			int tmp = arr[idx][0];
			for (int i = 0; i < 7; i++) {
				arr[idx][i] = arr[idx][i + 1];
			}

			arr[idx][7] = tmp;
		}

	}
}
