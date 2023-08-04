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
		Stack<Character> stack = new Stack<>();
		char[] arr = str.toCharArray();
		try {
			for (char c : arr) {
				if(c == '{' || c=='[' || c=='(' || c=='<') stack.push(c);
				else if(c == '}') {
					if(stack.peek() == '{') {
						stack.pop();
					} else {
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