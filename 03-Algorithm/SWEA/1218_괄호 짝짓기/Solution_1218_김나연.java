import java.util.Scanner;
import java.util.Stack;

public class Solution_1218_김나연 {
	
	static int n;
	static String s;
	static int flag=1;

	public static void main(String[] args) {
		
		
		Scanner in = new Scanner(System.in);
		
		for(int tc=1;tc<=10;tc++) {
			Stack<Character> st = new Stack<>();
			flag=1;
			
			n = in.nextInt();
			s = in.next();
			
			
			for (int i = 0; i < n; i++) {
				
				if(!st.empty()) {
					if(st.peek()=='(') {
						if(s.charAt(i)==')') {
							st.pop();
						}else if(s.charAt(i)==']'||s.charAt(i)=='>'||s.charAt(i)=='}') {
							flag=0;
							break;
						}else {
							st.push(s.charAt(i));
						}
					}
					
					else if(st.peek()=='[') {
						if(s.charAt(i)==']') {
							st.pop();
						}else if(s.charAt(i)==')'||s.charAt(i)=='>'||s.charAt(i)=='}') {
							flag=0;
							break;
						}else {
							st.push(s.charAt(i));
						}
					}
					
					else if(st.peek()=='<') {
						if(s.charAt(i)=='>') {
							st.pop();
						}else if(s.charAt(i)==']'||s.charAt(i)==')'||s.charAt(i)=='}') {
							flag=0;
							break;
						}else {
							st.push(s.charAt(i));
						}
					}
					
					else if(st.peek()=='{') {
						if(s.charAt(i)=='}') {
							st.pop();
						}else if(s.charAt(i)==']'||s.charAt(i)=='>'||s.charAt(i)==')') {
							flag=0;
							break;
						}else {
							st.push(s.charAt(i));
						}
					}
					
					else {
						flag=0;
						break;
						
					}
				}else {
					st.push(s.charAt(i));
				}
			}

			if(flag==1) System.out.println("#" + tc + " " + 1);
			else System.out.println("#" + tc + " " + 0);

		}
		
		
	}

}
