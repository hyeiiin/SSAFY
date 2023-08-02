package ssafy.Boj;

import java.io.*;
import java.util.*;

public class _11660_Boj {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 표의 크기
		int m = Integer.parseInt(st.nextToken()); // 합을 구해야하는 횟수
		int[][] map = new int[n][n]; // 행 기준 누적합을 넣을 배열
		// 누적합 배열 생성
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			for (int j = 1; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) + map[i][j - 1];
			}
		}

		// 누적합 배열 출력
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println("");
		}

		// 구간 입력
		for (int t = 0; t < m; t++) {
			int sum = 0;
			st = new StringTokenizer(br.readLine());
			int[][] xy = new int[2][2];
			for (int xx = 0; xx < 2; xx++) {
				for (int yy = 0; yy < 2; yy++) {
					xy[xx][yy] = Integer.parseInt(st.nextToken()) - 1;
				}
			}

			int x1 = xy[0][0];
			int y1 = xy[0][1];
			int x2 = xy[1][0];
			int y2 = xy[1][1];

			if (y1 == 0) {
				for (int i = x1; i <= x1 + (x2 - x1); i++) {
//					System.out.println("i : " + i);
					sum += map[i][y2];
				}
			} else {
				// 연산 수행
				for (int i = 0; i <= (x2 - x1); i++) {
					sum += (map[xy[i][0]][y2] - map[xy[i][0]][y1 - 1]);
				}

			}

			System.out.println(sum);

		}

	}

}
