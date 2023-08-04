package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_1225_문예은 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < 10; t++) { // 총 10번 테스트케이스로 고정
			br.readLine(); // 들어오는 테스트케이스 번호 날리기
			Deque<Integer> code = new ArrayDeque<>(); // 암호 배열, 앞뒤 뚫려있는 큐에 담기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) { // 총 8개의 암호 데이터 입력
				code.offer(Integer.parseInt(st.nextToken())); 
			}
			while(code.peekLast() != 0) { // 마지막 원소가 0이 될 때 종료
				for (int i = 1; i <= 5; i++) { // 뺄 값은 1부터 5까지 증가
					int goBack = code.pollFirst(); // 뒤로갈 원소 빼기
					if(goBack-i <= 0) { // 감소시킨 값이 0보다 작거나 같을 때 -> 이 부분 있어야 정상작동
						code.addLast(0); // 0을 마지막원소로 넣고 탈출 
						break;
					}
					code.addLast(goBack-i);
				}
			}
			sb.append("#").append(t+1).append(" "); // 테스트케이스 출력
			for (int i = 0; i < 8; i++) {
				sb.append(code.pollFirst()).append(" "); // 큐 원소 차례로 빼며 출력
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}
