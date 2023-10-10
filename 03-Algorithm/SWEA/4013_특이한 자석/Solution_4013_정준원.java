package sdf;

import java.util.Arrays;
/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.SynchronousQueue;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution_4013_정준원 {

	static int N;
	static int res = 0;
	static boolean[][] visit;
	static int[][] arr;
	static int[] dirs;
	static int[][] move;
	static int[][] arrcopy = new int[4][8];

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int k = Integer.parseInt(br.readLine());
			res = 0;
			arr = new int[4][8];
			visit = new boolean[N][N];

			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					// System.out.print(" " + arr[i][j]);
				}
				// System.out.println();
			}

			// System.out.println("k " + k);
			move = new int[k][2];
			copy();

			for (int i1 = 0; i1 < k; i1++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				move[i1][0] = Integer.parseInt(st.nextToken());// 몇번째
				move[i1][1] = Integer.parseInt(st.nextToken()); // 방향
				dirs = new int[4];
				solve(move[i1][0] - 1, move[i1][1]);

//				for (int i = 0; i < 4; i++) {
//					for (int j = 0; j < 8; j++)
//						System.out.print(" copy" + arrcopy[i][j]);
//					System.out.println();
//				}

			}

			for (int i = 0; i < 4; i++) {
				getsum(i);
			}

			System.out.println("#" + test_case + " " + res);
		}

	}

	static void getsum(int idx) {
		// s극 이 1 n 극이 0
		int i = idx;

		// System.out.println("res" + res + " " + i + "arrcopy" + arrcopy[i][0]);

		for (int i1 = 0; i1 < 1; i1++) { // 0번째 값

			if (i == 0 && arrcopy[i][i1] == 1) {
				res += 1;
				// System.out.println("res000 " + res);
			}

			else if (i == 1 && arrcopy[i][i1] == 1) {
				res += 2;
				// System.out.println("res111 " + res);

			} else if (i == 2 && arrcopy[i][i1] == 1) {
				res += 4;
				// System.out.println("res222 " + res);

			} else if (i == 3 && arrcopy[i][i1] == 1) {
				res += 8;
				// System.out.println("res333 " + res);
			}
		}

	}

	static void copy() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				arrcopy[i][j] = arr[i][j];
			}
		}

	}

	static void solve(int idx, int dir) {
		// 만나는 톱니 인덱스 2 6 6 6
		// System.out.println("dirrrrr" + dir);
		dirs[idx] = dir;
		int dircopy = dir;

		int v = arrcopy[idx][6];
		for (int i = idx - 1; i >= 0; i--) {
//			System.out.println("v" + v);

//			System.out.println("diff" + arrcopy[i][2]);
			if (v != arrcopy[i][2]) {
				dirs[i] = -dir;
				dir = -dir;
			}

			if (dirs[i] == 0) {
				break;
			}

			v = arrcopy[i][6];
		}

		v = arrcopy[idx][2];

		dir = dircopy;

		for (int i = idx + 1; i < 4; i++) {
			if (v != arrcopy[i][6]) {
//				System.out.println("-1111111111");
				dirs[i] = -1 * dir;
				dir = -dir;
			}
			if (dirs[i] == 0)
				break;
			v = arrcopy[i][2];
		}
//
//		for (int i : dirs)
//			System.out.println("dirs" + i);

		for (int i = 0; i < 4; i++) {
			if (dirs[i] == 1) {
				// System.out.println("clock");
				clockwise(i);
			} else if (dirs[i] == -1) {
//				System.out.println("counter");
				counterclockwise(i);
			}

		}

	}

	private static void clockwise(int idx) { // 인덱스
		int tmp = arrcopy[idx][7];
		for (int i = 7; i >= 1; i--) {
			arrcopy[idx][i] = arrcopy[idx][i - 1];
		}
		arrcopy[idx][0] = tmp;
	}

	private static void counterclockwise(int idx) {
		int tmp = arrcopy[idx][0];
//		System.out.println("tmp" + tmp + " IDX" + idx);
		for (int i = 1; i <= 7; i++) {
			arrcopy[idx][i - 1] = arrcopy[idx][i];
//			System.out.println("innnn" + arrcopy[idx][i]);

		}

		arrcopy[idx][7] = tmp;
	}

}