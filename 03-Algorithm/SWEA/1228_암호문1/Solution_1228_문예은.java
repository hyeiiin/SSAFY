package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_1228_문예은 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) { // 총 10번 테스트케이스로 고정
			int N = Integer.parseInt(br.readLine()); // 암호문 번호 개수
			LinkedList<Integer> code = new LinkedList<>(); // 암호문 리스트 생성
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				code.add(Integer.parseInt(st.nextToken())); // 각 암호 번호 저장
			}
			int C = Integer.parseInt(br.readLine()); // 명령어 개수
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				st.nextToken(); // I 입력기호 날리기
				int nextIdx = Integer.parseInt(st.nextToken()); // 맨 앞에서부터 참조할 위치 인덱스 
				int insertCnt = Integer.parseInt(st.nextToken()); // 삽입할 암호 개수
				for (int i = 0; i < insertCnt; i++) {
					int num = Integer.parseInt(st.nextToken()); // 삽입할 암호 코드
					code.add(nextIdx++, num);
				}
			}
			sb.append("#").append(t).append(" "); // 테스트케이스 출력
			for (int i = 0; i < 10; i++) {
				sb.append(code.pollFirst()).append(" "); // 원소 차례로 빼며 출력
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}
