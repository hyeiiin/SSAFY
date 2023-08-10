package algorithm;

import java.util.*;
import java.io.*;

public class Solution_6808_문혜린 {
	static int win, lose; //규영이가 이긴 횟수, 진 횟수
	static int numbers[]; //순열 배열
	static boolean isSelected[]; //숫자 사용 여부 배열
	static int card[]; //인영이가 가진 카드
	static int otherCard[]; //규영이가 가진 카드
	static StringBuilder sb = new StringBuilder();
	
	///순열
	public static void algorithm(int cnt) {
		if(cnt == 9) {
			int score = 0; //인영 점수
			int otherScore = 0; //규영 점수
			for (int i = 0; i < 9; i++) {
				if(otherCard[i]>card[numbers[i]]) { //규영이가 높은 경우
					otherScore += otherCard[i]+ card[numbers[i]];
				}
				else if(otherCard[i]<card[numbers[i]]) { //인영이가 높은 경우
					score += otherCard[i]+ card[numbers[i]];
				}
			}
			if(score>otherScore) { //인영이가 이기는 경우
				lose++;
			}
			else if(score<otherScore) { //규영이가 이기는 경우
				win++;
			}
		}
		else {
			for(int i=0; i<9; i++) {
				if(isSelected[i]) {
					continue;
				}
				numbers[cnt] = i;
				isSelected[i] = true;
				algorithm(cnt+1);
				isSelected[i] = false;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine()); //테스트 케이스 개수
		
		for(int t=1; t<=TC; t++) {
			//초기화
			win = 0;
			lose = 0;
			numbers = new int[9];
			isSelected = new boolean[9];
			card = new int[9];
			otherCard = new int[9];
			
			boolean other[] = new boolean[19]; //규영이가 가진 카드 true
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				other[tmp] = true; //규영이가 가진 카드면 true
				otherCard[i] = tmp; //규영이가 가진 카드 저장
			}
			int cnt=0;
			for (int i = 1; i < 19; i++) {
				if(!other[i]) { //규영이가 가지고 있지 않으면
					card[cnt++] = i;
				}
			}
			algorithm(0);
			sb.append("#"+t+" "+win+" "+lose+"\n");
		}
		System.out.println(sb);
	}

}
