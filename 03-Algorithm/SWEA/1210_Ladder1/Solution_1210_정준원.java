package sdf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요. 
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution_1210_정준원 {
	static ArrayList<Integer> list;

	public static void main(String args[]) throws Exception {

		int T;
		T = 10;
		/*
		 * 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int test_case = 1; test_case <= T; test_case++) {

			/////////////////////////////////////////////////////////////////////////////////////////////
			/*
			 * 이 부분에 여러분의 알고리즘 구현이 들어갑니다.
			 */
			/////////////////////////////////////////////////////////////////////////////////////////////

			int dump = Integer.parseInt(br.readLine());

//			System.out.println("du" + dump);
//			int[] height = new int[dump];

			list = new ArrayList();

			StringTokenizer st = new StringTokenizer(br.readLine());

			while (st.hasMoreTokens()) {
				list.add(Integer.parseInt(st.nextToken()));
			}

//			System.out.println(list.size());
			Collections.sort(list);

			while (dump > 0) {
				dump();
				dump--;
			}

			int res = 0;
			res = list.get(list.size() - 1) - list.get(0);
			System.out.println("#" + test_case + " " + res);

		}
	}

	static void dump() {
//		System.out.println(list.toString());

		int small = list.remove(0);
		int big = list.remove(list.size() - 1);

		list.add(small + 1);
		list.add(big - 1);
		Collections.sort(list);
	}

}