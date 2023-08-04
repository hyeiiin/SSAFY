package swea;

import java.util.Scanner;
import java.util.Stack;

public class Solution_1218_전상혁 {
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		int size;
		String str;
		for (int tc = 1; tc <= 10; tc++) {
			size = sc.nextInt();
			str = sc.next();
			
			Stack<Character> stack = new Stack<Character>();
			char bracket;
			int res = 0;
			
			for (int i = 0; i < size; i++) {
				//왼괄호면 스택에 넣어주기
				bracket = str.charAt(i);
				if ((bracket=='{' || bracket=='[' || bracket=='(' || bracket=='<')) {
					stack.push(bracket);
				//오른괄호면 왼괄호와 짝이 맞는지 비교 	
				}else {
					if ((match(bracket,stack.peek()))) {
						//top 원소(왼괄호)와 짝이 맞으면 꺼내서 제거
						stack.pop();
					}else {
						res = 0;
						break;
					}
				}
			}
			//유효=1 유효x=0
			if (stack.isEmpty()) {
				res = 1;
			}else
				res = 0;
			
			
			System.out.printf("#%d %d%n", tc, res);
			
		}
		

	}
	//짝 매칭
	private static boolean match(char a, char b) {
		if (a=='}' && b=='{') {
			return true;
		}else if(a==']' && b=='[') {
			return true;
		}else if(a==')' && b=='(') {
			return true;
		}else if(a=='>' && b=='<') {
			return true;
		}else return false;
	}
	

}
