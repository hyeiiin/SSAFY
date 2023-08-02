package sdf;

import java.io.*;
import java.io.IOException;
import java.util.*;

public class Main_11660_정준원 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N + 1][N + 1];
		int[][] d = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 1; j <= N; j++) {

				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

// 
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {

				d[i][j] = d[i][j - 1] + d[i - 1][j] - d[i - 1][j - 1] + arr[i][j]; // 여기서 이차원 배열 d 는 0,0 에서 해당 i,j 까지의
																					// 수들의 합이다.

			}
		}

		for (int i = 0; i < M; i++) {
			int x, y, x2, y2;
			st = new StringTokenizer(br.readLine());

			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());

			int v = d[x2][y2] - d[x2][y - 1] - d[x - 1][y2] + d[x - 1][y - 1];  //x,y 부터 x2,y2 까지의 합 을 구하는 것이므로
//			d[x2][y - 1] - d[x - 1][y2] 해당 두가지 값을 뺀다음 겹치는 부분을 더하면 된다.

			System.out.println(v);

		}

	}

}
