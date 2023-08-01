package sdf;

import java.util.*;

public class Solution_1210_정준원 {
	public static int arr[][] = new int[100][100];
	public static boolean find = false;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {

			int tc_cnt = sc.nextInt();
			int result = 0;

			find = false;
			int c = 0;
			// 사다리 배열 생성
			for (int row = 0; row < 100; row++) {
				for (int col = 0; col < 100; col++) {
					arr[row][col] = sc.nextInt();
					if (arr[row][col] == 2) {
						c = col;
					}

				}

				result = findX(99, c);

			}

			System.out.println("#" + tc_cnt + " " + result);
		}

	}

	public static int findX(int i, int j) {

		boolean visited[][] = new boolean[100][100];

		while (true) {

			if (i == 0) {
				return j;
			}

			visited[i][j] = true; // 지나온 길 표시

			if (j - 1 >= 0 && arr[i][j - 1] == 1 && visited[i][j - 1] == false) // 왼쪽으로 이동
			{

				j--;
			}

			else if (j + 1 < 100 && arr[i][j + 1] == 1 && visited[i][j + 1] == false) // 오른쪽으로 이동
			{
				j++;
			}

			else { // 아래로 이동

				i--;
			}

		}

	}

}
