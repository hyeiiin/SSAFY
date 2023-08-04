package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_1218_탁하윤 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc=1; tc<=10; tc++) {
			int len = Integer.parseInt(br.readLine());	// 주어진 괄호의 개수
			
			char[] in = br.readLine().toCharArray();	// 주어진 괄호를 담을 char[] 배열
			Stack<Character> stack1 = new Stack<>();	// '('
			Stack<Character> stack2 = new Stack<>();	// '['
			Stack<Character> stack3 = new Stack<>();	// '{'
			Stack<Character> stack4 = new Stack<>();	// '<'
			
			int result = 1;	// 1- 유효함 0- 유효하지 않음
			
			for(int i=0; i<len; i++) {
				// 괄호의 개수만큼 돌면서 열린괄호라면 각 스택에 push
				if(in[i] == '(') {
					stack1.add(in[i]);
				} else if(in[i] == '[') {
					stack2.add(in[i]);
				} else if(in[i] == '{') {
					stack3.add(in[i]);
				} else if(in[i] == '<') {
					stack4.add(in[i]);
				}
				// 닫힌 괄호라면 스택이 비어있지 않다면 각 스택에서 pop, 
				// 비어있다면 유효하지 않은 괄호이기 때문에 결과값을 0으로 바꾸고 for문을 종료한다.
				if(in[i]==')') {
					if(!stack1.isEmpty()) {
						stack1.pop();
					} else {
						result = 0;
						break;
					}
				} else if(in[i]==']') {
					if(!stack2.isEmpty()) {
						stack2.pop();
					} else {
						result = 0;
						break;
					}
				} else if(in[i]=='}') {
					if(!stack3.isEmpty()) {
						stack3.pop();
					} else {
						result = 0;
						break;
					}
				} else if(in[i]=='>') {
					if(!stack4.isEmpty()) {
						stack4.pop();
					} else {
						result = 0;
						break;
					}
				}
			}
			
			// for문을 다 돌고 스택에 값이 남아있다면 유효하지 않은 괄호이기 때문에 결과값을 0으로 해준다.
			if(!stack1.isEmpty() || !stack2.isEmpty() || !stack3.isEmpty() || !stack4.isEmpty()) {
				result = 0;
			}
			
			System.out.printf("#%d %d\n", tc, result);
		}

	}

}
