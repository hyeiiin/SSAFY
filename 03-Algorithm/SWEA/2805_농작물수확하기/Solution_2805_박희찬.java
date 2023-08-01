import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int TC;
	static int N;
	static int sum;
	static int[][] lst;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc < TC + 1; tc++) { /////////// TC

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 1 <= N <= 49

			lst = new int[N][N];

			for (int i = 0; i < N; i++) {
				String str = br.readLine();

				for (int j = 0; j < N; j++) {
					lst[i][j] = str.charAt(j) - '0';
				}
			}

			sum = 0;
			
			// 1. 중앙 행까지만 반복
			for (int row = 0; row <= N / 2; row++) {
				// 2. 해당 행에 맞게 열의 범위를 설정하여 합산
				for (int col = N / 2 - row; col <= N / 2 + row; col++) {
					if (row == N / 2) {  // 2-1. 중앙 행 == 합산
						sum += lst[row][col];
					} else {  // 2-2. 중앙 이외의 행은 대칭이므로 상, 하 구역 합산
						sum += lst[row][col];
						sum += lst[N - row - 1][col];
					}
				}
			}
			System.out.printf("#" + tc + " " + sum + "\n");
		}  /////////// TC

	}
}
