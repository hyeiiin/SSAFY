import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	static int TC;
	static int[] lst = new int[8];
	static Deque<Integer> qu = new LinkedList<>();
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//= new StringTokenizer(br.readLine());
		
		
		for (int tc = 1; tc < 10 + 1; tc++) {  // TC
			st = new StringTokenizer(br.readLine());
			TC = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());

			// Deque에 8개의 숫자를 입력 받음
			for (int i = 0; i < 8; i++) {
				qu.add(Integer.parseInt(st.nextToken()));
			}
			
			func();
			
			
		}  // TC
	}
	
	private static void func() {
		while(true) {

			// 주어진 동작 5번 수행
			for (int i = 1; i < 6; i++) {
				int cur = qu.peek();

				// 1. 숫자가 감소할 때 0보다 작아지는 경우 0으로 유지되며, 출력 및 프로그램 종료.
				if (cur - i <= 0) {
					qu.pollFirst();
					qu.addLast(0);
					
					sb = new StringBuilder();
					sb.append("#").append(TC).append(" ");
					while(!qu.isEmpty()) {
						sb.append(qu.pollFirst()).append(" ");
					}
					System.out.println(sb.toString());
					
					return;
				} else {  // 2. (1~5) 감소시킨 수를 맨 뒤로 보냄.
					qu.addLast(qu.pollFirst() - i);
				}
			}
		}
	}

}
