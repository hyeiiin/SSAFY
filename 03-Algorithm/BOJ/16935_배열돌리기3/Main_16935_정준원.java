package sdf;

import java.io.*;

import java.util.*;

public class Main_16935_정준원 {

	static StringBuilder sb = new StringBuilder();
	static int N, M, R;
	static int[][] arr;

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}

		int[] oper = new int[R];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < R; i++) {
			oper[i] = Integer.parseInt(st.nextToken());

			if (oper[i] == 1)
				rotate1();

			if (oper[i] == 2)
				rotate2();

			if (oper[i] == 3) {
				rotate3();
			}

			if (oper[i] == 4)
				rotate4();

			if (oper[i] == 5)
				rotate5(arr);

			if (oper[i] == 6)
				rotate6(arr);
		}

//		rotate1();
//		rotate2();
//		 rotate3();
//		rotate4();
//		rotate5(arr);
//		rotate6(arr);

		// 5번 연산이 시계

		// 6번 반시계

		N = arr.length;
		M = arr[0].length;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				System.out.print((arr[i][j]) + " ");
			System.out.println();
		}

	}

	static void rotate1() {
		N = arr.length;
		M = arr[0].length;

		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M; j++) {
				int temp;

				temp = arr[i][j];
				arr[i][j] = arr[N - i - 1][j];
				arr[N - i - 1][j] = temp;

			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++)
//				System.out.print((arr[i][j]) + " ");
//			System.out.println();
//		}

	}

	static void rotate2() {
		N = arr.length;
		M = arr[0].length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				int temp;

				temp = arr[i][j];
				arr[i][j] = arr[i][M - j - 1];
				arr[i][M - j - 1] = temp;

			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++)
//				System.out.print((arr[i][j]) + " ");
//			System.out.println();
//		}

	}

	static void rotate3() {
		N = arr.length;
		M = arr[0].length;
		int[][] arr2 = new int[M][N];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				arr2[i][j] = arr[N - j - 1][i];
			}
		}
		arr = new int[M][N];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = arr2[i][j];
			}
		}

	}

	static void rotate4() {
		N = arr.length;
		M = arr[0].length;
		int[][] arr2 = new int[M][N];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				arr2[i][j] = arr[j][M - 1 - i];
			}
		}
		arr = new int[M][N];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = arr2[i][j];
			}
		}

	}

	static void rotate5(int[][] tmparr) {
		int n = tmparr.length;
		int m = tmparr[0].length;

		int[][] temp = new int[n / 2][m / 2];

//		System.out.println("in");

		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				temp[i][j] = tmparr[i][j];
			}
		}
//
//		for (int i = 0; i < n / 2; i++) {
//			System.out.println(Arrays.toString(temp[i]));
//		}
//
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				tmparr[i][j] = tmparr[i + (n / 2)][j];
//				System.out.println(Arrays.toString(tmparr[i]));

			}
		}

		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				tmparr[i + (n / 2)][j] = tmparr[i + (n / 2)][j + (m / 2)];
			}
		}

		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				tmparr[i + (n / 2)][j + (m / 2)] = tmparr[i][j + (m / 2)];
			}
		}

		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				tmparr[i][j + (m / 2)] = temp[i][j];
			}
		}

//		for (int i = 0; i < n; i++) {
//			System.out.println(Arrays.toString(tmparr[i]));
//		}

	}

	static void rotate6(int[][] tmparr) {
		int n = tmparr.length;
		int m = tmparr[0].length;

		int[][] temp = new int[n / 2][m / 2];

//		System.out.println("in");

		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				temp[i][j] = tmparr[i][j];
			}
		}
//
//		for (int i = 0; i < n / 2; i++) {
//			System.out.println(Arrays.toString(temp[i]));
//		}

		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				tmparr[i][j] = tmparr[i][j + (m / 2)];
//				System.out.println(Arrays.toString(tmparr[i]));

			}
		}

		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				tmparr[i][j + (m / 2)] = tmparr[i + (n / 2)][j + (m / 2)];
			}
		}

		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				tmparr[i + (n / 2)][j + (m / 2)] = tmparr[i + (n / 2)][j];
			}
		}

		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				tmparr[i + (n / 2)][j] = temp[i][j];
			}
		}

//		for (int i = 0; i < n; i++) {
//			System.out.println(Arrays.toString(tmparr[i]));
//		}

	}

}
