package ssafy.Swea;

import java.io.*;
import java.util.*;

public class _1210_Swea {

	private static int targetC;

	public static void main(String[] args) throws Exception {
		int T = 10;
		int Size = 100;
		int[][] ladder;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		for (int test = 0; test < T; test++) {
			Integer.parseInt(br.readLine());
			ladder = new int[Size][Size];
			for (int i = 0; i < Size; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < Size; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());

					if (ladder[i][j] == 2) {
						targetC = j;
					}
				}
			}

//			System.out.println(targetC);

			for (int i = Size - 1; i >= 0; i--) {
				System.out.println("main: " + i + "," + targetC);
				// 오른쪽 경계 확인
				// 오른쪽 확인
				if (targetC + 1 < Size && ladder[i][targetC + 1] == 1) {
					while (ladder[i][targetC + 1] == 1) {
						System.out.println("Right: " + i + "," + targetC);
						targetC++;

						if (targetC + 1 >= Size) {
							break;
						}

					}
				} else if (targetC - 1 >= 0 && ladder[i][targetC - 1] == 1) {
					while (ladder[i][targetC - 1] == 1) {
						System.out.println("Left: " + i + "," + targetC);
						targetC--;

						if (targetC - 1 < 0) {
							break;
						}
					}
				}

			}
			System.out.println("#" + (test + 1) + " " + targetC);
		}

	}

}
