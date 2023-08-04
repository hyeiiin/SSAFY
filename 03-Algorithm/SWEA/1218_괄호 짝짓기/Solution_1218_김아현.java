package ssafy.Swea;

import java.util.*;
import java.io.*;

public class _1218_Swea {

	static Stack<Character> stack;
	static boolean check=true;
	static char[] numArr;
	public static void main(String[] args) throws Exception {
		int test = 10;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= test; t++) {
			Integer.parseInt(br.readLine());
			stack = new Stack<>();
			numArr = br.readLine().toCharArray();
	
			for (char num : numArr) {
				if (num == '{' || num == '[' || num == '(' || num == '<') {
					stack.push(num);
				} else {
					if (stack.isEmpty()) {
						check = false;
						break;
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
							check = false;
							break;
						}
					}
				}
			}

			if (check && stack.isEmpty()) {
				System.out.println("#" + t + " " + 1);
			} else {
				System.out.println("#" + t + " " + 0);
			}
		}

	}

}
