package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1233_문예은 {

	// 부모노드가 숫자이고, 리프노드가 연산자이면 0
	// 부모노드가 모두 연산자이고, 리프노드 모두 숫자이면 1
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int test_case = 1; test_case <= 10; test_case++) { // 테스트케이스 고정
			StringBuilder sb = new StringBuilder();
			sb.append("#" + test_case + " ");
			int N = Integer.parseInt(br.readLine()); // 정점 개수 
			ArrayList<String[]> nodes = new ArrayList<>(); // 정점 개수만큼 번호, 연산자 또는 숫자 받기
			for (int n = 0; n < N; n++) { // 정점 개수만큼 반복
				st = new StringTokenizer(br.readLine());
				if (st.countTokens() == 4) { // 부모노드일 때
					nodes.add(new String[]   // 정점 번호,연산자,왼쪽 자식노드,오른쪽 자식노드
							{st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken()});
				} else if (st.countTokens() == 2) { // 리프노드일 때
					nodes.add(new String[]  // 정점 번호, 숫자
							{st.nextToken(),st.nextToken()}); 
				}
			}
			int count = 0;
			for (String[] n : nodes) { // 입력된 정점 탐색
				if (n.length == 4 && !isOp(n[1])) { // 부모노드이고, 연산자아닐 때
					sb.append(0);
					break;
				}
				else if (n.length == 2 && isOp(n[1])){ // 자식노드이고, 연산자일 때
					sb.append(0);
					break;
				}
				count++;
			}
			if (count == N) {
				sb.append(1);
			}
			System.out.println(sb);
		}
		
	}
	private static boolean isOp(String op) {
		if (op.equals("-") || op.equals("+") || op.equals("/") || op.equals("*"))
			return true;
		else return false;
	}

}
