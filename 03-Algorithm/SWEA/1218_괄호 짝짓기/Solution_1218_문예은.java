package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Solution_1218_문예은 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		HashMap<String, String> map = new HashMap<>(); // 여는 괄호, 닫는 괄호 쌍 생성
		map.put("(", ")");
		map.put("[", "]");
		map.put("{", "}");
		map.put("<", ">");
		for (int t = 0; t < 10; t++) { // 총 10번 테스트케이스로 고정
			int len = Integer.parseInt(br.readLine()); // 괄호 문자열 길이 입력
			String[] arr = br.readLine().split(""); // 공백없는 문자열 각각을 배열에 저장
			Stack<String> stack = new Stack<>(); // 괄호 하나씩 넣을 스택
			
			int result = 1; // 유효성 여부, 1=유효, 0=유효하지 않음
			for(int l = 0; l < len; l++) { // 괄호 문자열 길이만큼 반복
				if (map.containsKey(arr[l])) { // 들어온 괄호가 여는 괄호이면?
					stack.push(arr[l]); // 스택에 넣기
				} else { // 닫는 괄호면?
					if(map.get(stack.pop()).equals(arr[l])) { // pop한 값과 arr[l]의 밸류가 매칭하는지 비교
						result = 1; 
					} else { // 매칭하지 않으면 유효하지 않음
						result = 0;
						break;
					}
				}
			}
			sb.append("#").append(t+1).append(" ").append(result).append("\n");
		}
		System.out.print(sb.toString());
	}

}
