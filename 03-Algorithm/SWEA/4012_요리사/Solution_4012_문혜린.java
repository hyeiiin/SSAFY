package algorithm.swea;

import java.util.*;
import java.io.*;

//요리사
public class Solution_4012_문혜린 {
	static int N; //식재료 수
	static boolean numbers[]; //조합이 저장될 배열 (선택되면 true)
	static int[][] synergy; //시너지 배열
	static int min; //두 음식 맛 최소 차이
	
	public static void plus() {
		int A = 0; //A 음식의 합
		int B = 0; //B 음식의 합
		//고른 음식 두개씩 시너지 계산
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i==j) { //자기 자신은 pass
					continue;
				}
				if(numbers[i] && numbers[j]) { //조합에 뽑혔으면
					A += synergy[i][j]; //A에 시너지 더해주기
				}
				else if(!numbers[i] && !numbers[j]) { //조합 안뽑혔으면
					B += synergy[i][j]; //B에 시너지 더해주기
				}
			}
		}
		//두 음식 간의 맛 차이가 최소가 되면 갱신
		if(Math.abs(A-B)<min) {
			min = Math.abs(A-B);
		}
	}
	public static void algorithm(int cnt, int start) { //조합
		if(cnt == N/2) { //조합 완성
			plus();
		}
		else { //조합 만들기
			for (int i = start; i < N; i++) {
				numbers[i] = true;
				algorithm(cnt+1, i+1);
				numbers[i] = false;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); //테스트 케이스 개수
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine()); //식재료 수
			synergy = new int[N][N]; //시너지 배열
			//초기화
			numbers = new boolean[N];
			min = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			algorithm(0, 0);
			sb.append("#"+t+" "+min+"\n");
		}
		System.out.println(sb);
	}

}
