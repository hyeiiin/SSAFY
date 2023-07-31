package SWEA;

import java.util.*;
import java.io.*;

public class Solution_1289_신동근 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			String memory = br.readLine();
			
			char beforeBit = '0';	// 메모리에서 이전 비트 '0'으로 초기화
			int count = 0;	// 원래 값으로 복구하기 위한 최소 수
			
			// 메모리에 저장된 비트 수만큼 탐색
			for(int i=0; i<memory.length(); i++) {
				// 이전비트와 탐색한 메모리에 담긴 비트가 다른 경우
				if(beforeBit != memory.charAt(i)) {
					beforeBit = memory.charAt(i);	// 이전 비트 갱신해주기
					count++;	// 복구하기 위한 최소 수 증가
				}
			}
			
			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}
		System.out.print(sb);

	}

}
