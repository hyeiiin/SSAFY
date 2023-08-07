package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2493
 * 탑
 * @author SSAFY
 *
 */
public class Main_2493_박정인 {
	static int N;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> s = new Stack<>();
		Stack<Integer> idxStack = new Stack<>();
		int[] results = new int[N + 1];
		
		for (int i = N; i >= 1; i--) {
			if (s.isEmpty() || s.peek() > arr[i]) {
				s.push(arr[i]);
				idxStack.push(i);
				continue;
			}
						
			while (!s.isEmpty() && s.peek() < arr[i]) {
				s.pop();
				int idx = idxStack.pop();
				results[idx] = i;
			}
			s.push(arr[i]);
			idxStack.push(i);
		}
		
		for (int i = 1; i <= N; i++) {
			sb.append(results[i]).append(" ");
		}
		
		System.out.println(sb);
	}
}
