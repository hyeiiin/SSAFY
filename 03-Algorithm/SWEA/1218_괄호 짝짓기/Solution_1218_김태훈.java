package swea;
import java.util.*;
import java.io.*;
public class Solution_1218_김태훈 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int t = 10;
		int[] arr = new int[t];
		for(int i=0; i<t; i++) {
			int n = Integer.parseInt(br.readLine());
			
			Stack<Character> stack = new Stack<>();
			char[] str = br.readLine().toCharArray();
			boolean ar = true;
			for(int j=0; j<n; j++) {
				if(str[j]=='(' || str[j]=='[' || str[j]=='{' || str[j]=='<') {
					stack.push(str[j]);
				}
				else if((str[j]==')' || str[j]==']' || str[j]=='}' || str[j]=='>')) {
					if(stack.isEmpty()) {
						ar = false;
						break;
					}
					if(str[j] == ')' && stack.peek()=='(') {
						stack.pop();
					}
					else if(str[j] == ']' && stack.peek()=='[') {
						stack.pop();
					}
					else if(str[j] == '}' && stack.peek()=='{') {
						stack.pop();
					}
					else if(str[j] == '>' && stack.peek()=='<') {
						stack.pop();
					}
					else {
						ar = false;
						break;
					}
				}
				
			}
			if(stack.isEmpty() && ar)
				arr[i] = 1;
			else
				arr[i] = 0;
		}
		for (int i = 0; i < t; i++) {
			System.out.printf("#%d %d\n",i+1, arr[i]);
		}
	}

}
