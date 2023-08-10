package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_6808_문예은 {
	static int[] inCard, gyuCard; // 순열로 뽑을 인영쓰 카드와 고정된 규영쓰 카드
	static int[] resultCard; // 순열 결과 저장 배열
	static boolean[] useCard; // 규영이가 뽑은 카드 체크 -> 나머지가 인영쓰 카드
	static boolean[] isSelected; // 카드 선택여부 체크
	static int win, lose;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken()); // 테스트케이스 개수
		for (int t = 1; t <= T; t++) {
			win = 0; // 이기는 경우의 수
			lose = 0; // 지는 경우의 수
			inCard = new int[9];
			gyuCard = new int[9];
			useCard = new boolean[19]; // 1~18까지 카드에서 규영쓰가 뽑은 카드 체크
			isSelected = new boolean[9]; 
			resultCard = new int[9];

			st = new StringTokenizer(br.readLine()); // 규영쓰 9장 카드에 대한 정보 입력
			for (int i = 0; i < 9; i++) {
				gyuCard[i] = Integer.parseInt(st.nextToken()); // 한장씩 숫자 입력 
				useCard[gyuCard[i]] = true; // 카드번호에 맞춰 사용처리
			}
			int num = 0;
			for (int i = 1; i <= 18; i++) {
				if(!useCard[i]) inCard[num++] = i; // 인영쓰 9장 카드 정보 입력
			}
			permutation(0);
			sb.append("#"+t+" "+win+" "+lose+"\n");
		}
		System.out.println(sb);
	}
	private static void permutation(int cnt) {
		if(cnt == 9) { // 가능한 카드 다 뽑았을 때
			int gyuCnt = 0; // 규영쓰 총점
			int inCnt = 0; // 인영쓰 총점
			for(int i = 0; i < 9; i++) {
				if (gyuCard[i] > resultCard[i]) {
					gyuCnt += gyuCard[i] + resultCard[i];
				} else {
					inCnt += gyuCard[i] + resultCard[i];
				}
			}
			if (gyuCnt > inCnt) win++;
			if (gyuCnt < inCnt) lose++;
			return;
		}
		for (int i = 0; i < 9; i++) { // 1부터 9까지 카드 반복
			if(!isSelected[i]) { // 이미 뽑힌 숫자가 아니라면?
				// 해당 숫자 넣어주기
				resultCard[cnt] = inCard[i];
 				isSelected[i]= true;
				// 다음 자리 순열 뽑기
				permutation(cnt+1);
				isSelected[i]= false; 
			}
		}
	}
}
