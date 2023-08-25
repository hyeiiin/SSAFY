package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17281_정준원 {

	static int[] seq; // 타순 정하기... 각 값에다 선수번호 할당한다.
	static boolean[] visitplay;
	static int res = Integer.MIN_VALUE;
	static int[][] inning;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int N;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		inning = new int[N][10];
		seq = new int[10];
		seq[4] = 1; // 4번타자 1번선수
		visitplay = new boolean[10];
		visitplay[4] = true;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				inning[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		playerput(2);
		System.out.println(res);

	}

	static int play() {
		// System.out.println("play");

		int sum = 0;
		int idx = 1; // 몇번째게임인지

		//System.out.println(Arrays.toString(seq)); // 몇번째 게임에 몇번타자가 오는지...
		for (int i = 0; i < inning.length; i++) {
			boolean[] lu = new boolean[4];

			// System.out.println("i " + i);
			int out = 0;
//			System.out.println("ssss");

			while (out < 3) {

				// System.out.println("idx out " + idx + " " + out + " ");
				// System.out.println("idx out num" + idx + " " + out + " " +
				// num);
				int num = inning[i][seq[idx]];
				if (num == 1) {
					for (int r = 3; r >= 1; r--) {
						if (lu[r]) {
							lu[r] = false;
							int nr = (r + 1);
							if (nr > 3) {
								sum++;
							} else
								lu[nr] = true;
						}
					}
					lu[1] = true;
				}

				if (num == 2) { // 2번 선수일 경우 ..
					for (int r = 3; r >= 1; r--) {
						if (lu[r]) {
							lu[r] = false;
							int nr = (r + 2);
							if (nr > 3) {
								sum++;
							} else
								lu[nr] = true;
						}
					}

					lu[2] = true;
				}

				if (num == 3) {

					for (int r = 3; r >= 1; r--) {
						// System.out.println("r" + r);

						if (lu[r]) {
							lu[r] = false;
							int nr = (r + 3);
							if (nr > 3) {
								sum++;
							} else
								lu[nr] = true;
						}
					}
					lu[3] = true;

				}

				if (num == 4) {
					for (int r = 3; r >= 1; r--) {
						if (lu[r]) {
							lu[r] = false;
							int nr = (r + 4);
							if (nr > 3) {
								sum++;
							}
						}

					}
					sum++;
				}

				if (num == 0)
					out++;

				idx++;

				if (idx >= 10) {
					idx = idx % 9;
				}

//				System.out.println("while");
			}

		}

	//	System.out.println("sum" + sum);
		return sum;

		// System.out.println("endplay");

	}

	static void playerput(int depth) { // 몇번재 선수번호인지

//		System.out.println("depth" + depth);

		if (depth == 10) {
//			System.out.println("depth end");
			int v = play();
			res = Math.max(res, v);
			return;
		}

		for (int i = 1; i < 10; i++) { // 타순

			if (!visitplay[i]) {
				seq[i] = depth;
				visitplay[i] = true;
//				System.out.println("before recur" + depth + 1);
				playerput(depth + 1);
				visitplay[i] = false;
			}

		}

	}
}
