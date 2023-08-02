package ssafy.Boj;

import java.util.*;

public class _11659_Boj {

	private static int[] sumNumbers; // 누적합 넣는 배열
	private static int[] numbers;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 수의 개수
		int m = sc.nextInt(); // 합을 구해야하는 횟수
		sumNumbers = new int[n];
		numbers = new int[n]; // n개의 수

		numbers[0] = sc.nextInt();
		sumNumbers[0] = numbers[0];
		for (int a = 1; a < n; a++) {
			numbers[a] = sc.nextInt();
			sumNumbers[a] = numbers[a] + sumNumbers[a - 1];
		}

		for (int a = 0; a < m; a++) {
			int i = sc.nextInt(); // 합을 구해야하는 구간 i,j
			int j = sc.nextInt();

			if (i == 1) {
				System.out.println(sumNumbers[j - 1]);
			} else {
				System.out.println(sumNumbers[j - 1] - sumNumbers[i - 2]);
			}
		}

		sc.close();

	}

}
