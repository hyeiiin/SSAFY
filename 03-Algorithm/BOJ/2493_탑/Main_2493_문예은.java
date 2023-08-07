package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_문예은 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int lazorCnt = Integer.parseInt(br.readLine());
		Stack<int[]> lazor = new Stack<int[]>(); // 레이저 번호, 높이 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= lazorCnt; i++) { // 레이저 번호만큼 반복, 0이 아닌 1부터 시작
			int height = Integer.parseInt(st.nextToken());
			if(lazor.isEmpty()) { // 스택 비어있을 때 맨 첫 단계
				sb.append("0 ");
				lazor.push(new int[] {i, height});
			} else {
				while(true) {
					if(lazor.isEmpty()) { 
						sb.append("0 ");
						lazor.push(new int[] {i, height});
						break;
					}
					int[] top = lazor.peek();
					if(top[1] > height) { //  이전 레이저가 현재 레이저보다 크면?
						sb.append(top[0] + " "); // 이전 레이저의 번호 출력
						lazor.push(new int[] {i, height});
						break;
					} else { //이전 레이저가 현재 레이저보다 작으면? 레이저 수신 불가
						lazor.pop();
					}
				}
			}
		}
		System.out.println(sb);
	}
}
