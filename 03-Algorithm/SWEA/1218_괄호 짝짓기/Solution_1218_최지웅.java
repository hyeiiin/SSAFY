package todo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_1218_최지웅 {
	
	static char curChar;
	static char topChar;
	
	static boolean check(String str, int len) {
		Stack<Character> stack = new Stack<>();
		int i;
		for (i = 0; i < len; i++) {
			curChar = str.charAt(i);
			if (stack.isEmpty()) {
				switch(curChar) {
				case '<' :
				case '[':
				case '{':
				case '(':
					stack.push(curChar);
					break;
				case ')':
				case '}':
				case ']':
				case '>':
					return false;
				}
			} else {
				switch(curChar) {
				case '<' :
				case '[':
				case '{':
				case '(':
					stack.push(curChar);
					continue;
				}
				topChar = stack.peek();
				switch(curChar) {
				case ')':
					if (topChar == '(') {
						stack.pop();
					} else return false;
					break;
				case '}':
					if (topChar == '{') {
						stack.pop();
					} else return false;
					break;
				case ']':
					if (topChar == '[') {
						stack.pop();
					} else return false;
					break;
				case '>':
					if (topChar == '<') {
						stack.pop();
					} else return false;
				}	
			}	
		}
		
		if (i == len && stack.isEmpty()) return true;
		else return false;
	}
	
	public static void main(String[] args) throws Exception {
		
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int len;
		String str;
		for (int t = 1; t <= 10; t++) {
			len = Integer.parseInt(br.readLine());
			str = br.readLine();
			sb.append("#").append(t).append(" ");
			if (check(str, len)) sb.append("1");
			else sb.append("0");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
