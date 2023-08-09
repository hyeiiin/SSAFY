package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_11286_문예은 {
	/*
	 * 0아니면 배열에 자체 값 추가 0이면 배열에서 절대값 작은값 '출력'
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int op; // 연산 정보
		int N = Integer.parseInt(br.readLine()); // 연산 개수
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]==o2[0]) { // 절대값 같을 때
						return o1[1]-o2[1];
				}
				else { // 절대값 다를 때
					 return o1[0] - o2[0];
				}
			}
		}); // 자동 정렬배열
		
		for (int n = 0; n < N; n++) {
			op = Integer.parseInt(br.readLine());
			if(op != 0) { // 배열에 값 추가
				pq.offer(new int[] {Math.abs(op), op}); // 절대값, 실제값
			} else if (op == 0 && pq.isEmpty()) { // 배열 비어있는데 출력하라고 한 경우
				sb.append(0+"\n");
			} else { // 배열에서 절대값 작은 값 출력
				int[] arr = pq.poll();
				sb.append(arr[1]+"\n");
			}
		}
		System.out.print(sb.toString());
	}

}
