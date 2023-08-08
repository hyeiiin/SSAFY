package sdf;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요. 
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.util.StringTokenizer;
import java.awt.geom.QuadCurve2D;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution_1233_정준원 {
	static int n;
	static boolean[][] visit;
	static Deque<Integer> dq;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int test_case = 1; test_case <= 10; test_case++) {

			int n;
			n = Integer.parseInt(br.readLine());
			int res = 1;

			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken();
				String str = st.nextToken();

				if (st.hasMoreTokens()) {// 자식노드 있는경우 연산자가 있어야함

					if (str.charAt(0) >= '0' && str.charAt(0) <= '9') {

						res = 0;

					}
				} else { // 단말노드일때
					if (str.charAt(0) >= '0' && str.charAt(0) <= '9') {

					} else { // 사칙연산 있을때

						res = 0;

					}
				}

			}

			System.out.print("#" + test_case + " " + res);

			System.out.println();
		}

	}

}