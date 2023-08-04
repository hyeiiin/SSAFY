import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_1218_한정수 {
	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean result = true;
		
		int T = 1;
		for (int test_case=1 ; test_case<=10 ; test_case++) {
			int num = Integer.parseInt(br.readLine());
			char[] input = br.readLine().toCharArray();
			Stack<Character> stack = new Stack<>();
			
			
			for (int i=0; i<num; i++) {
//				stack.push(input[i]);
				if (input[i]==')' || input[i]==']' || input[i]=='}' || input[i]=='>') {
					if(stack.empty()) {
						// 비어있는 상태에서, 즉 ( [ { < 가 나오지도 않았는데 닫는게 먼저 나오면 잘못된거임.
						result = false;
						break;
					}
					if(input[i]==')' && stack.lastElement()=='(') {
						stack.pop();
					}
					else if(input[i]==']' && stack.lastElement()=='[') {
						stack.pop();
					}
					else if(input[i]=='}' && stack.lastElement()=='{') {
						stack.pop();
					}
					else if(input[i]=='>' && stack.lastElement()=='<') {
						stack.pop();
					}
					else {
						//잘못된 입력.
						result = false;
						break;
					}
				}
				else {
					//다 읽었는데 스택에 남아있으면 안됨
					stack.push(input[i]);
				}

			}
			if(!stack.empty()) {
				result = false;
			}
			if(result) {
				System.out.printf("#%d %d",test_case, 1);
				System.out.println();
			}
			else {
				System.out.printf("#%d %d",test_case, 0);
				System.out.println();
			}
			result = true;
			
		}
		
		
	}

}
