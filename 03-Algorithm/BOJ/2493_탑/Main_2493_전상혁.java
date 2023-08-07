package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_전상혁 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		
		Stack<int[]> stack = new Stack<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int top = Integer.parseInt(st.nextToken());
			
			while(!stack.isEmpty()) {
				if (stack.peek()[0]> top) { //기존 스택에 들어 있는 탑의 높이 (즉 왼쪽 탑의 높이) 가 현재 탑보다 클 경우 
					sb.append(stack.peek()[1]).append(" "); //왼쪽 탑의 인덱스 추출
					break;
				}
				//왼쪽 탑의 높이가 현재 탑보다 낮을 경우 스택에서 삭제
				stack.pop(); 
			}
			if (stack.isEmpty()) {
				// 비교할 탑이 없거나 현재 탑보다 높은 탑이 없는 경우
				sb.append(0).append(" ");
			}
			stack.push(new int[] {top, i}); //[6,1] , [9,2] ...
		}
		
		System.out.println(sb);
	
	}

}
