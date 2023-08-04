import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_1218_조은서 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Stack<Character> stack = new Stack<>();
		
		for(int tc=1; tc<=10; tc++) {
			int n = Integer.parseInt(br.readLine());
			char[] charArr = br.readLine().toCharArray();
			
			for(int i=0; i<n; i++) {
				if(charArr[i]==')' && stack.peek() == '(') stack.pop();
				else if(charArr[i]=='}' && stack.peek() == '{') stack.pop();
				else if(charArr[i]==']' && stack.peek() == '[') stack.pop();
				else if(charArr[i] == '>' && stack.peek() == '<') stack.pop();
				else {
					stack.push(charArr[i]);
				}
			}
				
			if(stack.isEmpty()) {
				sb.append("#").append(tc).append(" ").append("1").append("\n");
			} else {
				sb.append("#").append(tc).append(" ").append("0").append("\n");
			}
			
			stack.clear();
		}
		
		System.out.println(sb);
	}
}
