package SWEA;

import java.util.*;
import java.io.*;

public class Solution_1218_신동근 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		// 테스트케이스 10번 돌리기
		for(int tc=1; tc<=10; tc++) {
			int N = Integer.parseInt(br.readLine());	// 문자열 길이 입력받기
			String input = br.readLine();	// 문자열 입력받기
			Stack<Character> stack = new Stack<>();	// 괄호 짝 맞는지 여부를 판단해주기 위해 Stack 이용하기
			boolean isOk = false;	// 괄호 짝 맞는지 여부를 판단해주는 변수 -> 괄호짝 맞으면 false, 틀리면 true
			int result = 1;		// 결과값 일단 1로 지정하고 시작
			
			// 문자열 길이만큼 탐색
			for(int i=0; i<input.length(); i++) {
				char ch = input.charAt(i);	// 문자 한글자씩 뽑기
				
				// 해당 문자가 괄호 왼쪽 부분을 담당하는 문자인 경우
				if(ch == '(' || ch == '{' || ch == '[' || ch == '<') {
					stack.push(ch);	// 스택에 집어넣음
				}
				// 해당 문자가 괄호 오른쪽 부분을 담당하는 문자인 경우
				else if(ch == ')' || ch == '}' || ch == ']' || ch == '>') {
					// 스택이 비어있지 않은 경우
					if(!stack.isEmpty()) {
						char tempCh = stack.pop();	// 스택에서 최상단 값 뽑음(왼쪽 괄호에 해당하는 것)
						// 소괄호인 경우
						if(ch == ')') {
							// 스택에서 뽑은 괄호가 짝이 안맞으면
							if(tempCh != '(') {
								isOk = true;	// 괄호짝 안맞는거임(true)
								break;	// 더 탐색할 필요없이 반복문 빠져나옴
							}
						}
						// 중괄호인 경우
						else if(ch == '}') {
							// 스택에서 뽑은 괄호가 짝이 안맞으면
							if(tempCh != '{') {
								isOk = true;	// 괄호짝 안맞는거임(true)
								break;			// 더 탐색할 필요없이 반복문 빠져나옴
							}
						}
						// 대괄호인 경우
						else if(ch == ']') {
							// 스택에서 뽑은 괄호가 짝이 안맞으면
							if(tempCh != '[') {
								isOk = true;	// 괄호짝 안맞는거임(true)
								break;			// 더 탐색할 필요없이 반복문 빠져나옴
							}
						}
						// 부등호 괄호인 경우
						else if(ch == '>') {
							// 스택에서 뽑은 괄호가 짝이 안맞으면
							if(tempCh != '<') {
								isOk = true;	// 괄호짝 안맞는거임(true)
								break;			// 더 탐색할 필요없이 반복문 빠져나옴
							}
						}
					}
					// 스택이 비어있는 경우
					else {
						isOk = true;	// 스택 비어있으면 괄호짝 안맞는거임(true)
						break;			// 더 탐색할 필요없이 반복문 빠져나옴
					}
				}
			}
			
			// 괄호짝이 안맞는 경우
			if(isOk) {
				result = 0;	
			}
			// 괄호짝이 맞는 경우
			else {
				result = 1;
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.print(sb);

	}

}
