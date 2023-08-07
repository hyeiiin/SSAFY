package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_2493_문혜린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		Stack<int[]> stack = new Stack<>(); //탑 번호, 높이
		
		for(int i=1; i<=N; i++) {
			//탑 높이 입력 받기
			int height = Integer.parseInt(st.nextToken());
			//스택이 비어있지 않고 현재 스택 최상단 원소가 입력받은 높이보다 작을 경우
			while(!stack.isEmpty() && stack.peek()[1] < height) {
				//신호를 수신할 수 없으므로 높은 탑 나올때까지 pop
				stack.pop();
			}
			//스택이 비어있지 않고 현재 스택 최상단 원소가 입력받은 높이보다 클 경우
			if(!stack.isEmpty() && stack.peek()[1] > height) {
				//신호를 수신할 수 있으므로 수신한 탑 번호 저장
				sb.append(stack.peek()[0] + " ");
			}
			//스택 비어있을 경우 수신 가능한  탑 존재하지 않음
			if(stack.isEmpty()) {
				sb.append(0 + " ");
			}
			//현재 입력받은 탑 스택에 저장
			stack.add(new int[] {i, height});
		}
		System.out.println(sb);
	}

}
