package algorithm;

import java.util.*;
import java.io.*;

//swea 농작물 수확하기
public class Solution_2805_문혜린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine()); //농장 크기
			int[][] val = new int[N][N]; //농작물 가치
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String str = st.nextToken(); //문자열 받아주기 (공백 없어서)
				for (int j = 0; j < N; j++) {
					val[i][j] = str.charAt(j)-'0'; //문자열 쪼개기
				}
			}
			
			int tmp = N/2+1; //마름모 만들기 위한 변수 (시작 위치)
			int sum = 0; //총 수익
			for(int i=0; i<N; i++) {
				if(i<=N/2) { //위쪽일 경우
					int k=1;
					for(int j=N/2-i; j<(N/2-i)+(i*2+1); j++) {
						sum += val[i][j];
					}
				}
				else { //아래쪽일 경우
					 for(int j=i-N/2; j<(i-N/2)+((N-i)*2-1); j++) {
						 sum += val[i][j];
					 }
				}
			}
			System.out.println("#"+t+" "+sum);
		}

	}

}
