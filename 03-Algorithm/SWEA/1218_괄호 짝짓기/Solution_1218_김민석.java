package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

class Solution_1218_김민석 {

	public static int solution(String str) {
		//정답
		int answer = 0;
		//스택생성
		Stack<Character> stack = new Stack<>();
		//toCharArray로 한글자씩 나누기
		char[] arr = str.toCharArray();
		try {
			for (char c : arr) {
				//시작 괄호일 경우
				if(c == '{' || c=='[' || c=='(' || c=='<') stack.push(c);
				//닫는 괄호일 경우
				else if(c == '}') {
					if(stack.peek() == '{') {
						//다음 뽑을게 같은 경우 pop
						stack.pop();
					} else {
						//아닐경우 예외 던지기
						throw new EmptyStackException();
					}
				}
				else if(c == ']') {
					if(stack.peek() == '[') {
						stack.pop();
					} else {
						throw new EmptyStackException();
					}
				}
				else if(c == ')') {
					if(stack.peek() == '(') {
						stack.pop();
					} else {
						throw new EmptyStackException();
					}
				}
				else if(c == '>') {
					if(stack.peek() == '<') {
						stack.pop();
					} else {
						throw new EmptyStackException();
					}
				}
			}			
			if(stack.isEmpty()) answer = 1;
		} catch (EmptyStackException e) {
			//예외받아서 유효하지 않음을 리턴
			return answer;
		}
		
		return answer;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 1; i <= 10; i++) {
			sb.append("#").append(i).append(" ");
			int length = Integer.parseInt(br.readLine());
			sb.append(solution(br.readLine())).append("\n");
		}
		System.out.println(sb);
	}
}