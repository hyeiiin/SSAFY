import java.util.*;
import java.io.*;

public class Solution_1218_김아현 {
	static Stack<Character> stack;
	static boolean check = true;
	static char[] numArr;

	public static void main(String[] args) throws Exception {
		int test = 10;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= test; t++) {
			Integer.parseInt(br.readLine());
			stack = new Stack<>();
			numArr = br.readLine().toCharArray();
			check = true;

			for (char num : numArr) {
				check = checkBracket(num);
				
				if(check == false) {
					break;
				}
			}

			if (check && stack.isEmpty()) {
				System.out.println("#" + t + " " + 1);
			} else {
				System.out.println("#" + t + " " + 0);
			}
		}
	}

	public static boolean checkBracket(char num) {
		if (num == '{' || num == '[' || num == '(' || num == '<') {
			stack.push(num);
		} else {
			if (stack.isEmpty()) {
				return false;
			} else {
				if (num == '}' && stack.peek() == '{') {
					stack.pop();
				} else if (num == ']' && stack.peek() == '[') {
					stack.pop();
				} else if (num == ')' && stack.peek() == '(') {
					stack.pop();
				} else if (num == '>' && stack.peek() == '<') {
					stack.pop();
				} else {
					return false;
				}
			}
		}

		return true;
	}
}
