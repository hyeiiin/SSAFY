package ssafy.Swea;

import java.io.*;
import java.util.*;

// 2805. 농작물 수확하기
public class _2805_Boj {

	private static int[][] arr;
	private static int middle;
	private static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine()); // t 테스트 케이스 개수

		for (int test = 1; test <= t; test++) {
			n = Integer.parseInt(br.readLine()); // n 농장의 크기
			arr = new int[n][n];
			String input;

			for (int i = 0; i < n; i++) {
				input = br.readLine();
				for (int j = 0; j < n; j++) {
					arr[i][j] = input.charAt(j) - 48;
				}
			}
			
			middle = (int) Math.round(n / 2.0);
			int sum = 0;
			// 중앙값까지만 반복.
			for (int row = 0; row < middle; row++) {

				// 중앙값일 경우 한 번만 더함.
				// 중앙값이 아닐 경우 대칭으로 더함.
				for (int col = (middle - 1) - row; col <= (middle - 1) + row; col++) {
					sum += arr[row][col];

					if (middle - 1 == row) continue;
					sum += arr[n - 1 - row][col];
				}
			}

			System.out.println("#" + test + " " + sum);
		}

	}

}
