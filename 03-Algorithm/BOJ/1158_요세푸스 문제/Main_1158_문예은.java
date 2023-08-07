package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_1158_문예은 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken()); // 1부터 N번까지의 사람
		int K = Integer.parseInt(st.nextToken()); // 순서대로 K번째 사람 제거
		Deque<Integer> table = new ArrayDeque<Integer>();
		for (int n = 1; n <= N; n++) {
			table.offer(n); // 순서대로 사람 앉히기
		}
		
		sb.append("<");
		while(table.size() != 1) {
			for (int k = 1; k < K; k++) { // 1부터 k-1 까지 뒤로 옮기기
				table.offerLast(table.pollFirst()); 
			}
			sb.append(table.pollFirst()).append(", "); // k번째 앉아있는 사람 빼기
		}
		sb.append(table.poll()).append(">");
		System.out.println(sb);
	}

}
