package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_1289_문예은 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 입력
		
		for (int i = 0; i < T; i++) { // 테스트 케이스만큼 반복 
			int cnt = 0; // 고친 횟수 
			String str = br.readLine(); // 메모리값 문자열 입력 
			char[] ch = str.toCharArray(); // 한 문자씩 탐색하기 위해 CHAR배열로 변경
			if(ch[0] == '1') cnt++; // 맨 첫 문자가 1이면 한번 추가, (0이면 변경안함)
			for (int j = 1; j < ch.length; j++) { // 그 다음 문자부터 탐색
				if(ch[j]!=ch[j-1]) cnt++; // 현재 문자와 이전 문자가 다르면 변경된 것이므로 한번 추가
			}
			
			System.out.printf("#%d %s%n", i+1, cnt);
		}
	}

}
