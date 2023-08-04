import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	static int TC;
	static Stack<Character> stack;
	static StringBuilder sb;
	static int size;  // 문자열 길이
	static int res;  // 최종 판단 Flag

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//= new StringTokenizer(br.readLine());

		for (int tc = 1; tc < 10 + 1; tc++) { // TC
			st = new StringTokenizer(br.readLine());
			size = Integer.parseInt(st.nextToken());
			res = 1; // true
			stack = new Stack<>();
			
			String bracket = br.readLine();
			
			// 주어진 길이 만큼 순회
			for (int i = 0; i < size; i++) {
				char next = bracket.charAt(i);
				
				// 1. 여는 괄호를 만나면 stack에 추가.
				if (next == '(' || next == '{' || next == '[' || next == '<') {
					stack.push(next);
				} else { // 2-1. 닫는 괄호를 만나면
					// 2-2. 괄호 쌍 검사
					if (!stack.isEmpty() && next == ')' && stack.peek() == '(')
						stack.pop();
					else if (!stack.isEmpty() && next == '}' && stack.peek() == '{')
						stack.pop();
					else if (!stack.isEmpty() && next == ']' && stack.peek() == '[')
						stack.pop();
					else if (!stack.isEmpty() && next == '>' && stack.peek() == '<')
						stack.pop();
					else {  // 2-3. 괄호가 유효하지 않음.
						res = 0;
						break;
					}
				} // else
			} // for

			// 유효성 플래그 및 올바른 쌍이라면 stack이 비어있는지 검사.
			if(res == 1 && stack.isEmpty())
				System.out.println("#" + tc + " " + res);
			else System.out.println("#" + tc + " " + res);
		} // TC
	}

}
