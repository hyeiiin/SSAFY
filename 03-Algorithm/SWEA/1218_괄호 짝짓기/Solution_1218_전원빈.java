package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class _1218 {

	static Stack<Character> s = new Stack<>();
	static int n;
	static String c;
	static int flag = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		for(int test = 1; test <= 10; test++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#"+test + " ");
			n = Integer.parseInt(bf.readLine());
			c = bf.readLine();
			flag = 0;
			for(int i = 0; i< c.length(); i++) {
				if(c.charAt(i) == '(' || c.charAt(i) == '[' || c.charAt(i) == '{' || c.charAt(i) == '<') {
					s.push(c.charAt(i));
				}else if(c.charAt(i) == ')') {
					if(s.peek() != '(') {
						sb.append(0);
						flag = 1;
						break;
					}else s.pop();
				}else if(c.charAt(i) == ']') {
					if(s.peek() != '[') {
						sb.append(0);
						flag = 1;
						break;
					}else s.pop();
				}else if(c.charAt(i) == '}') {
					if(s.peek() != '{') {
						sb.append(0);
						flag = 1;
						break;
					}else s.pop();
				}else if(c.charAt(i) == '>') {
					if(s.peek() != '<') {
						sb.append(0);
						flag = 1;
						break;
					}else s.pop();
				}
			}
			
			if(flag == 0)sb.append(1);
			System.out.println(sb.toString());
			
		}

	}
}