package sdf;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
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
import java.awt.geom.QuadCurve2D;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution_2805_정준원 {
	static int[] dr = { 1, -1, 1, -1 };
	static int[] dc = { 1, -1, -1, 1 };
	static int n;
	static boolean[][] visit;

	public static void main(String args[]) throws Exception {
		/*
		 * 아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다. 여러분이 작성한 코드를
		 * 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후, 이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때
		 * 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다. 따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		 * 단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		// System.setIn(new FileInputStream("res/input.txt"));

		/*
		 * 표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());

		/*
		 * 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		 */

		for (int test_case = 1; test_case <= T; test_case++) {

			n = Integer.parseInt(br.readLine());

			int[][] arr = new int[n][n];
			visit = new boolean[n][n];

			int len = n / 2;

			for (int i = 0; i < n; i++) {
				String str = br.readLine();
				for (int j = 0; j < n; j++) {
					arr[i][j] = str.charAt(j) - '0';
				}
			}

			for (int i = 0; i < n; i++) {
//				System.out.println(Arrays.toString(arr[i]));
			}

			int sum = 0;
			int res = 0;

			for (int i = 0; i <= len; i++) {
				for (int j = len - i; j < len + i + 1; j++) {
					sum += arr[i][j];

				}
//				System.out.println("sum" + sum);
			}

			for (int i = 0; i < len; i++) {
				for (int j = i + 1; j <= n - 1 - i - 1; j++) {
					sum += arr[i + len + 1][j];
				}
//				System.out.println("sum" + sum);
			}

			System.out.println("#" + test_case + " " + sum);

		}

	}
//
//	static int solve(int sum, int r, int c, int[][] arr) {
//		sum += arr[r][c];
//
//		Queue<int[]> q = new LinkedList<>();
//
//		q.add(new int[] { r, c });
//		sum += arr[r][c];
//
//		while (!q.isEmpty()) {
//			int[] v = q.poll();
//			int vr = v[0];
//			int vc = v[1];
//
//			for (int i = 0; i < 4; i++) {
//				int nr = vr + dr[i];
//				int nc = vc + dc[i];
//
//				if (isvalid(nr, vc) && !visit[nr][nc]) {
//					q.add(new int[] { nr, nc });
//					visit[nr][nc] = true;
//					sum += arr[nr][nc];
//				}
//			}
//
//		}
//
//		return sum;
//
//	}

	static boolean isvalid(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < n;
	}
}