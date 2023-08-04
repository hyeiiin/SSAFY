package algorithm.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_1218_박정인 {
	static String prev = "([{<";
	static String post = ")]}>";
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			String str = br.readLine();

			int result = 1;
			
			Stack<String> s = new Stack<>();
			
			for (int i = 0; i < str.length(); i++) {
				String target = "" + str.charAt(i);
				if (prev.contains(target)) {	// 앞 괄호
					s.push(target);
				} else {	// 뒤 괄호
					if (s.isEmpty()) {	// 비었는데 뒤 괄호 들어온 경우
						result = 0;
						break;
					}
					int postIdx = getIndex(target);		// 뒤 괄호 종류				
					int prevIdx = getIndex(s.peek());	// 앞 괄호 종류
					
					if (prevIdx != postIdx) {
						result = 0;
						break;
					}
					s.pop();
				}
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int getIndex(String target) {
		if (post.contains(target)) {
			return post.indexOf(target);
		}
		
		return prev.indexOf(target);
	}
}
