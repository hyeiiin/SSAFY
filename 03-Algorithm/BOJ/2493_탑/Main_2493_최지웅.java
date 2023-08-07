package todo._0807;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_최지웅 {

	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int[] tops = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			tops[n] = Integer.parseInt(st.nextToken());
		}
		
		int[] receiver = new int[N + 1]; 
		Stack<Integer> stack = new Stack<>();
	
		
		for (int n = 1; n <= N; n++) {
			if (stack.isEmpty()) {
				receiver[n] = 0;
				stack.push(n);
			} else {
				while (!stack.isEmpty() && tops[stack.peek()] < tops[n]) {
					stack.pop();
				}
				if (stack.isEmpty()) {
					receiver[n] = 0;
					stack.push(n);
				} else {
					receiver[n] = stack.peek();
					stack.push(n);
				}
			}
		}
			
		StringBuilder sb = new StringBuilder();
		for (int idx = 1; idx <= N; idx++) {
			sb.append(receiver[idx] + " ");
		}
		System.out.println(sb.toString());
	}

}
