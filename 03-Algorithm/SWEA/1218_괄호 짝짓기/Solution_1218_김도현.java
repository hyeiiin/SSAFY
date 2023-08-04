import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_1218_김도현 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int test_case = 1; test_case <= 10; test_case++) {
			int N = Integer.parseInt(br.readLine());
			String str = br.readLine();
			Stack<Character> stack = new Stack<>(); // 스택만듬
			boolean flag = false;
			for (int i = 0; i < N; i++) { // 문자 하나하나 비교
				char ch = str.charAt(i); 
				if(!check(ch)) { // 왼쪽 괄호면 스택에 그냥 집어넣음
					stack.push(ch);
				}else {            // 오른쪽 괄호면은 스택의 top값과 비교
					char pop = stack.pop();
					if(compare(pop,ch)) {
						continue;
					}
					else {
						flag = true;
						break;
					}
				}
			}
			if(!stack.empty() || flag == true) {
				System.out.println("#"+test_case+" 0");
			}else if(stack.empty() && flag == false){
				System.out.println("#"+test_case+" 1");
			}
		}
		
		
	}
	
	public static boolean check(char ch) {
		if(ch=='{' || ch=='[' || ch=='(' || ch=='<') {
			return false;
		}else {
			return true;
		}
	}
	
	public static boolean compare(char pop, char ch) {
		if(pop=='{') {
			if(ch=='}') {
				return true;
			}else {
				return false;
			}
		}else if(pop=='(') {
			if(ch==')') {
				return true;
			}else {
				return false;
			}
		}else if(pop=='[') {
			if(ch==']') {
				return true;
			}else {
				return false;
			}
		}else if(pop=='<') {
			if(ch=='>') {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
}
